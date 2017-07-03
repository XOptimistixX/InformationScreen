package com.example.leochris.launcher;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.leochris.launcher.featured.FeaturedFragment;
import com.example.leochris.launcher.weather.WeatherTab;
import com.ogaclejapan.smarttablayout.SmartTabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private int mInterval = 30000; // 5 seconds by default, can be changed later
    private Handler mHandler;
    private ViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.addFragment(new WeatherTab(), "WEATHER");
        adapter.addFragment(new FeaturedFragment(), "FEATURED");
        adapter.addFragment(new TextTab(), "TEXT");

        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(adapter.getCount()-1);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        //Set tab color
        tabLayout.setBackgroundColor(Color.parseColor("#45B39D"));
        mHandler = new Handler();

        //automatically scroll to next tab
        startRepeatingTask();
    }

    private void setupViewPager(ViewPager viewPager) {
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new TextTab(), "ONE");
        //adapter.addFragment(new VideoTab(), "THREE");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    @Override
    public void onBackPressed() {

    }

    public void launchLauncher(View view) {
        Intent intent = getPackageManager().getLaunchIntentForPackage("sg.edu.nushigh.interactivedisplaylauncher");
        startActivity(intent);
    }

    /*protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == 1) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                FragmentInfo mFragmentInfo = (FragmentInfo) data.getSerializableExtra("new fragment");
                ImageTab mImageTab = new ImageTab();
                Bundle arg = new Bundle();

                System.out.println(mFragmentInfo.getName());

                arg.putString("name", mFragmentInfo.getName());
                arg.putString("uri", mFragmentInfo.getUri());
                mImageTab.setArguments(arg);

                adapter.addFragment(mImageTab, mImageTab.getArguments().getString("name"));
                adapter.notifyDataSetChanged();
            }
        }
    }*/

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopRepeatingTask();
    }

    Runnable mStatusChecker = new Runnable() {
        @Override
        public void run() {
            try {
                if(viewPager.getCurrentItem() < viewPager.getAdapter().getCount() - 1) {
                    viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                }
                else {
                    viewPager.setCurrentItem(0);
                }
            } finally {
                // 100% guarantee that this always happens, even if
                // your update method throws an exception
                mHandler.postDelayed(mStatusChecker, mInterval);
            }
        }
    };

    void startRepeatingTask() {
        mStatusChecker.run();
    }

    void stopRepeatingTask() {
        mHandler.removeCallbacks(mStatusChecker);
    }
}
