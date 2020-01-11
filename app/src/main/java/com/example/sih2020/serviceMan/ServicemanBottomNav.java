package com.example.sih2020.serviceMan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.sih2020.History_fragment;
import com.example.sih2020.Home_fragment;
import com.example.sih2020.Notification_fragment;
import com.example.sih2020.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ServicemanBottomNav extends AppCompatActivity {
    private ServicemanHistoryfragment servicemanHistoryfragment;
    private ServicemanHomefragment servicemanHomefragment;
    private ServicemanNotificationfragment servicemanNotificationfragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serviceman_bottom_nav);
        BottomNavigationView bottomNavigationView = findViewById(R.id.s_bottom_bar);
        servicemanHomefragment = new ServicemanHomefragment();
        servicemanHistoryfragment = new ServicemanHistoryfragment();
        servicemanNotificationfragment= new ServicemanNotificationfragment();
        bottomNavigationView.setItemIconTintList(null);
        setOurFragment(servicemanHomefragment);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.home:
                        setOurFragment(servicemanHomefragment);
                        return true;

                    case R.id.history:
                        setOurFragment(servicemanHistoryfragment);
                        return true;

                    case R.id.notification:
                        setOurFragment(servicemanNotificationfragment);
                        return true;

                    default:
                        return false;
                }

            }
        });
    }
    private void setOurFragment(Fragment fragment)
    {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.s_mainframe,fragment);
        fragmentTransaction.commit();
    }
}
