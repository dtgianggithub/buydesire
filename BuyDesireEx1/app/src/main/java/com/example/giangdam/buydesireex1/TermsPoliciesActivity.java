package com.example.giangdam.buydesireex1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;

public class TermsPoliciesActivity extends AppCompatActivity {

    Toolbar toolbar;

    ListView lvTermsPolicies;

    String[] string_list_termpolicies;
    MyRecommendationsAdapter myRecommendationsAdapter ;  // custom adapter
    ArrayList<String> arrayListTermPolicies;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms_policies);

        toolbar = (Toolbar)findViewById(R.id.app_bar_termspolicies);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("Terms & Policies");

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        arrayListTermPolicies = new ArrayList<String>();
        string_list_termpolicies = getResources().getStringArray(R.array.listtermpolicies);
        Collections.addAll(arrayListTermPolicies, string_list_termpolicies); // replace for for() or foreach
        myRecommendationsAdapter = new MyRecommendationsAdapter(this,R.layout.custom_list_recommendations,arrayListTermPolicies);
        lvTermsPolicies = (ListView)findViewById(R.id.lvTermsPolicies);
        lvTermsPolicies.setAdapter(myRecommendationsAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_terms_policies, menu);
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
