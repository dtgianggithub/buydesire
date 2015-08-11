package com.example.giangdam.buydesireex1;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Giang.Dam on 8/5/2015.
 */
public class PagerAdapterTest extends FragmentPagerAdapter {

    //ArrayList<Fragment> fragments;
    //Context context;


    /*
    public PagerAdapterTest(Context context,FragmentManager supportFragmentManager, ArrayList<Fragment> fragments) {
        super(supportFragmentManager);
        this.fragments = fragments;
        this.context = context;
    }
    */

    public PagerAdapterTest(FragmentManager supportFragmentManager) {
        super(supportFragmentManager);

    }


    @Override
    public Fragment getItem(int i) {
        switch (i){
            case 0:
                return new RecommendedFragment_Us();
            case 1:
                return new RecommendedFragment_Friends();
            case 2:
                return new RecommendedFragment_you();
            default:
                return new RecommendedFragment_Us();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }


}
