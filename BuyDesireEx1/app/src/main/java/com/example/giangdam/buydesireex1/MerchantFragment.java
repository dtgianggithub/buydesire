package com.example.giangdam.buydesireex1;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.TabHost;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Giang.Dam on 8/5/2015.
 */
public class MerchantFragment extends android.support.v4.app.Fragment {

    ViewPager viewpagerMerchant ;
    TabHost tabHostMerchant;
    HorizontalScrollView horizontalScrollView;


    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.merchants_fragment,container,false);

        viewpagerMerchant = (ViewPager)view.findViewById(R.id.viewpagerMerchant);
        tabHostMerchant = (TabHost)view.findViewById(R.id.tabHostMerchant);
        horizontalScrollView = (HorizontalScrollView)view.findViewById(R.id.scrollView);


        initTabHost();
        initViewPager();



        for(int i = 0; i < tabHostMerchant.getTabWidget().getChildCount(); i++) {
            View v = tabHostMerchant.getTabWidget().getChildAt(i);

            // Look for the title view to ensure this is an indicator and not a divider.
            TextView tv = (TextView)v.findViewById(android.R.id.title);
            if(tv == null) {
                continue;
            }
            v.setBackgroundResource(R.drawable.tab_selector);
        }




        viewpagerMerchant.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                tabHostMerchant.setCurrentTab(position);
                //tabHost.getTabWidget().getChildTabViewAt(position).setBackgroundColor(Color.RED);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        tabHostMerchant.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                int selectedPage = tabHostMerchant.getCurrentTab();
                viewpagerMerchant.setCurrentItem(selectedPage);

                View tabView = tabHostMerchant.getCurrentTabView();
                int scrollPostion = tabView.getLeft() - (horizontalScrollView.getWidth() - tabView.getWidth()) / 2;
                horizontalScrollView.smoothScrollTo(scrollPostion, 0);
                
            }
        });


        return  view;
    }


    private void initTabHost() {
        tabHostMerchant.setup();

        String[] tabNames = {"Map","List", "Directory"};
        for(int i = 0 ; i< tabNames.length; i++){
            TabHost.TabSpec tabSpec;
            tabSpec = tabHostMerchant.newTabSpec(tabNames[i]);
            tabSpec.setIndicator(tabNames[i]);
            tabSpec.setContent(new FakeContent(getActivity().getApplicationContext()));
            tabHostMerchant.addTab(tabSpec);
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

        ArrayList<Fragment> fragments = new ArrayList<android.support.v4.app.Fragment>();
        //fragments.clear();
        fragments.add(new MerchantFragment_Map());
        fragments.add(new MerchantFragment_List());
        fragments.add(new MerchantFragment_Directory());


        PagerAdapter pagerAdapter = new PagerAdapter(getFragmentManager(),fragments);
        viewpagerMerchant.setAdapter(pagerAdapter);

    }
}
