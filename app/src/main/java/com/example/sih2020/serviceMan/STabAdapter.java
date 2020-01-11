package com.example.sih2020.serviceMan;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

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
                RequestPending requestPending = new RequestPending();
                return requestPending;
            case 1:
                 RequestCompleted requestCompleted = new RequestCompleted();
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
