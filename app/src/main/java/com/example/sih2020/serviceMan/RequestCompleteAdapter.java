package com.example.sih2020.serviceMan;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sih2020.R;

public class RequestCompleteAdapter extends RecyclerView.Adapter<RequestCompleteAdapter.myHolder1> {




    public class myHolder extends RecyclerView.ViewHolder implements View.OnClickListener{


        TextView request_id , responsible_id ,  description , complain_id ;
        CardView cardview;
        LinearLayout ll_hide;

        public myHolder(@NonNull View itemView) {
            super(itemView);
            request_id = itemView.findViewById(R.id.s_RecyclerView_requestID__req_completed);
            responsible_id = itemView.findViewById(R.id.s_RecyclerView_ResponsibleMan_req_completed);
            description = itemView.findViewById(R.id.s_RecyclerView_Description_req_completed);
            complain_id = itemView.findViewById(R.id.s_RecyclerView_ComplainID_req_completed);


        }

        @Override
        public void onClick(View v) {

        }
    }

}
//-------------------------------


    public MyHolder1(@NonNull View itemView) {
        super(itemView);

        request_id = itemView.findViewById(R.id.s_RecyclerView_requestID__pen_req);
        responsible_id = itemView.findViewById(R.id.s_RecyclerView_ResponsibleMan_pen_req);
        description = itemView.findViewById(R.id.s_RecyclerView_Description_pen_req);
        complain_id = itemView.findViewById(R.id.s_RecyclerView_ComplainID_pen_req);
        cardview = itemView.findViewById(R.id.s_cardview_pen_req);
        ll_hide=  itemView.findViewById(R.id.s_ll_hide_pen_req);
        ll_hide.setVisibility(View.INVISIBLE);

        cardview.setOnClickListener(new View.OnClickListener() {                //Expandable card feature
            @Override
            public void onClick(View v) {

                if(ll_hide.getVisibility()==View.INVISIBLE)
                    ll_hide.setVisibility(View.VISIBLE);
                else
                    ll_hide.setVisibility(View.INVISIBLE);
            }
        });
    }