package com.example.sih2020.serviceMan;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

public class PendingComplaints extends AppCompatActivity {

    RecyclerView recyclerView_complaints;
    PendingComplaintAdapter myPendingComplaintAdapter;

    List<String> pendingComplaintList;

    List<Complaint> pendingComplaintObjectList;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference serviceManReference, pendingComplaintListReference, complaintReference;

    FirebaseAuth auth;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pending_complaints);

        recyclerView_complaints = findViewById(R.id.recyclerView_complaints);
        recyclerView_complaints.setLayoutManager(new LinearLayoutManager(this));



        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        pendingComplaintList = new ArrayList<String>();
        pendingComplaintObjectList = new ArrayList<Complaint>();

        myPendingComplaintAdapter = new PendingComplaintAdapter(getApplicationContext(),pendingComplaintObjectList);
        recyclerView_complaints.setAdapter(myPendingComplaintAdapter);

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
                            myPendingComplaintAdapter.notifyDataSetChanged();
                            Log.i("machine id", complaint.getComplaintMachineId());
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                }


                Log.i("ComplaintSize",String.valueOf(pendingComplaintObjectList.size()));



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }



}
