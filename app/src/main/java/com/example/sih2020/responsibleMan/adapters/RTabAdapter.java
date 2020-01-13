package com.example.sih2020.responsibleMan.adapters;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.sih2020.responsibleMan.fragments.RMComplaintPendingFragment;
import com.example.sih2020.serviceMan.fragments.RequestCompletedFragment;


public class RTabAdapter extends FragmentPagerAdapter {
    private Context myContext;
    int totalTabs;

    public RTabAdapter(Context context, FragmentManager fm, int totalTabs) {
        super(fm);
        myContext = context;
        this.totalTabs = totalTabs;
    }

    // this is for fragment tabs
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                RMComplaintPendingFragment rmComplaintPendingFragment = new RMComplaintPendingFragment();
                return rmComplaintPendingFragment;
            case 1:
                RequestCompletedFragment requestCompleted = new RequestCompletedFragment();
                return requestCompleted;

            default:
                return null;
        }
    }
    // this counts total number of tabs
    @Override
    public int getCount() {
        return totalTabs;
    }
}
