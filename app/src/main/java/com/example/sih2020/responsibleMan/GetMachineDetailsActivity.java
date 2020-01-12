package com.example.sih2020.responsibleMan;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.sih2020.R;
import com.example.sih2020.model.Complaint;
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

    Complaint complaint;

    String complaintIdValue;

    Button generateComplaint;
    HashMap<String,Integer> serviceManList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_machine_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        generateComplaint = findViewById(R.id.generateComplaint);
        show_history = findViewById(R.id.show_history);

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
                Toast.makeText(GetMachineDetailsActivity.this, machine.getDepartment(), Toast.LENGTH_SHORT).show();
                Picasso.get().load(machine.getLink()).into(QRCodeImage);
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
                complaint.setComplaintGeneratedDate(new Date(2019,1,11));
                complaint.setStatus(complaint.getGeneratedOnly());
                serviceManList = new HashMap<>();

                serviceManListReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        for(DataSnapshot serviceManReference : dataSnapshot.getChildren())
                        {

                            String key = serviceManReference.getKey();

                            Log.i("serviceMan key",key);

                            ServiceMan serviceMan = new ServiceMan();
                            String email = serviceManReference.child("email").getValue().toString();
                            String userName = serviceManReference.child("userName").getValue().toString();
                            String load = serviceManReference.child("load").getValue().toString();

                            serviceMan.setEmail(email);
                            serviceMan.setUserName(userName);
                            serviceMan.setLoad(Integer.parseInt(load));

                            Log.i("serviceMan username",serviceMan.getUserName());
                            Log.i("serviceMan load",String.valueOf(serviceMan.getLoad()));
                            serviceManList.put(key,serviceMan.getLoad());

                        }

                        serviceManList = sortByValue(serviceManList);
                        Map.Entry<String,Integer> entry = serviceManList.entrySet().iterator().next();

                        complaint.setComplaintAllocatedTo(entry.getKey());
                        complaint.setComplaintId(complaintIdValue);
                        complaint.setStatus(complaint.getGeneratedAndAccpted());
                        serviceManListReference.child(entry.getKey()).child("load").setValue(entry.getValue()+1);
                        serviceManListReference.child(entry.getKey()).child("pendingComplaintList").push().setValue(complaintIdValue);

                        serviceManListReference.removeEventListener(this);
                        responsibleReference.child("pendingComplaints").push().setValue(complaintIdValue);
                        complaintReference.child(complaintIdValue).setValue(complaint);
                        complaintIdReference.setValue(String.valueOf(Integer.parseInt(complaintIdValue)+1));

                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }


                });

            }
        });

    }
    public static HashMap<String, Integer> sortByValue(HashMap<String, Integer> hm)
    {
        // Create a list from elements of HashMap
        List<Map.Entry<String, Integer> > list =
                new LinkedList<Map.Entry<String, Integer> >(hm.entrySet());

        // Sort the list
        Collections.sort(list, new Comparator<Map.Entry<String, Integer> >() {
            public int compare(Map.Entry<String, Integer> o1,
                               Map.Entry<String, Integer> o2)
            {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });

        // put data from sorted list to hashmap
        HashMap<String, Integer> temp = new LinkedHashMap<String, Integer>();
        for (Map.Entry<String, Integer> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }
}
