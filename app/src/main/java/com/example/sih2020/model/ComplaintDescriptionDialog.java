package com.example.sih2020.model;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.airbnb.lottie.LottieAnimationView;
import com.example.sih2020.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ComplaintDescriptionDialog extends Dialog implements
        android.view.View.OnClickListener {


    public Dialog d;
    String filename;

    EditText complaintDescription;
    TextView cancelButton, submitButton;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference machineReference, complaintIdReference, serviceManListReference, responsibleReference,complaintReference;

    FirebaseAuth auth;
    FirebaseUser user;


    Complaint complaint;

    HashMap<String,Integer> serviceManList;

    String complaintIdValue;

    LottieAnimationView animationView;

    public ComplaintDescriptionDialog(Activity a,Complaint complaint,String complaintIdValue) {
        super(a);
        this.complaint = complaint;
        this.setCanceledOnTouchOutside(false);
        this.complaintIdValue = complaintIdValue;
        // TODO Auto-generated constructor stub

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.generate_complaint_description_dialog);

        complaintDescription = findViewById(R.id.complaintDescription);
        cancelButton = findViewById(R.id.cancelButton);
        submitButton = findViewById(R.id.submitButton);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        firebaseDatabase = FirebaseDatabase.getInstance();
        complaintIdReference = firebaseDatabase.getReference("complaintId");
        serviceManListReference = firebaseDatabase.getReference("Users").child("ServiceMan");
        responsibleReference = firebaseDatabase.getReference("Users").child("ResponsibleMan").child(user.getUid());
        complaintReference = firebaseDatabase.getReference("Complaints");

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
                        complaint.setComplaintDescription(complaintDescription.getText().toString());
                        serviceManListReference.child(entry.getKey()).child("load").setValue(entry.getValue()+1);
                        serviceManListReference.child(entry.getKey()).child("pendingComplaintList").push().setValue(complaintIdValue);

                        serviceManListReference.removeEventListener(this);
                        responsibleReference.child("pendingComplaints").push().setValue(complaintIdValue);
                        complaintReference.child(complaintIdValue).setValue(complaint);
                        complaintIdReference.setValue(String.valueOf(Integer.parseInt(complaintIdValue)+1));

                        dismiss();

                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }


                });

            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
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

    @Override
    public void onClick(View v) {

    }
}
