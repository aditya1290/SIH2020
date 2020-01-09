package com.example.sih2020;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.net.URL;

public class GetMachineDetails extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference machineReference;

    String generationCode;
    Machine machine;

    ImageView QRCodeImage;
    Button show_history;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_machine_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        show_history = findViewById(R.id.show_history);

        generationCode = getIntent().getStringExtra("generationCode");

        firebaseDatabase = FirebaseDatabase.getInstance();
        machineReference = firebaseDatabase.getReference("machines").child(generationCode);

        QRCodeImage = findViewById(R.id.QrCodeImage);

        machineReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                machine = dataSnapshot.getValue(Machine.class);
                Toast.makeText(GetMachineDetails.this, machine.getDepartment(), Toast.LENGTH_SHORT).show();
                Picasso.get().load(machine.getLink()).into(QRCodeImage);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        show_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(GetMachineDetails.this, ShowHistory.class);
                i.putExtra("generationCode",generationCode);
                startActivity(i);
            }
        });

    }
}
