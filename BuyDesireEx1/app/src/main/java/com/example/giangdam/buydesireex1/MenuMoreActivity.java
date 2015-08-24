package com.example.giangdam.buydesireex1;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.TabHost;
import android.widget.TextView;

import com.example.giangdam.buydesireex1.menumore.MenuMoreByCategoryFragment;
import com.example.giangdam.buydesireex1.menumore.MenuMoreByRegionFragment;
import com.example.giangdam.buydesireex1.menumore.MenuMoreSortFragment;

import java.util.ArrayList;

public class MenuMoreActivity extends AppCompatActivity {

    ViewPager viewpagerMenuMore ;
    TabHost tabHostMenuMore;
    HorizontalScrollView horizontalScrollView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(android.R.style.Theme_Holo_Light_Dialog_NoActionBar_MinWidth);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_more);


        viewpagerMenuMore = (ViewPager)findViewById(R.id.viewpagerMenuMore);
        tabHostMenuMore = (TabHost)findViewById(R.id.tabHostMenuMore);
        horizontalScrollView = (HorizontalScrollView)findViewById(R.id.scrollView);

        initTabHost();
        initViewPager();


        for(int i = 0; i < tabHostMenuMore.getTabWidget().getChildCount(); i++) {
            View v = tabHostMenuMore.getTabWidget().getChildAt(i);

            // Look for the title view to ensure this is an indicator and not a divider.
            TextView tv = (TextView)v.findViewById(android.R.id.title);
            if(tv == null) {
                continue;
            }
            v.setBackgroundResource(R.drawable.tab_selector);
        }

        viewpagerMenuMore.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                tabHostMenuMore.setCurrentTab(position);
                //tabHost.getTabWidget().getChildTabViewAt(position).setBackgroundColor(Color.RED);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        tabHostMenuMore.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                int selectedPage = tabHostMenuMore.getCurrentTab();
                viewpagerMenuMore.setCurrentItem(selectedPage);

                View tabView = tabHostMenuMore.getCurrentTabView();
                int scrollPostion = tabView.getLeft() - (horizontalScrollView.getWidth() - tabView.getWidth()) / 2;
                horizontalScrollView.smoothScrollTo(scrollPostion, 0);

            }
        });
    }


    private void initTabHost() {
        tabHostMenuMore.setup();

        String[] tabNames = {"By Category","By Region", "Sort"};
        for(int i = 0 ; i< tabNames.length; i++){
            TabHost.TabSpec tabSpec;
            tabSpec = tabHostMenuMore.newTabSpec(tabNames[i]);
            tabSpec.setIndicator(tabNames[i]);
            tabSpec.setContent(new FakeContent(getApplicationContext()));
            tabHostMenuMore.addTab(tabSpec);
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
        viewpagerMenuMore.setOffscreenPageLimit(2);
        ArrayList<Fragment> fragments = new ArrayList<android.support.v4.app.Fragment>();
        fragments.add(new MenuMoreByCategoryFragment());
        fragments.add(new MenuMoreByRegionFragment());
        fragments.add(new MenuMoreSortFragment());


        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager(),fragments);
        viewpagerMenuMore.setAdapter(pagerAdapter);
    }


}
