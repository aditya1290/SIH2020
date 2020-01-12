package com.example.sih2020.serviceMan;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sih2020.R;
import com.example.sih2020.model.Request;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class PendingRequestActivity extends AppCompatActivity {

    RecyclerView recyclerView_Requests;
    RequestPendingAdapter myPendingRequestAdapter;

    List<Request> pendingRequestObjectList;
    List<String> pendingRequestList;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference requestReference, serviceManReference, pendingRequestListReference;

    FirebaseAuth auth;
    FirebaseUser user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pending_request);

        recyclerView_Requests = findViewById(R.id.s_recyclerView_pending_request1);
        recyclerView_Requests.setLayoutManager(new LinearLayoutManager(this));

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        pendingRequestList = new ArrayList<String>();
        Request request = new Request();
        request.setComplaintId("hjdjkhk");
        pendingRequestObjectList = new ArrayList<Request>();

        myPendingRequestAdapter = new RequestPendingAdapter(getApplicationContext(), pendingRequestObjectList);
        recyclerView_Requests.setAdapter(myPendingRequestAdapter);


        firebaseDatabase =  FirebaseDatabase.getInstance();
        serviceManReference = firebaseDatabase.getReference("Users").child("ServiceMan").child(user.getUid());
        pendingRequestListReference = serviceManReference.child("pendingRequests");
        requestReference = firebaseDatabase.getReference("Requests");


        pendingRequestListReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                String key = dataSnapshot.getValue().toString();

                requestReference.child(key).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Request request = new Request();
                        request = dataSnapshot.getValue(Request.class);

                        pendingRequestObjectList.add(request);
                        myPendingRequestAdapter.notifyDataSetChanged();
                        Log.i("danda ghus gya",request.getComplaintId());
                        //Log.i("machine id", request.getComplaintMachineId());
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




    }
}
