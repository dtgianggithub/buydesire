package com.example.giangdam.buydesireex1;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.TabHost;

import java.util.ArrayList;

/**
 * Created by Giang.Dam on 8/5/2015.
 */
public class DesiresFragment extends android.support.v4.app.Fragment {


    ViewPager viewPager ;
    TabHost tabHost;
    HorizontalScrollView horizontalScrollView;


    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.desires_fragment,container,false);

        viewPager = (ViewPager)view.findViewById(R.id.viewpagerDesire);
        tabHost = (TabHost)view.findViewById(R.id.tabHostDesire);
        horizontalScrollView = (HorizontalScrollView)view.findViewById(R.id.scrollView);

        initTabHost();;
        initViewPager();



        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                tabHost.setCurrentTab(position);
                //tabHost.getTabWidget().getChildTabViewAt(position).setBackgroundColor(Color.RED);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                int selectedPage = tabHost.getCurrentTab();
                viewPager.setCurrentItem(selectedPage);

                View tabView = tabHost.getCurrentTabView();
                int scrollPostion = tabView.getLeft()- (horizontalScrollView.getWidth() - tabView.getWidth())/2;
                horizontalScrollView.smoothScrollTo(scrollPostion,0);
            }
        });


        return  view;
    }




    private void initTabHost() {
        tabHost.setup();

        String[] tabNames = {"Popular","Friends", "Only me"};
        for(int i = 0 ; i< tabNames.length; i++){
            TabHost.TabSpec tabSpec;
            tabSpec = tabHost.newTabSpec(tabNames[i]);
            tabSpec.setIndicator(tabNames[i]);
            tabSpec.setContent(new FakeContent(getActivity().getApplicationContext()));
            tabHost.addTab(tabSpec);
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

        ArrayList<android.support.v4.app.Fragment> fragments = new ArrayList<android.support.v4.app.Fragment>();
        //fragments.clear();
        fragments.add(new DesiresFragment_Popular());
        fragments.add(new DesiresFragment_Friends());
        fragments.add(new DesiresFragment_OnlyMe());


        PagerAdapter pagerAdapter = new PagerAdapter(getFragmentManager(),fragments);
        viewPager.setAdapter(pagerAdapter);

    }
}
