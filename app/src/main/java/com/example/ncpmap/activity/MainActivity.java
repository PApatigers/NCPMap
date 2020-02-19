package com.example.ncpmap.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;


import com.example.ncpmap.R;
import com.example.ncpmap.adapter.MainViewAdapter;
import com.example.ncpmap.fragment.FunctionFragment;
import com.example.ncpmap.fragment.NcpDailyCaseFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity {

    private ViewPager mViewPager;
    private BottomNavigationView mBottonView;
    private Toolbar mToolBar;
    private FragmentManager fm;
    private List<Fragment> mFragments;

    private Fragment mNcpDailyCaseFragment,mFunctionFragment;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener ( ) {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId ( )) {
                case R.id.ncp_map:
                    mViewPager.setCurrentItem (0);
                    return true;
                case R.id.function:
                    mViewPager.setCurrentItem (1);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mViewPager = findViewById(R.id.main_pager);
        mBottonView = findViewById(R.id.bottom_view);
        mToolBar = findViewById(R.id.main_toolbar);

        initFragment();
        mBottonView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    void initFragment(){
        fm = getSupportFragmentManager();
        mNcpDailyCaseFragment = new NcpDailyCaseFragment();
        mFunctionFragment = new FunctionFragment();

        mFragments = new ArrayList<>();
        mFragments.add(mNcpDailyCaseFragment);
        mFragments.add(mFunctionFragment);

        FragmentPagerAdapter adapter = new MainViewAdapter(fm,mFragments);

        mViewPager.setAdapter(adapter);
        mViewPager.setCurrentItem(0);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                MenuItem item = mBottonView.getMenu().getItem(position);
                item.setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
