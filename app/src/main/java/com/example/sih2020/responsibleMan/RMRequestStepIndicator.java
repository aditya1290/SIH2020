package com.example.sih2020.responsibleMan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.sih2020.R;
import com.transferwise.sequencelayout.SequenceStep;

public class RMRequestStepIndicator extends AppCompatActivity {

    SequenceStep sequenceStep1,sequenceStep2,sequenceStep3,sequenceStep4,sequenceStep5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rmrequest_step_indicator);
        sequenceStep1=findViewById(R.id.step1);
        sequenceStep2=findViewById(R.id.step2);
        sequenceStep3=findViewById(R.id.step3);
        sequenceStep4=findViewById(R.id.step4);
        sequenceStep5=findViewById(R.id.step5);


        sequenceStep4.setSubtitle(R.string.agent_done);
        sequenceStep3.setPadding(0,4,0,4);
       
        sequenceStep4.setTitleTextAppearance(R.style.Title);

    }
}
