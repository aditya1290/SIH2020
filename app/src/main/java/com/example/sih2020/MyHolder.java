package com.example.sih2020;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyHolder extends RecyclerView.ViewHolder {


    TextView date;
    TextView CompliantId;
    TextView AgentId;
    TextView Description;


    public MyHolder(@NonNull View itemView) {
        super(itemView);

        this.date = itemView.findViewById(R.id.RecyclerView_Date);
        this.CompliantId = itemView.findViewById(R.id.RecyclerView_ComplaintId);
        this.AgentId = itemView.findViewById(R.id.RecyclerView_AgentId);
        this.Description = itemView.findViewById(R.id.RecyclerView_Description);

    }
}
