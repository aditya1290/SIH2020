package com.example.sih2020;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Bottom_Navigation extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private Home_fragment homeFragment;
    private History_fragment historyFragment;
    private Notification_fragment notificationFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom__navigation);
        bottomNavigationView = findViewById(R.id.bottom_bar);
        homeFragment = new Home_fragment();
        historyFragment = new History_fragment();
        notificationFragment = new Notification_fragment();
        setOurFragment(homeFragment);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.home:
                        setOurFragment(homeFragment);
                        return true;

                    case R.id.history:
                        setOurFragment(historyFragment);
                        return true;

                    case R.id.notification:
                        setOurFragment(notificationFragment);
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
        fragmentTransaction.replace(R.id.mainframe,fragment);
        fragmentTransaction.commit();
    }
}

