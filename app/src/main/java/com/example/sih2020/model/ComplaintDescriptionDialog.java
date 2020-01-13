package com.example.sih2020.model;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.sih2020.R;

public class ComplaintDescriptionDialog extends Dialog implements
        android.view.View.OnClickListener {


    public Dialog d;
    String filename;

    EditText complaintDescription;
    TextView cancelButton, submitButton;

    LottieAnimationView animationView;

    public ComplaintDescriptionDialog(Activity a) {
        super(a);
        this.setCanceledOnTouchOutside(false);
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


    }

    @Override
    public void onClick(View v) {

    }
}
