package com.jonathanblair.fitnessjournal.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by oluwafemi.bamisaye on 4/16/2016.
 */
public class JournalPagerAdapter extends FragmentStatePagerAdapter {

    private Context context;
    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final List<String> mFragmentTitleList = new ArrayList<>();

    // Build a Constructor and assign the passed Values to appropriate values in the class
    public JournalPagerAdapter(FragmentManager fm, Context mContext) {
        super(fm);
        this.context = mContext;
    }

    //This method return the fragment for the every position in the View Pager
    @Override
    public Fragment getItem(int position) {

        return mFragmentList.get(position);


    }

    // This method return the fragment and title for the Tabs in the Tab Strip
    public void addFragment(Fragment fragment, String title) {
        mFragmentList.add(fragment);
        mFragmentTitleList.add(title);
    }


    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "Food & Beverages";
            case 1:
                return "Exercise";
        }

        return mFragmentTitleList.get(position);
    }

    // This method return the Number of tabs for the tabs Strip
    @Override
    public int getCount() {
        return mFragmentList.size();
    }
}
