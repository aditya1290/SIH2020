package com.example.sih2020.serviceMan.adapters;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.sih2020.serviceMan.fragments.RequestCompletedFragment;
import com.example.sih2020.serviceMan.fragments.RequestPendingFragment;

public class STabAdapter extends FragmentPagerAdapter {
    private Context myContext;
    int totalTabs;

    public STabAdapter(Context context, FragmentManager fm, int totalTabs) {
        super(fm);
        myContext = context;
        this.totalTabs = totalTabs;
    }

    // this is for fragment tabs
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                RequestPendingFragment requestPending = new RequestPendingFragment();
                return requestPending;
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
