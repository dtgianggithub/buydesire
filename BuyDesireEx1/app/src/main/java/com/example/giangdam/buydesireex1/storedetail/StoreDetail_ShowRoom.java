package com.example.giangdam.buydesireex1.storedetail;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.TabHost;

import com.example.giangdam.buydesireex1.PagerAdapter;
import com.example.giangdam.buydesireex1.R;

import java.util.ArrayList;

/**
 * Created by Giang.Dam on 8/21/2015.
 */
public class StoreDetail_ShowRoom extends Fragment {


    ViewPager viewpagerStoreInfoShowroom ;
    TabHost tabHostStoreInfoShowroom;
    HorizontalScrollView horizontalScrollView;


    //PagerAdapter pagerAdapter;
    ArrayList<Fragment> fragments;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.store_detail_fragment_showroom, container, false);



        viewpagerStoreInfoShowroom = (ViewPager)view.findViewById(R.id.viewpagerStoreInfoShowroom);
        tabHostStoreInfoShowroom = (TabHost)view.findViewById(R.id.tabHostStoreInfoShowroom);
        horizontalScrollView = (HorizontalScrollView)view.findViewById(R.id.scrollView);


        //fragments = new ArrayList<android.support.v4.app.Fragment>();

        initTabHost();
        initViewPager();
        viewpagerStoreInfoShowroom.setOffscreenPageLimit(2);


        viewpagerStoreInfoShowroom.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                tabHostStoreInfoShowroom.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        tabHostStoreInfoShowroom.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                int selectedPage = tabHostStoreInfoShowroom.getCurrentTab();
                viewpagerStoreInfoShowroom.setCurrentItem(selectedPage);

                View tabView = tabHostStoreInfoShowroom.getCurrentTabView();
                int scrollPostion = tabView.getLeft() - (horizontalScrollView.getWidth() - tabView.getWidth()) / 2;
                horizontalScrollView.smoothScrollTo(scrollPostion, 0);

            }
        });
        return view;
    }




    private void initTabHost() {
        tabHostStoreInfoShowroom.setup();

        String[] tabNames = {"Popular","Shop"};
        for(int i = 0 ; i< tabNames.length; i++){
            TabHost.TabSpec tabSpec;
            tabSpec = tabHostStoreInfoShowroom.newTabSpec(tabNames[i]);
            tabSpec.setIndicator(tabNames[i]);
            tabSpec.setContent(new FakeContent(getActivity().getApplicationContext()));
            tabHostStoreInfoShowroom.addTab(tabSpec);
        }
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

    private void initViewPager(){
        //viewpagerStoreInfoShowroom.setOffscreenPageLimit(2);
        ArrayList<android.support.v4.app.Fragment> fragments = new ArrayList<android.support.v4.app.Fragment>();
        fragments.add(new StoreDetail_ShowRoom_Popular());
        fragments.add(new StoreDetail_ShowRoom_Shop());

        PagerAdapter pagerAdapter = new PagerAdapter(getActivity().getSupportFragmentManager(),fragments);
        viewpagerStoreInfoShowroom.setAdapter(pagerAdapter);

    }

    @Override
    public void onResume() {
        super.onResume();
        //initViewPager();
    }
}
