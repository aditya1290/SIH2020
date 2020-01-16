package com.example.sih2020.serviceMan.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sih2020.R;
import com.example.sih2020.model.Complaint;

import java.util.List;

public class HisoryAdapter extends RecyclerView.Adapter<HisoryAdapter.MyHolder> {

    Context c;
    List<Complaint> x;

    public HisoryAdapter(Context c, List<Complaint> x) {
        this.c = c;
        this.x = x;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rm_pending_complaint_item, null);
        return new HisoryAdapter.MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myholder1, int position) {

        myholder1.pendingComplaintDate.setText(x.get(position).getComplaintGeneratedDate());
        myholder1.pendingComplaintDescription.setText(x.get(position).getComplaintDescription());
        myholder1.pendingComplaintServicemanName.setText(x.get(position).getServicemanName());
        myholder1.pendingComplaintId.setText(x.get(position).getComplaintId());
        myholder1.pendingComplaintMachineId.setText(x.get(position).getComplaintMachineId());

    }

    @Override
    public int getItemCount() {
        return x.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder{

        TextView pendingComplaintDate, pendingComplaintId, pendingComplaintServicemanName, pendingComplaintDescription, pendingComplaintMachineId;

        public MyHolder(@NonNull View itemView) {
            super(itemView);

            pendingComplaintDate = itemView.findViewById(R.id.rm_pending_complaint_date);
            pendingComplaintId = itemView.findViewById(R.id.rm_pending_complaint_id);
            pendingComplaintDescription = itemView.findViewById(R.id.rm_pending_complaint_desc);
            pendingComplaintServicemanName = itemView.findViewById(R.id.rm_pending_complaint_serviceman);
            pendingComplaintMachineId = itemView.findViewById(R.id.rm_pending_complaint_machine_id);


        }

    }
}
