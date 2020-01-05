package com.example.sih2020;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;

public class GenerateQRActivity extends AppCompatActivity {

    EditText input;
    ImageView qrcode;
    ImageView image;
    Button GenerateQR;
    String text;
    Button save;
    OutputStream outputStream;

    private TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_qr);

        GenerateQR = (Button)findViewById(R.id.generateQRButton);
        input = (EditText)findViewById(R.id.serialnumber);
        qrcode = (ImageView)findViewById(R.id.qrcode);
        save = (Button)findViewById(R.id.save);


        mDisplayDate = (TextView) findViewById(R.id.installationdate);

        //Permission
        if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
        {
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
        }
        if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
        {
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},1);
        }

        GenerateQR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text = input.getText().toString().trim();
                MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
                try{
                    BitMatrix bitMatrix = multiFormatWriter.encode(text, BarcodeFormat.QR_CODE,200,200);
                    BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                    Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
                    qrcode.setImageBitmap(bitmap);

                }catch(WriterException e)
                {
                    e.printStackTrace();
                }
            }
        });

        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        GenerateQRActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                String date = month + "/" + day + "/" + year;
                mDisplayDate.setText(date);
            }
        };

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveImage();
            }
        });
    }

    private void saveImage() {

        ImageView img = (ImageView) findViewById(R.id.qrcode);
        BitmapDrawable draw = (BitmapDrawable) img.getDrawable();
        Bitmap bitmap = draw.getBitmap();
        FileOutputStream outStream = null;
        File sdCard = Environment.getExternalStorageDirectory();
        File dir = new File(sdCard.getAbsolutePath() + "/QRcode");
        dir.mkdirs();
        File outFile = new File(dir, text+".jpg");
        try {
            outStream = new FileOutputStream(outFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outStream);
        Toast.makeText(this, "Image Saved Successfully", Toast.LENGTH_SHORT).show();
        try {
            outStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            outStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}























// Some extra code to use further
// 1. Dialog box for QR code
//Starting of dialog box
/*
    public void myAlertbox()
    {
        MyDialog = new Dialog(GenerateQRActivity.this);
        MyDialog.setContentView(R.layout.qrcode_dialog);
        MyDialog.setTitle("QR CODE");
        save = (Button)findViewById(R.id.dialogSave);
        exit = (Button)findViewById(R.id.dialogExit);
        ImageinDialog = (ImageView)findViewById(R.id.ImageInDialog);
        ImageinDialog.setImageDrawable(getResources().getDrawable(R.drawable.bhj));

        save.setEnabled(true);
        exit.setEnabled(true);

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDialog.cancel();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(GenerateQRActivity.this, "Save your image here", Toast.LENGTH_SHORT).show();
            }
        });

    }
    *///------>Ending



