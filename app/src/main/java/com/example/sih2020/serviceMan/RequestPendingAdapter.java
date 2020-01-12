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

public class RequestPendingAdapter extends  RecyclerView.Adapter<RequestPendingAdapter.MyHolder1>{

    Context c;
    List x ;      //Define your list here    - Aditya

    public RequestPendingAdapter(Context c, List<  > x)                                               //Enter the type of data in the space for model
    {
        this.c = c;
        this.x = x;
    }

    @NonNull
    @Override
    public RequestPendingAdapter.MyHolder1 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.serviceman_pending_request_item,null);
        return new RequestPendingAdapter.MyHolder1(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RequestPendingAdapter.MyHolder1 myholder1, int position) {

        myholder1.request_id.setText("A");
        myholder1.responsible_id.setText("B");
        myholder1.description.setText("C");
        myholder1.complain_id.setText("D");

        boolean isExpanded = pendingComplaintList.get(position).isExpanded();
        myholder.ll_hide.setVisibility(isExpanded ? View.VISIBLE : View.GONE);

    }

    @Override
    public int getItemCount() {
        return 0;                                                                                   // Return item count from firebase
    }


    class MyHolder1 extends RecyclerView.ViewHolder{


        TextView request_id , responsible_id ,  description , complain_id ;
        CardView cardview;
        LinearLayout ll_hide;

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
    }

}
