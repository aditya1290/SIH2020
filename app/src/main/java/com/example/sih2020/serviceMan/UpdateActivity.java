package com.example.sih2020.serviceMan;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sih2020.R;
import com.example.sih2020.model.Request;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UpdateActivity extends AppCompatActivity {


    FirebaseDatabase firebaseDatabase;
    DatabaseReference requestIdReference, requestReference, serviceManReference, responsibleManReference;

    FirebaseAuth auth;
    FirebaseUser user;

    EditText Submit_Description;
    RadioGroup radio_group;
    RadioButton radioButton;
    String status;
    Button submit_update;

    String requestIdValue;
    String generatorUid, complaintId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);


        Submit_Description = (EditText) findViewById(R.id.Submit_Description);
        radio_group = (RadioGroup) findViewById(R.id.radio_group);
        submit_update = (Button) findViewById(R.id.submit_update);

        generatorUid = getIntent().getStringExtra("generatorUid");
        complaintId = getIntent().getStringExtra("complaintId");

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        firebaseDatabase = FirebaseDatabase.getInstance();
        requestIdReference = firebaseDatabase.getReference("RequestId");
        requestReference = firebaseDatabase.getReference("Requests");
        serviceManReference = firebaseDatabase.getReference("Users").child("ServiceMan").child(user.getUid());
        responsibleManReference = firebaseDatabase.getReference("Users").child("ResponsibleMan").child(generatorUid);


        requestIdReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                requestIdValue = dataSnapshot.getValue().toString();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        submit_update.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                int selectedId = radio_group.getCheckedRadioButtonId();
                radioButton = (RadioButton) findViewById(selectedId);
                status = radioButton.getText().toString();

                Request request = new Request();
                request.setServiceMan(user.getUid());
                request.setDescription(Submit_Description.getText().toString());
                Log.i("status", status);
                if(status.equals("Pending"))
                    request.setStatus(false);
                else if(status.equals("Approved"))
                    request.setStatus(true);
                request.setResponsible(generatorUid);
                request.setComplaintId(complaintId);

                serviceManReference.child("pendingRequests").push().setValue(requestIdValue);
                responsibleManReference.child("pendingRequests").push().setValue(requestIdValue);

                requestReference.child(requestIdValue).setValue(request);
                requestIdReference.setValue(String.valueOf(Integer.parseInt(requestIdValue)+1));


            }
        });

        
    }
}
