package com.example.sih2020.serviceMan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sih2020.R;

import java.util.List;

public class RequestCompleteAdapter extends RecyclerView.Adapter<RequestCompleteAdapter.myHolder> {

    Context c;
    List x;

    public RequestCompleteAdapter(Context c, List < > x)
    {
        this.c = c;
        this.x = x;
    }


    @NonNull
    @Override
    public RequestCompleteAdapter.myHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.serviceman_request_completed_item,null);
        return new RequestCompleteAdapter.myHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RequestCompleteAdapter.myHolder holder, int position) {

        holder.request_id.setText("A");
        holder.responsible_id.setText("B");
        holder.description.setText("C");
        holder.complain_id.setText("D");

        boolean isExpanded = pendingComplaintList.get(position).isExpanded();
        holder.ll_hide.setVisibility(isExpanded ? View.VISIBLE : View.GONE);
    }

    @Override
    public int getItemCount() {
        return 0;
    }


    public class myHolder extends RecyclerView.ViewHolder{

        TextView request_id , responsible_id ,  description , complain_id ;
        CardView cardview;
        LinearLayout ll_hide;

        public myHolder(@NonNull View itemView) {
            super(itemView);
            request_id = itemView.findViewById(R.id.s_RecyclerView_requestID__req_completed);
            responsible_id = itemView.findViewById(R.id.s_RecyclerView_ResponsibleMan_req_completed);
            description = itemView.findViewById(R.id.s_RecyclerView_Description_req_completed);
            complain_id = itemView.findViewById(R.id.s_RecyclerView_ComplainID_req_completed);
            cardview = itemView.findViewById(R.id.s_cardview_req_completed);
            ll_hide = itemView.findViewById(R.id.s_ll_hide_req_completed);
            ll_hide.setVisibility(View.INVISIBLE);


            cardview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(ll_hide.getVisibility()==View.INVISIBLE)
                        ll_hide.setVisibility(View.VISIBLE);
                    else
                        ll_hide.setVisibility(View.INVISIBLE);

                }
            });
        }
    }

}
/