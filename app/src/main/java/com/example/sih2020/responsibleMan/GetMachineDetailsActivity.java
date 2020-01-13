package com.example.sih2020.responsibleMan;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.sih2020.R;
import com.example.sih2020.model.Complaint;
import com.example.sih2020.model.ComplaintDescriptionDialog;
import com.example.sih2020.model.Machine;
import com.example.sih2020.model.ServiceMan;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class GetMachineDetailsActivity extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference machineReference, complaintIdReference, serviceManListReference, responsibleReference,complaintReference;

    FirebaseAuth auth;
    FirebaseUser user;

    String generationCode;
    Machine machine;

    ImageView QRCodeImage;
    Button show_history;
    Button generateComplaint;

    TextView serialNo,department,serviceTime,dateOfInstallation;

    Complaint complaint;

    String complaintIdValue;
    String description;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_machine_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        generateComplaint = findViewById(R.id.generateComplaint);
        show_history = findViewById(R.id.show_history);

        description = new String("");

        serialNo = findViewById(R.id.machineDetailsSerialNo);
        department = findViewById(R.id.machineDetailsDepartment);
        serviceTime = findViewById(R.id.machineDetailsServiceTime);
        dateOfInstallation = findViewById(R.id.machineDetailsInstallationDate);

        generationCode = getIntent().getStringExtra("generationCode");

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        firebaseDatabase = FirebaseDatabase.getInstance();
        machineReference = firebaseDatabase.getReference("machines").child(generationCode);
        complaintIdReference = firebaseDatabase.getReference("complaintId");
        serviceManListReference = firebaseDatabase.getReference("Users").child("ServiceMan");
        responsibleReference = firebaseDatabase.getReference("Users").child("ResponsibleMan").child(user.getUid());
        complaintReference = firebaseDatabase.getReference("Complaints");

        QRCodeImage = findViewById(R.id.QrCodeImage);

        machineReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                machine = dataSnapshot.getValue(Machine.class);
                //Toast.makeText(GetMachineDetailsActivity.this, machine.getDepartment(), Toast.LENGTH_SHORT).show();

                serialNo.setText(machine.getSerialNumber());
                department.setText(machine.getDepartment());
                serviceTime.setText(machine.getServiceTime()+" months");
                dateOfInstallation.setText(machine.getDate());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        show_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(GetMachineDetailsActivity.this, ShowDetailsActivity.class);
                i.putExtra("generationCode",generationCode);
                startActivity(i);
            }
        });

        complaintIdReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                complaintIdValue = dataSnapshot.getValue().toString();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        generateComplaint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                complaint = new Complaint();
                complaint.setComplaintGenerator(user.getUid());
                complaint.setComplaintMachineId(generationCode);
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                month = month+1;
                int day = cal.get(Calendar.DAY_OF_MONTH);


                complaint.setComplaintGeneratedDate(day+"/"+month+"/"+year);
                complaint.setStatus(complaint.getGeneratedOnly());

                ComplaintDescriptionDialog complaintDescriptionDialog = new ComplaintDescriptionDialog(GetMachineDetailsActivity.this,complaint,complaintIdValue);
                complaintDescriptionDialog.show();

            }
        });

    }

}
