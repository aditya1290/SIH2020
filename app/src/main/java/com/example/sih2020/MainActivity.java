package com.example.sih2020;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    Button scan;
    Button generate;
    FloatingActionButton assistant;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scan =(Button)findViewById(R.id.scan);
        generate = (Button)findViewById(R.id.generate);
        assistant = (FloatingActionButton)findViewById(R.id.assistant);


        scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ScanQRActivity.class);
                startActivity(i);
            }
        });

        generate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, GenerateQRActivity.class);
                startActivity(i);
            }
        });

        assistant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MessageActivity.class);
                intent.putExtra("userid", "25k9fXm5vBQRsGZRW2deJoyPAnY2");
                startActivity(intent);

            }
        });



    }
}
