package com.example.sih2020;

import android.content.Context;
import android.media.audiofx.AudioEffect;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder> {

    Context c;
    public List<PastRecord> pastRecords;


    public MyAdapter(Context c, List<PastRecord> pastRecords) {
        this.c = c;
        this.pastRecords = pastRecords;
    }

    public MyAdapter(Context c) {
        this.c = c;
    }

    public void setPastRecords(List<PastRecord> pastRecords) {
        this.pastRecords = pastRecords;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, null);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myholder, int position) {

        myholder.date.setText(pastRecords.get(position).getServiceDate().toString());
        myholder.AgentId.setText(pastRecords.get(position).getServiceMan());
        myholder.Description.setText(pastRecords.get(position).getDescription());
        myholder.CompliantId.setText("random");

        boolean isExpanded = pastRecords.get(position).isExpanded();
        myholder.ll_hide.setVisibility(isExpanded ? View.VISIBLE : View.GONE);
    }

    @Override
    public int getItemCount() {
        return pastRecords.size();
    }


    class MyHolder extends RecyclerView.ViewHolder {


        TextView date;
        CardView cardview;
        TextView CompliantId;
        TextView AgentId;
        TextView Description;
        LinearLayout ll_hide;

        public MyHolder(@NonNull final View itemView) {
            super(itemView);

            date = itemView.findViewById(R.id.RecyclerView_Date);
            CompliantId = itemView.findViewById(R.id.RecyclerView_ComplaintId);
            AgentId = itemView.findViewById(R.id.RecyclerView_AgentId);
            Description = itemView.findViewById(R.id.RecyclerView_Description);
            ll_hide = itemView.findViewById(R.id.ll_hide);
            cardview = itemView.findViewById(R.id.cardview);

            cardview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    PastRecord past = pastRecords.get(getAdapterPosition());
                    past.setExpanded(!past.isExpanded());
                    notifyItemChanged(getAdapterPosition());

                }
            });

        }


    }





}