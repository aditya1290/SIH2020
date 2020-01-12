package com.example.sih2020.serviceMan.fragments;


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
public class RequestCompletedFragment extends Fragment {


    public RequestCompletedFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View rootview =  inflater.inflate(R.layout.serviceman_request_completed, container, false);

        RecyclerView s_recyclerView_completed_request = (RecyclerView)rootview.findViewById(R.id.s_recyclerView_completed_request);
        s_recyclerView_completed_request.setLayoutManager(new LinearLayoutManager(getActivity()));






        return rootview;
    }

}
