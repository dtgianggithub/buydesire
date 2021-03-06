package com.example.giangdam.buydesireex1;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Giang.Dam on 8/5/2015.
 */
public class SettingsFragment extends android.support.v4.app.Fragment {


    String[] string_list_setting;
    MyRecommendationsAdapter myRecommendationsAdapter ;  // custom adapter
    ArrayList<String> arrayListSetting;//array list string list menu
    ListView lvSetting; //listview contain menu list

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.settings_fragment,container,false);

        arrayListSetting = new ArrayList<String>();
        string_list_setting = getResources().getStringArray(R.array.listsetting);
        Collections.addAll(arrayListSetting, string_list_setting); // replace for for() or foreach
        myRecommendationsAdapter = new MyRecommendationsAdapter(getActivity(),R.layout.custom_list_recommendations,arrayListSetting);
        lvSetting = (ListView)view.findViewById(R.id.lvSetting);
        lvSetting.setAdapter(myRecommendationsAdapter);


        lvSetting.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {

                    case 0:
                        break;
                    case 7:
                        doLogout();
                        break;
                    default:
                        break;
                }
            }
        });

        return  view;
    }

    public void doLogout(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Confirmation");
        builder.setMessage("Logout now?");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if (LoginActivity.typeLogin == 1) {
                    LoginActivity.accessToken.setCurrentAccessToken(null);
                }
                if (LoginActivity.typeLogin == 2) {
                    LoginActivity.pref = getActivity().getSharedPreferences(LoginActivity.TWITTER_SHAREPRE, Context.MODE_PRIVATE);
                    SharedPreferences.Editor edit = LoginActivity.pref.edit();
                    edit.putString("ACCESS_TOKEN", "");
                    edit.apply();
                }

                if (LoginActivity.typeLogin == 3) {
                    LoginActivity.pref = getActivity().getSharedPreferences(LoginActivity.GOOGLEPLUS_SHAREPRE, Context.MODE_PRIVATE);
                    SharedPreferences.Editor edit = LoginActivity.pref.edit();
                    edit.putString("SESSION_ID", "");
                    edit.apply();

                }

                if (LoginActivity.typeLogin == 4) {
                    LoginActivity.pref = getActivity().getSharedPreferences(LoginActivity.MYACCOUNT_SHAREPRE, Context.MODE_PRIVATE);
                    SharedPreferences.Editor edit = LoginActivity.pref.edit();
                    edit.putLong("My_User_Id",-1);
                    edit.apply();
                }


                Intent intent = new Intent(getActivity(), LoginActivity.class);
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
}
