package com.example.ncpmap.adapter;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;



import java.util.List;

public class MainViewAdapter extends FragmentPagerAdapter {

    private List<Fragment> mFragments;

    public MainViewAdapter(FragmentManager fm) {
        super(fm);
    }

    public MainViewAdapter(FragmentManager fm, List<Fragment> mFragments) {
        super(fm);
        this.mFragments = mFragments;
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }


    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }


}
