package com.example.giangdam.buydesireex1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.TabHost;
import android.widget.TextView;

import com.example.giangdam.buydesireex1.storedetail.StoreDetail_Details;
import com.example.giangdam.buydesireex1.storedetail.StoreDetail_MoreInfo;
import com.example.giangdam.buydesireex1.storedetail.StoreDetail_ShowRoom;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

public class StoreInfomationActivity extends AppCompatActivity {

    android.support.v7.widget.Toolbar toolbar;
    LatLng storeLatLng;


    ViewPager viewpagerStoreInfo ;
    TabHost tabHostStoreInfo;
    HorizontalScrollView horizontalScrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_infomation);

        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.app_bar_storeinfo);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setTitle("Store Name");

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("StoreInfoBundle");
        if(!bundle.isEmpty())
        {
            storeLatLng = new LatLng(bundle.getDouble("Latitude"), bundle.getDouble("Longitude"));
        }


        viewpagerStoreInfo = (ViewPager)findViewById(R.id.viewpagerStoreInfo);
        tabHostStoreInfo = (TabHost)findViewById(R.id.tabHostStoreInfo);
        horizontalScrollView = (HorizontalScrollView)findViewById(R.id.scrollView);

        initTabHost();
        initViewPager();


        for(int i = 0; i < tabHostStoreInfo.getTabWidget().getChildCount(); i++) {
            View v = tabHostStoreInfo.getTabWidget().getChildAt(i);

            // Look for the title view to ensure this is an indicator and not a divider.
            TextView tv = (TextView)v.findViewById(android.R.id.title);
            if(tv == null) {
                continue;
            }
            v.setBackgroundResource(R.drawable.tab_selector);
        }

        viewpagerStoreInfo.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                tabHostStoreInfo.setCurrentTab(position);
                //tabHost.getTabWidget().getChildTabViewAt(position).setBackgroundColor(Color.RED);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        tabHostStoreInfo.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                int selectedPage = tabHostStoreInfo.getCurrentTab();
                viewpagerStoreInfo.setCurrentItem(selectedPage);

                View tabView = tabHostStoreInfo.getCurrentTabView();
                int scrollPostion = tabView.getLeft() - (horizontalScrollView.getWidth() - tabView.getWidth()) / 2;
                horizontalScrollView.smoothScrollTo(scrollPostion, 0);

            }
        });

    }



    private void initTabHost() {
        tabHostStoreInfo.setup();

        String[] tabNames = {"Showroom","Details", "More Info"};
        for(int i = 0 ; i< tabNames.length; i++){
            TabHost.TabSpec tabSpec;
            tabSpec = tabHostStoreInfo.newTabSpec(tabNames[i]);
            tabSpec.setIndicator(tabNames[i]);
            tabSpec.setContent(new FakeContent(getApplicationContext()));
            tabHostStoreInfo.addTab(tabSpec);
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
        viewpagerStoreInfo.setOffscreenPageLimit(2);
        ArrayList<Fragment> fragments = new ArrayList<android.support.v4.app.Fragment>();
        fragments.add(new StoreDetail_ShowRoom());
        fragments.add(new StoreDetail_Details());
        fragments.add(new StoreDetail_MoreInfo());


        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager(),fragments);
        viewpagerStoreInfo.setAdapter(pagerAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_store_infomation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
