package com.example.sih2020;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.sih2020.responsibleMan.BottomNavigationActivity;
import com.example.sih2020.responsibleMan.GenerateQRActivity;

public class MainActivity extends AppCompatActivity {

    CardView scan;
    CardView generate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        scan = findViewById(R.id.scan);
        generate = (CardView) findViewById(R.id.generate);


        scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, BottomNavigationActivity.class);
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

    }
}
