package com.example.sih2020.serviceMan;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sih2020.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class RequestPending extends Fragment {


    RecyclerView s_recyclerView_pending_request;
    RequestPendingAdapter myRequestPendingAdapter;

    public RequestPending() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        s_recyclerView_pending_request = container.findViewById(R.id.s_recyclerView_pending_request);
        s_recyclerView_pending_request.setLayoutManager(new LinearLayoutManager(this));


        myRequestPendingAdapter = new RequestPendingAdapter(this, getmylist());
        s_recyclerView_pending_request.setAdapter(myRequestPendingAdapter);

        return inflater.inflate(R.layout.serviceman_request_pending, container, false);
    }

}

