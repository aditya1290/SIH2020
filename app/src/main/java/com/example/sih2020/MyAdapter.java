package com.example.sih2020;

import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyHolder> {

    Context c;
    ArrayList<model> models;

    public MyAdapter(Context c, ArrayList<model> models) {
        this.c = c;
        this.models = models;
    }


    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row,null);


        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myholder, int position) {

        myholder.date.setText(models.get(position).getDate());
        myholder.AgentId.setText(models.get(position).getAgentId());
        myholder.Description.setText(models.get(position).getDescription());
        myholder.CompliantId.setText(models.get(position).getComplaintId());

    }

    @Override
    public int getItemCount() {
        return models.size();
    }
}
