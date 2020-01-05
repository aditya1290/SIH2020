package com.example.sih2020;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcode;
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcodeDetector;
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcodeDetectorOptions;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.common.FirebaseVisionImageMetadata;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.karumi.dexter.listener.single.PermissionListener;
import com.otaliastudios.cameraview.CameraView;
import com.otaliastudios.cameraview.frame.Frame;
import com.otaliastudios.cameraview.frame.FrameProcessor;

import java.util.List;

public class ScanQRActivity extends AppCompatActivity {

    CameraView cameraView;
    boolean isDetected = false;

    FirebaseVisionBarcodeDetector detector;
    FirebaseVisionBarcodeDetectorOptions options;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_qr);

        if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
        {
            requestPermissions(new String[]{Manifest.permission.CAMERA},1);
        }
        if (checkSelfPermission(Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED)
        {
            requestPermissions(new String[]{Manifest.permission.RECORD_AUDIO},1);
        }


        Dexter.withActivity(this)
                .withPermissions(new String[]{Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO})
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        setupCamera();
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {

                    }
                }).check();

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 1);
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO)!= PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, 1);
        }
    }

    private void setupCamera()
    {
        cameraView = (CameraView)findViewById(R.id.CameraView);
        cameraView.setLifecycleOwner(this);
        cameraView.addFrameProcessor(new FrameProcessor() {
            @Override
            public void process(@NonNull Frame frame) {
                processImage(getVisionImageFromFrame(frame));
            }
        });


        options = new FirebaseVisionBarcodeDetectorOptions.Builder()
                .setBarcodeFormats(FirebaseVisionBarcode.FORMAT_QR_CODE).build();

        detector = FirebaseVision.getInstance().getVisionBarcodeDetector(options);
    }

     private void processImage(FirebaseVisionImage image) {
        if(!isDetected)
        {
            detector.detectInImage(image)
                    .addOnSuccessListener(new OnSuccessListener<List<FirebaseVisionBarcode>>() {
                        @Override
                        public void onSuccess(List<FirebaseVisionBarcode> firebaseVisionBarcodes) {
                            processResult(firebaseVisionBarcodes);
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(ScanQRActivity.this,""+ e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }

    private FirebaseVisionImage getVisionImageFromFrame(Frame frame)
    {
        byte[] data = frame.getData();
        FirebaseVisionImageMetadata metadata = new FirebaseVisionImageMetadata.Builder()
                .setFormat(FirebaseVisionImageMetadata.IMAGE_FORMAT_NV21)
                .setWidth(frame.getSize().getWidth())
                .setHeight(frame.getSize().getHeight())
                .build();

        return FirebaseVisionImage.fromByteArray(data, metadata);
    }

    private void processResult(List<FirebaseVisionBarcode> firebaseVisionBarcodes)
    {
        if(firebaseVisionBarcodes.size()>0)
        {
            for(FirebaseVisionBarcode item : firebaseVisionBarcodes)
            {
                createDialog(item.getRawValue());
            }
        }
    }

    private void createDialog(String text)                      //creatint UI for Dialog box which complete on ending current activity as well
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        isDetected = !isDetected;
        builder.setMessage(text)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        dialogInterface.dismiss();
                        finish();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }



}
