package com.example.sih2020.serviceMan;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sih2020.R;
import com.example.sih2020.model.Complaint;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class PendingComplaintActivity extends AppCompatActivity {

    List<String> pendingComplaintList;

    List<Complaint> pendingComplaintObjectList;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference serviceManReference, pendingComplaintListReference, complaintReference;

    FirebaseAuth auth;
    FirebaseUser user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pending_complaint);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        pendingComplaintList = new ArrayList<String>();
        pendingComplaintObjectList = new ArrayList<Complaint>();

        firebaseDatabase = FirebaseDatabase.getInstance();
        serviceManReference = firebaseDatabase.getReference("Users").child("ServiceMan").child(user.getUid());
        pendingComplaintListReference = serviceManReference.child("pendingComplaint");
        complaintReference = firebaseDatabase.getReference("Complaints");

        pendingComplaintListReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot pendingComplaintReference : dataSnapshot.getChildren())
                {
                    String key = pendingComplaintReference.getValue().toString();
                    //Log.i("key", key);

                    pendingComplaintList.add(key);

                    complaintReference.child(key).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            Complaint complaint = new Complaint();
                            complaint = dataSnapshot.getValue(Complaint.class);
                            pendingComplaintObjectList.add(complaint);
                            //Log.i("machine id", complaint.getComplaintMachineId());
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                }




            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}
