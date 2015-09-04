package com.example.giangdam.buydesireex1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.giangdam.buydesireex1.setting.MyLanguageAdapter;

import java.util.ArrayList;
import java.util.Collections;

public class LanguageActivity extends AppCompatActivity {


    android.support.v7.widget.Toolbar toolbar;
    ListView lvLanguage;

    String[] language_strings;
    ArrayList<String> language_arraylist;
    MyLanguageAdapter myLanguageAdapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);


        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.app_bar_language);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setTitle("Languages");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        lvLanguage = (ListView)findViewById(R.id.lvLanguage);
        language_strings = getResources().getStringArray(R.array.language);
        language_arraylist = new ArrayList<>();
        Collections.addAll(language_arraylist,language_strings);
        myLanguageAdapter = new MyLanguageAdapter(this, R.layout.custom_list_language,language_arraylist);
        lvLanguage.setAdapter(myLanguageAdapter);



        lvLanguage.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                for(int i = 0; i< language_arraylist.size(); i++){
                    View tempView = parent.getChildAt(i);
                    ImageView tempImageSelect = (ImageView) tempView.findViewById(R.id.imgSelectLanguage);
                    tempImageSelect.setImageResource(android.R.color.transparent);
                }

                View currentView = parent.getChildAt(position);
                ImageView currentImageSelect = (ImageView)currentView.findViewById(R.id.imgSelectLanguage);
                currentImageSelect.setImageResource(R.drawable.ic_item_selected);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_language, menu);
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
