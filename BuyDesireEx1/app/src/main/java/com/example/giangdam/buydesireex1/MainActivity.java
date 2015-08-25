package com.example.giangdam.buydesireex1;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.giangdam.model.User;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.google.android.gms.maps.MapFragment;
import com.google.gson.Gson;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
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



    ImageView imgprofilepicture;
    TextView lblusername;
    Bitmap bitmaptwitter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //UNIVERSAL IMAGE LOADER SETUP
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder().cacheOnDisk(true)
                .cacheInMemory(true).imageScaleType(ImageScaleType.EXACTLY).displayer(new FadeInBitmapDisplayer(300)).build();

        ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(getApplicationContext())
                .defaultDisplayImageOptions(defaultOptions).memoryCache(new WeakMemoryCache()).diskCacheSize(100*1024*1024).build();

        ImageLoader.getInstance().init(configuration);

        setContentView(R.layout.activity_main);




        imgprofilepicture = (ImageView)findViewById(R.id.imgprofilepicture);
        lblusername = (TextView)findViewById(R.id.lblusername);

        //ImageLoader for avater
        final ImageLoader imageLoaderAvartar = ImageLoader.getInstance();
        final DisplayImageOptions imageOptionsAvartar = new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisk(true)
                .displayer(new RoundedBitmapDisplayer(1000))
                .resetViewBeforeLoading(true).build();

        if(LoginActivity.typeLogin == 1){
            GraphRequest request = GraphRequest.newMeRequest(
                    LoginActivity.accessToken,
                    new GraphRequest.GraphJSONObjectCallback() {
                        @Override
                        public void onCompleted(
                                JSONObject object,
                                GraphResponse response) {
                            // Application code
                            User user = new Gson().fromJson(String.valueOf(object),User.class);
                            if(user != null){
                                lblusername.setText(user.getName());
                                //new viewImageUrl(imgprofilepicture).execute(user.getPicture().getData().getUrl());
                                imageLoaderAvartar.displayImage(user.getPicture().getData().getUrl(),imgprofilepicture, imageOptionsAvartar);
                            }


                        }
                    });
            Bundle parameters = new Bundle();
            parameters.putString("fields", "id,name,link,picture");
            request.setParameters(parameters);
            request.executeAsync();
        }

        if(LoginActivity.typeLogin == 2){
            LoginActivity.pref = getSharedPreferences(LoginActivity.TWITTER_SHAREPRE,MODE_PRIVATE);
            lblusername.setText(LoginActivity.pref.getString("NAME", ""));
            imageLoaderAvartar.displayImage(LoginActivity.pref.getString("IMAGE_URL", ""),imgprofilepicture, imageOptionsAvartar);
            //new LoadProfileTwitter().execute();
        }

        if(LoginActivity.typeLogin == 3){
            //new LoadProfileGooglePlus().execute();
            LoginActivity.pref = getSharedPreferences(LoginActivity.GOOGLEPLUS_SHAREPRE,MODE_PRIVATE);
            lblusername.setText(LoginActivity.pref.getString("PERSONNAME_GOOGLEPLUS", ""));
            imageLoaderAvartar.displayImage(LoginActivity.pref.getString("URLIMG_GOOGLEPLUS", ""),imgprofilepicture, imageOptionsAvartar);

        }




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
                        /*
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivity(intent);
                        break;
                        */

                        Intent intent = new Intent(MainActivity.this, CameraPreviewActivity.class);
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
                        if (MerchantFragment_Map.googleMap != null) {
                            MerchantFragment_Map.googleMap = ((MapFragment)getFragmentManager().findFragmentById(R.id.map)).getMap();
                            getFragmentManager().beginTransaction()
                                    .remove(getFragmentManager().findFragmentById(R.id.map)).commit();
                            MerchantFragment_Map.googleMap = null;
                        }
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

        switch (item.getItemId()){
            case R.id.ic_menu_more:
                //do something here
                Intent intent = new Intent(MainActivity.this,MenuMoreActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);


    }



    private class viewImageUrl extends AsyncTask<String, Void, Bitmap> {

        ImageView bmImage;

        public viewImageUrl(ImageView bmImage){
            this.bmImage = bmImage;
        }


        @Override
        protected Bitmap doInBackground(String... params) {

            String urlDisplay = params[0];
            Bitmap bitmap = null;
            try {
                InputStream inputStream = new URL(urlDisplay).openStream();
                bitmap = BitmapFactory.decodeStream(inputStream);
            } catch (IOException e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }catch (RuntimeException e){
                e.printStackTrace();
            }
            return bitmap;
        }

        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            Bitmap circleBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
            BitmapShader shader = new BitmapShader (bitmap,  Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
            Paint paint = new Paint();
            paint.setShader(shader);

            Canvas c = new Canvas(circleBitmap);
            c.drawCircle(bitmap.getWidth() / 2, bitmap.getHeight() / 2, bitmap.getWidth() / 2, paint);


            bmImage.setImageBitmap(circleBitmap);
        }
    }

    private class LoadProfileGooglePlus extends AsyncTask<String, String, Bitmap> {

        LoadProfileGooglePlus(){
            LoginActivity.pref = getSharedPreferences(LoginActivity.GOOGLEPLUS_SHAREPRE,MODE_PRIVATE);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
        protected Bitmap doInBackground(String... args) {
            try {
                bitmaptwitter = BitmapFactory.decodeStream((InputStream) new URL(LoginActivity.pref.getString("URLIMG_GOOGLEPLUS", "")).getContent());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return bitmaptwitter;
        }
        protected void onPostExecute(Bitmap image) {
            Bitmap image_circle = Bitmap.createBitmap(bitmaptwitter.getWidth(), bitmaptwitter.getHeight(), Bitmap.Config.ARGB_8888);
            BitmapShader shader = new BitmapShader (bitmaptwitter,  Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
            Paint paint = new Paint();
            paint.setShader(shader);
            Canvas c = new Canvas(image_circle);
            c.drawCircle(image.getWidth() / 2, image.getHeight() / 2, image.getWidth() / 2, paint);
            imgprofilepicture.setImageBitmap(image_circle);
            lblusername.setText(LoginActivity.pref.getString("PERSONNAME_GOOGLEPLUS", ""));
        }
    }


    private class LoadProfileTwitter extends AsyncTask<String, String, Bitmap> {

       LoadProfileTwitter(){
           LoginActivity.pref = getSharedPreferences(LoginActivity.TWITTER_SHAREPRE,MODE_PRIVATE);
       }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
        protected Bitmap doInBackground(String... args) {
            try {
                bitmaptwitter = BitmapFactory.decodeStream((InputStream) new URL(LoginActivity.pref.getString("IMAGE_URL", "")).getContent());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return bitmaptwitter;
        }
        protected void onPostExecute(Bitmap image) {
            Bitmap image_circle = Bitmap.createBitmap(bitmaptwitter.getWidth(), bitmaptwitter.getHeight(), Bitmap.Config.ARGB_8888);
            BitmapShader shader = new BitmapShader (bitmaptwitter,  Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
            Paint paint = new Paint();
            paint.setShader(shader);
            Canvas c = new Canvas(image_circle);
            c.drawCircle(image.getWidth() / 2, image.getHeight() / 2, image.getWidth() / 2, paint);
            imgprofilepicture.setImageBitmap(image_circle);
            lblusername.setText(LoginActivity.pref.getString("NAME", ""));
        }
    }
}
