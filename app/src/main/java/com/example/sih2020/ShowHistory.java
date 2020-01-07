package com.example.sih2020;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class ShowHistory extends AppCompatActivity {

    RecyclerView recyclerView;
    MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_history);

        recyclerView = findViewById(R.id.RecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        myAdapter = new MyAdapter(this, getlist());
        recyclerView.setAdapter(myAdapter);

    }


    private ArrayList<model> getlist()
    {
        ArrayList<model> models = new ArrayList<>();

        model m = new model();

        m.setDate("sdfgh");
        m.setComplaintId("dfghj");
        m.setAgentId("xhj");
        m.setDescription("cv");
        models.add(m);

        m.setDate("sdfgh");
        m.setComplaintId("dfghj");
        m.setAgentId("xhj");
        m.setDescription("cv");
        models.add(m);
        m.setDate("sdfgh");
        m.setComplaintId("dfghj");
        m.setAgentId("xhj");
        m.setDescription("cv");
        models.add(m);
        m.setDate("sdfgh");
        m.setComplaintId("dfghj");
        m.setAgentId("xhj");
        m.setDescription("cv");
        models.add(m);


        models.add(m);

        return models;
    }


}
