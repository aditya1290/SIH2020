package com.example.sih2020.serviceMan;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sih2020.R;
import com.example.sih2020.model.Complaint;

import java.util.List;

public class PendingComplaintAdapter extends RecyclerView.Adapter<PendingComplaintAdapter.MyHolder> {

    Context c;
    public List<Complaint> pendingComplaintList;

    public PendingComplaintAdapter(Context c, List<Complaint> pendingComplaint) {
        this.c = c;
        this.pendingComplaintList = pendingComplaint;
    }

    public PendingComplaintAdapter(Context c) {
        this.c = c;
    }

    public void setPendingComplaintList(List<Complaint> pendingComplaintList) {
        this.pendingComplaintList = pendingComplaintList;
    }

    @NonNull
    @Override
    public PendingComplaintAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pending_complaint_item, null);
        return new PendingComplaintAdapter.MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PendingComplaintAdapter.MyHolder myholder, int position) {

        myholder.pendingComplaintDate.setText("11/01/2020");
        myholder.pendingComplaintDescription.setText(pendingComplaintList.get(position).getComplaintDescription());
        myholder.pendingComplaintGeneratorName.setText(pendingComplaintList.get(position).getComplaintGenerator());
        //myholder.pendingComplaintId.setText(pendingComplaintList.get(position));

        boolean isExpanded = pendingComplaintList.get(position).isExpanded();
        myholder.ll_hide.setVisibility(isExpanded ? View.VISIBLE : View.GONE);
    }

    @Override
    public int getItemCount() {
        return pendingComplaintList.size();
    }







    class MyHolder extends RecyclerView.ViewHolder
    {
        TextView pendingComplaintDate, pendingComplaintId, pendingComplaintGeneratorName, pendingComplaintDescription;
        CardView cardView;
        LinearLayout ll_hide;

        public MyHolder(@NonNull final View itemView)
        {
            super(itemView);

            pendingComplaintDate = itemView.findViewById(R.id.pending_request_date);
            pendingComplaintId = itemView.findViewById(R.id.pending_request_complaintId);
            pendingComplaintDescription = itemView.findViewById(R.id.pending_request_description);
            pendingComplaintGeneratorName = itemView.findViewById(R.id.pending_request_genratorName);

            cardView = itemView.findViewById(R.id.cardview);
            ll_hide = itemView.findViewById(R.id.ll_hide);

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Complaint complaint = pendingComplaintList.get(getAdapterPosition());
//                    complaint.setExpanded(!complaint.isExpanded());
//                    notifyItemChanged(getAdapterPosition());

                    Intent i = new Intent(c, UpdateActivity.class);
                    i.putExtra("generatorUid",complaint.getComplaintGenerator());
                    i.putExtra("complaintId",complaint.getComplaintId());
                    c.startActivity(i);

                }
            });
        }

    }
}
