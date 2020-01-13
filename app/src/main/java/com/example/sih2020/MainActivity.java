package com.example.sih2020;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.airbnb.lottie.LottieAnimationView;
import com.example.sih2020.responsibleMan.BottomNavigationActivity;
import com.example.sih2020.responsibleMan.GenerateQRActivity;

public class MainActivity extends AppCompatActivity {

    CardView scan;
    CardView generate;
    LottieAnimationView animationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //animationView.playAnimation();




    }
}
