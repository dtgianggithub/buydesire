package com.example.giangdam.buydesireex1;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by Giang.Dam on 8/5/2015.
 */
public class PagerAdapter extends FragmentPagerAdapter {

    ArrayList<Fragment> fragments;


    public PagerAdapter(FragmentManager supportFragmentManager, ArrayList<android.support.v4.app.Fragment> fragments) {
        super(supportFragmentManager);
        this.fragments = fragments;
    }



    @Override
    public Fragment getItem(int i) {
        return this.fragments.get(i);
    }

    @Override
    public int getCount() {
        return this.fragments.size();
    }






}
