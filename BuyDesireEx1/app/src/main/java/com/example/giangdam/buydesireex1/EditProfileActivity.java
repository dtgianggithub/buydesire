package com.example.giangdam.buydesireex1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.giangdam.buydesireex1.dao.DBUser;
import com.example.giangdam.buydesireex1.daomanager.DatabaseManager;
import com.example.giangdam.buydesireex1.daomanager.IDatabaseManager;
import com.example.giangdam.model.User;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.google.gson.Gson;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class EditProfileActivity extends AppCompatActivity {


    android.support.v7.widget.Toolbar toolbar;

    ImageView imgEditProfile;
    EditText txtEditFirstName,txtEditLastName,txtEditProfileName,txtEditAboutMe,txtEditWebsiteURL,
            txtEditEmail;

    private List<DBUser> userList;
    /**
     * Manages the database for this application..
     */
    private IDatabaseManager databaseManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //UNIVERSAL IMAGE LOADER SETUP
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder().cacheOnDisk(true)
                .cacheInMemory(true).imageScaleType(ImageScaleType.EXACTLY).displayer(new FadeInBitmapDisplayer(300)).build();

        ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(getApplicationContext())
                .defaultDisplayImageOptions(defaultOptions).memoryCache(new WeakMemoryCache()).diskCacheSize(100*1024*1024).build();

        ImageLoader.getInstance().init(configuration);


        setContentView(R.layout.activity_edit_profile);



        //ImageLoader for avater
        final ImageLoader imageLoaderAvartar = ImageLoader.getInstance();
        final DisplayImageOptions imageOptionsAvartar = new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisk(true)
                .resetViewBeforeLoading(true).build();

        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.app_bar_edit_profile);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setTitle("Edit Profile");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        imgEditProfile = (ImageView)findViewById(R.id.imgEditProfile);
        txtEditFirstName = (EditText)findViewById(R.id.txtEditFirstName);
        txtEditLastName = (EditText)findViewById(R.id.txtEditLastName);
        txtEditProfileName = (EditText)findViewById(R.id.txtEditProfileName);
        txtEditAboutMe = (EditText)findViewById(R.id.txtEditAboutMe);
        txtEditWebsiteURL = (EditText)findViewById(R.id.txtEditWebsiteURL);
        txtEditEmail = (EditText)findViewById(R.id.txtEditEmail);



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
                                txtEditFirstName.setText(user.getName());
                                //new viewImageUrl(imgprofilepicture).execute(user.getPicture().getData().getUrl());
                                imageLoaderAvartar.displayImage(user.getPicture().getData().getUrl(),imgEditProfile, imageOptionsAvartar);

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
            txtEditFirstName.setText(LoginActivity.pref.getString("NAME", ""));
            imageLoaderAvartar.displayImage(LoginActivity.pref.getString("IMAGE_URL", ""),imgEditProfile, imageOptionsAvartar);
            //new LoadProfileTwitter().execute();
        }

        if(LoginActivity.typeLogin == 3){
            //new LoadProfileGooglePlus().execute();
            LoginActivity.pref = getSharedPreferences(LoginActivity.GOOGLEPLUS_SHAREPRE,MODE_PRIVATE);
            txtEditFirstName.setText(LoginActivity.pref.getString("PERSONNAME_GOOGLEPLUS", ""));
            imageLoaderAvartar.displayImage(LoginActivity.pref.getString("URLIMG_GOOGLEPLUS", ""),imgEditProfile, imageOptionsAvartar);

        }


        if(LoginActivity.typeLogin == 4){
            // init database manager
            databaseManager = new DatabaseManager(this);
            userList = new ArrayList<DBUser>();
            userList = databaseManager.listUsers();

            LoginActivity.pref = getSharedPreferences(LoginActivity.MYACCOUNT_SHAREPRE, MODE_PRIVATE);
            for(int i = 0; i< userList.size(); i++){
                if(LoginActivity.pref.getLong("My_User_Id", -1) == userList.get(i).getId()){
                    txtEditFirstName.setText(userList.get(i).getFirstname());
                    txtEditLastName.setText(userList.get(i).getLastname());
                   
                    break;
                }
            }
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit_profile, menu);
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
