package com.example.sih2020.responsibleMan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.sih2020.R;
import com.example.sih2020.responsibleMan.fragments.HistoryFragment;
import com.example.sih2020.responsibleMan.fragments.HomeFragment;
import com.example.sih2020.responsibleMan.fragments.NotificationFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BottomNavigationActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private HomeFragment homeFragment;
    private HistoryFragment historyFragment;
    private NotificationFragment notificationFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom__navigation);
        bottomNavigationView = findViewById(R.id.bottom_bar);
        homeFragment = new HomeFragment();
        historyFragment = new HistoryFragment();
        notificationFragment = new NotificationFragment();
        bottomNavigationView.setItemIconTintList(null);
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

