package com.example.sih2020;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ShowHistory extends AppCompatActivity {

    RecyclerView recyclerView;
    MyAdapter myAdapter;

    String generationCode;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference historyReference;
    List<PastRecord> pastRecords;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_history);

        generationCode = getIntent().getStringExtra("generationCode");

        firebaseDatabase = FirebaseDatabase.getInstance();
        historyReference = firebaseDatabase.getReference("machines").child(generationCode).child("pastRecordList");


        recyclerView = findViewById(R.id.RecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        myAdapter = new MyAdapter(this, getlist());
        recyclerView.setAdapter(myAdapter);


    }


    private List<PastRecord> getlist()
    {
        pastRecords = new ArrayList<>();

        historyReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot pastRecord : dataSnapshot.getChildren())
                {
                    PastRecord m = new PastRecord();
                    m = pastRecord.getValue(PastRecord.class);
                    String desc = m.getDescription();
                    Log.i("pastRecord",desc);
                    pastRecords.add(m);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return pastRecords;
    }


}
