package com.example.sih2020;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyHolder> {

    Context c;
    List<PastRecord> pastRecords;

    public MyAdapter(Context c, List<PastRecord> pastRecords) {
        this.c = c;
        this.pastRecords = pastRecords;
    }


    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row,null);


        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myholder, int position) {

        myholder.date.setText(pastRecords.get(position).getServiceDate().toString());
        myholder.AgentId.setText(pastRecords.get(position).getServiceMan());
        myholder.Description.setText(pastRecords.get(position).getDescription());
        myholder.CompliantId.setText("random");

    }

    @Override
    public int getItemCount() {
        return pastRecords.size();
    }
}
