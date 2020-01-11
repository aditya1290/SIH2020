package com.example.sih2020;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.sih2020.model.Complaint;
import com.example.sih2020.serviceMan.ServiceMan;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.Date;
import java.util.HashMap;

public class GetMachineDetails extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference machineReference, complaintId, serviceManListReference;

    FirebaseAuth auth;
    FirebaseUser user;

    String generationCode;
    Machine machine;

    ImageView QRCodeImage;
    Button show_history;

    Complaint complaint;

    String complaintIdValue;

    Button generateComplaint;
    HashMap<String,ServiceMan> serviceManList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_machine_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        generateComplaint = findViewById(R.id.generateComplaint);
        show_history = findViewById(R.id.show_history);

        generationCode = getIntent().getStringExtra("generationCode");

        firebaseDatabase = FirebaseDatabase.getInstance();
        machineReference = firebaseDatabase.getReference("machines").child(generationCode);
        complaintId = firebaseDatabase.getReference("complaintId");
        serviceManListReference = firebaseDatabase.getReference("Users").child("ServiceMan");

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

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

        generateComplaint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                complaintId.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        complaintIdValue = dataSnapshot.getValue().toString();
                        complaint = new Complaint();
                        complaint.setComplaintGenerator(user.getUid());
                        complaint.setComplaintMachineId(generationCode);
                        complaint.setComplaintGeneratedDate(new Date(2019,1,11));
                        complaint.setStatus(complaint.getGeneratedOnly());


                        serviceManListReference.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                for(DataSnapshot serviceMan : dataSnapshot.getChildren())
                                {
                                    String key = serviceMan.getKey();


                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        })  ;



                    }




                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });

    }
}
