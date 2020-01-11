package com.example.sih2020.serviceMan;

import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sih2020.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UpdateActivity extends AppCompatActivity {


    FirebaseDatabase firebaseDatabase;
    DatabaseReference RequestIdReference, RequestReference;

    FirebaseAuth auth;
    FirebaseUser user;

    EditText Submit_Description;
    RadioGroup radio_group;
    Button submit_update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);


        Submit_Description = (EditText) findViewById(R.id.Submit_Description);
        radio_group = (RadioGroup) findViewById(R.id.radio_group);
        submit_update = (Button) findViewById(R.id.submit_update);

        submit_update.setOnClickListener(new OnClickListener());

        
    }
}
