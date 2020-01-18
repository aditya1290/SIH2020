package com.example.inventory.responsibleMan.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inventory.R;
import com.example.inventory.model.Complaint;

import java.util.List;

public class RMCompletedComplaintAdapter extends  RecyclerView.Adapter<RMCompletedComplaintAdapter.MyHolder1>{

        Context c;
        List<Complaint> x ;

public RMCompletedComplaintAdapter(Context c, List<Complaint> x)                                               //Enter the type of data in the space for model
        {
        this.c = c;
        this.x = x;
        }

@NonNull
@Override
public RMCompletedComplaintAdapter.MyHolder1 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rm_completed_complaint_item,null);
        return new RMCompletedComplaintAdapter.MyHolder1(view);
        }

@Override
public void onBindViewHolder(@NonNull RMCompletedComplaintAdapter.MyHolder1 myholder1, int position) {


        myholder1.pendingComplaintDate.setText(x.get(position).getComplaintCompletedDate());
        myholder1.pendingComplaintDescription.setText(x.get(position).getComplaintDescription());
        myholder1.pendingComplaintServicemanName.setText(x.get(position).getServicemanName());
        myholder1.pendingComplaintId.setText(x.get(position).getComplaintId());
        myholder1.pendingComplaintMachineId.setText(x.get(position).getComplaintMachineId());


        Log.i("asdf","fgh");


        }

@Override
public int getItemCount() {
        return x.size();                                                                                   // Return item count from firebase
        }


class MyHolder1 extends RecyclerView.ViewHolder{

    TextView pendingComplaintDate, pendingComplaintId, pendingComplaintServicemanName, pendingComplaintDescription, pendingComplaintMachineId;

    public MyHolder1(@NonNull View itemView) {
        super(itemView);

        pendingComplaintDate = itemView.findViewById(R.id.rm_complated_complaint_date);
        pendingComplaintId = itemView.findViewById(R.id.rm_complated_complaint_id);
        pendingComplaintDescription = itemView.findViewById(R.id.rm_complated_complaint_desc);
        pendingComplaintServicemanName = itemView.findViewById(R.id.rm_complated_complaint_serviceman);
        pendingComplaintMachineId = itemView.findViewById(R.id.rm_complated_complaint_machine_id);


    }
}

}