package com.example.giangdam.buydesireex1;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;

public class SettingActivity extends AppCompatActivity {

    android.support.v7.widget.Toolbar toolbar;

    String[] string_list_setting;
    MyRecommendationsAdapter myRecommendationsAdapter ;  // custom adapter
    ArrayList<String> arrayListSetting;//array list string list menu
    ListView lvSetting; //listview contain menu list


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);


        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.app_bar_setting);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setTitle("Setting");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        arrayListSetting = new ArrayList<String>();
        string_list_setting = getResources().getStringArray(R.array.listsetting);
        Collections.addAll(arrayListSetting, string_list_setting); // replace for for() or foreach
        myRecommendationsAdapter = new MyRecommendationsAdapter(this,R.layout.custom_list_recommendations,arrayListSetting);
        lvSetting = (ListView)findViewById(R.id.lvSetting);
        lvSetting.setAdapter(myRecommendationsAdapter);


        lvSetting.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        Intent intentEditProfile = new Intent(SettingActivity.this, EditProfileActivity.class);
                        startActivity(intentEditProfile);
                        break;
                    case 1:
                        Intent intentShippingBilling = new Intent(SettingActivity.this, ShippingBillingActivity.class);
                        startActivity(intentShippingBilling);
                        break;
                    case 2:
                        Intent intentPayment = new Intent(SettingActivity.this, PaymentActivity.class);
                        startActivity(intentPayment);
                        break;
                    case 3:
                        Intent intentNotificationSetting = new Intent(SettingActivity.this,NotificationSettingActivity.class);
                        startActivity(intentNotificationSetting);
                        break;
                    case 4:
                        Intent intentPrivacy = new Intent(SettingActivity.this, PrivacyActivity.class);
                        startActivity(intentPrivacy);
                        break;
                    case 5:
                        Intent intentLocationService = new Intent(SettingActivity.this, LocationServiceActivity.class);
                        startActivity(intentLocationService);
                        break;
                    case 6:
                        Intent intentLanguage = new Intent(SettingActivity.this, LanguageActivity.class);
                        startActivity(intentLanguage);
                        break;
                    case 7:
                        doLogout();
                        break;
                    default:
                        break;
                }
            }
        });

    }


    public void doLogout(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirmation");
        builder.setMessage("Logout now?");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if (LoginActivity.typeLogin == 1) {
                    LoginActivity.accessToken.setCurrentAccessToken(null);
                }
                if (LoginActivity.typeLogin == 2) {
                    LoginActivity.pref = getSharedPreferences(LoginActivity.TWITTER_SHAREPRE, Context.MODE_PRIVATE);
                    SharedPreferences.Editor edit = LoginActivity.pref.edit();
                    edit.putString("ACCESS_TOKEN", "");
                    edit.apply();
                }

                if (LoginActivity.typeLogin == 3) {
                    LoginActivity.pref = getSharedPreferences(LoginActivity.GOOGLEPLUS_SHAREPRE, Context.MODE_PRIVATE);
                    SharedPreferences.Editor edit = LoginActivity.pref.edit();
                    edit.putString("SESSION_ID", "");
                    edit.apply();

                }

                if (LoginActivity.typeLogin == 4) {
                    LoginActivity.pref = getSharedPreferences(LoginActivity.MYACCOUNT_SHAREPRE, Context.MODE_PRIVATE);
                    SharedPreferences.Editor edit = LoginActivity.pref.edit();
                    edit.putLong("My_User_Id",-1);
                    edit.apply();
                }


                Intent intent = new Intent(SettingActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.create().show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_setting, menu);
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
