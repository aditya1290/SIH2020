package com.example.inventory.responsibleMan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.inventory.R;
import com.example.inventory.responsibleMan.adapters.RMachineAdapter;
import com.example.inventory.serviceMan.adapters.PendingComplaintAdapter;

public class MyMachine extends AppCompatActivity {

    RecyclerView recyclerView_machine;
    RMachineAdapter rMachineAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_machine);

        recyclerView_machine=findViewById(R.id.recyclerView_machine);
        recyclerView_machine.setLayoutManager(new LinearLayoutManager(this));
    }
}
