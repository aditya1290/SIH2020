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
import com.example.inventory.model.Request;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.List;

public class RMPendingRequestAdapter extends RecyclerView.Adapter<RMPendingRequestAdapter.MyHolder> {

    Context c;
    public List<Request> x;

    DatabaseReference serviceMan;


    public RMPendingRequestAdapter(Context c, List<Request> x) {
        this.c = c;
        this.x = x;
    }

    public RMPendingRequestAdapter(Context c) {
        this.c = c;
    }

    public void setx(List<Request> x) {
        this.x = x;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rm_pending_request_item, null);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyHolder myholder, final int position) {


        myholder.serviceman1.setText(x.get(position).getServicemanName());
        myholder.requestid1.setText(x.get(position).getRequestid());
        myholder.complain_id.setText(x.get(position).getComplaintId());
        myholder.description.setText(x.get(position).getDescription());

        myholder.accept_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            if(x.get(position).isStatus())
            {
                HashMap<String,Object> updateDatabaseValue = new HashMap<>();

                //add data
                updateDatabaseValue.put("/Users/ResponsibleMan/"+x.get(position).getResponsible()+"/completedComplaintList/"+x.get(position).getComplaintId(),"true");
                updateDatabaseValue.put("/Users/ServiceMan/"+x.get(position).getServiceMan()+"/completedComplaintList/"+x.get(position).getComplaintId(),"true");
                updateDatabaseValue.put("/Users/ServiceMan/"+x.get(position).getServiceMan()+"/completedRequestList/"+x.get(position).getRequestid(),"true");

                // delete data
                updateDatabaseValue.put("/Users/ResponsibleMan/"+x.get(position).getResponsible()+"/pendingComplaintList/"+x.get(position).getComplaintId(),null);
                updateDatabaseValue.put("/Users/ResponsibleMan/"+x.get(position).getResponsible()+"/pendingRequestList/"+x.get(position).getRequestid(),null);
                updateDatabaseValue.put("/Users/ServiceMan/"+x.get(position).getServiceMan()+"/pendingComplaintList/"+x.get(position).getComplaintId(),null);
                updateDatabaseValue.put("/Users/ServiceMan/"+x.get(position).getServiceMan()+"/pendingRequestList/"+x.get(position).getRequestid(),null);

                FirebaseDatabase.getInstance().getReference().updateChildren(updateDatabaseValue);

            }
            }
        });

    }

    @Override
    public int getItemCount() {
        Log.i("size",String.valueOf(x.size()));
        return x.size();
    }


    class MyHolder extends RecyclerView.ViewHolder {


        TextView serviceman1,requestid1,complain_id,description,accept_button,decline_button;

        public MyHolder(@NonNull final View itemView) {
            super(itemView);

            requestid1 = itemView.findViewById(R.id.request_id1);
            serviceman1 = itemView.findViewById(R.id.serviceman1);
            complain_id = itemView.findViewById(R.id.complain_id);
            description = itemView.findViewById(R.id.description);
            accept_button = itemView.findViewById(R.id.accept_button);
            decline_button = itemView.findViewById(R.id.decline_button);

        }

    }

}