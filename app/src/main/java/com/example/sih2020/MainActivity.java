package com.example.sih2020;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button scan;
    Button generate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scan =(Button)findViewById(R.id.scan);
        generate = (Button)findViewById(R.id.generate);

        startActivity(new Intent(MainActivity.this,GenerateQRActivity.class));

        scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent i = new Intent(MainActivity.this, ScanQRActivity.class);
//                startActivity(i);
            }
        });

        generate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent i = new Intent(MainActivity.this, GenerateQRActivity.class);
//                startActivity(i);
            }
        });



    }
}
