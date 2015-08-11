package com.example.giangdam.buydesireex1;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;


public class MainActivity extends AppCompatActivity {


    //declare toolbar replace actionbar
    android.support.v7.widget.Toolbar toolbar;

    //List menu for navigation drawer
    String[] string_menu;
    MyArrayAdapter myArrayAdapter ;  // custom adapter
    ArrayList<String> arrayList;//array list string list menu
    ListView lvMenu; //listview contain menu list

    DrawerLayout mDrawerLayout ; //layout support navigation drawer
    ActionBarDrawerToggle mDrawerToggle; //various help setting actionbar


    int currentChoose = -1;
    android.support.v4.app.FragmentManager fragmentManager;
    //FragmentTransaction fragmentTransaction;



    //DesiresFragment desiresFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.app_bar);
        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);

        //set data for menu list
        arrayList = new ArrayList<String>();
        string_menu = getResources().getStringArray(R.array.memulist);
        Collections.addAll(arrayList, string_menu); // replace for for() or foreach
        myArrayAdapter = new MyArrayAdapter(this,R.layout.menu_drawer,arrayList);
        lvMenu = (ListView)findViewById(R.id.lvMenu);
        lvMenu.setAdapter(myArrayAdapter);



        //settings for action bar
        mDrawerToggle= new ActionBarDrawerToggle(this, mDrawerLayout,toolbar, R.string.drawer_open, R.string.drawer_close){
            public void onDrawerClosed(View v){
                super.onDrawerClosed(v);
                invalidateOptionsMenu();
                syncState();
            }

            public void onDrawerOpened(View v){
                super.onDrawerOpened(v);
                invalidateOptionsMenu();
                syncState();

            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setLogo(R.drawable.ic_nav_logo);
        mDrawerToggle.syncState();


        fragmentManager = getSupportFragmentManager();

        //set fragment default display
        android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        DesiresFragment desiresFragment = new DesiresFragment();
        fragmentTransaction.replace(R.id.container,desiresFragment);
        fragmentTransaction.commit();

        //get event when click list menu
        lvMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                currentChoose = position;  //update current choose

                android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                //Intent intent;
                switch (position) {
                    case 0:
                        /*
                        AddFragment addFragment = new AddFragment();
                        fragmentTransaction.replace(R.id.container,addFragment);
                        fragmentTransaction.commit();
                        */

                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivity(intent);
                        break;
                    case 1:
                        DesiresFragment desiresFragment = new DesiresFragment();
                        fragmentTransaction.replace(R.id.container,desiresFragment);
                        fragmentTransaction.commit();
                        break;
                    case 2:
                        RecommendedFragment recommendedFragment = new RecommendedFragment();
                        fragmentTransaction.replace(R.id.container,recommendedFragment);
                       fragmentTransaction.commit();
                        break;
                    case 3:
                        final String appPackageName = "com.glimpse.android"; // getPackageName() from Context or Activity object
                        try {
                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
                        } catch (android.content.ActivityNotFoundException anfe) {
                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                        }
                        /*
                        GiftsFragment giftsFragment = new GiftsFragment();
                        fragmentTransaction.replace(R.id.container,giftsFragment);
                        fragmentTransaction.commit();
                        */
                        break;
                    case 4:
                        MerchantFragment merchantFragment = new MerchantFragment();
                        fragmentTransaction.replace(R.id.container,merchantFragment);
                        fragmentTransaction.commit();
                        break;
                    case 5:
                        FindFriendsFragment findFriendsFragment = new FindFriendsFragment();
                        fragmentTransaction.replace(R.id.container,findFriendsFragment);
                        fragmentTransaction.commit();
                        break;
                    case 6:
                        InviteFriendsFragment inviteFriendsFragment = new InviteFriendsFragment();
                        fragmentTransaction.replace(R.id.container,inviteFriendsFragment);
                       fragmentTransaction.commit();
                        break;
                    case 7:
                        ShoppingBagFragment shoppingBagFragment = new ShoppingBagFragment();
                        fragmentTransaction.replace(R.id.container,shoppingBagFragment);
                        fragmentTransaction.commit();
                        break;
                    case 8:
                        HelpFragment helpFragment = new HelpFragment();
                        fragmentTransaction.replace(R.id.container,helpFragment);
                        fragmentTransaction.commit();
                        break;
                    case 9:
                        SettingsFragment settingsFragment = new SettingsFragment();
                        fragmentTransaction.replace(R.id.container,settingsFragment);
                        fragmentTransaction.commit();
                        break;
                    default:
                        break;
                }

                mDrawerLayout.closeDrawers();
            }
        });

    }


    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }



        return super.onOptionsItemSelected(item);


    }
}
