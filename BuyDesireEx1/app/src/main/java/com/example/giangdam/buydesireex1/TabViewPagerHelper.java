package com.example.giangdam.buydesireex1;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TabHost;

import java.util.ArrayList;

/**
 * Created by Giang.Dam on 8/5/2015.
 */
public class TabViewPagerHelper  extends FragmentActivity{

    TabHost tabHost;
    ViewPager viewPager;
    Context context;

    public TabViewPagerHelper(Context context, TabHost tabHost, ViewPager viewPager){
        this.context = context;
        this.tabHost = tabHost;
        this.viewPager = viewPager;
    }


    public void initTabHost(String[] tabNames){
        tabHost.setup();

        for(int i = 0 ; i< tabNames.length; i++){
            TabHost.TabSpec tabSpec;
            tabSpec = tabHost.newTabSpec(tabNames[i]);
            tabSpec.setIndicator(tabNames[i]);
            tabSpec.setContent(new FakeContent(this.context));
            tabHost.addTab(tabSpec);
        }
    }


    public void initViewPager(ArrayList<android.support.v4.app.Fragment> arrayList){
        PagerAdapter pagerAdapter = new com.example.giangdam.buydesireex1.PagerAdapter(getSupportFragmentManager(),arrayList);
        this.viewPager.setAdapter(pagerAdapter);
    }

    public class FakeContent  implements TabHost.TabContentFactory{

        Context context;

        public FakeContent(Context context){
            this.context = context;
        }

        @Override
        public View createTabContent(String tag) {
            View fakeview = new View(context);
            fakeview.setMinimumHeight(0);
            fakeview.setMinimumWidth(0);
            return fakeview;
        }
    }
}
