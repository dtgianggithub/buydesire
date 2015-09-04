package com.example.giangdam.buydesireex1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.example.giangdam.buydesireex1.setting.MyContactUsAdapter;

import java.util.ArrayList;
import java.util.Collections;

public class ContactUsActivity extends AppCompatActivity {

    Toolbar toolbar;

    ListView lvContactUs;

    String[] string_list_contactus;
    MyContactUsAdapter myContactUsAdapter ;  // custom adapter
    ArrayList<String> arrayListContactUs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        toolbar = (Toolbar)findViewById(R.id.app_bar_contactus);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("Contact Us");

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        arrayListContactUs = new ArrayList<String>();
        string_list_contactus = getResources().getStringArray(R.array.contactus);
        Collections.addAll(arrayListContactUs, string_list_contactus); // replace for for() or foreach
        myContactUsAdapter = new MyContactUsAdapter(this,R.layout.custom_list_contactus,arrayListContactUs);
        lvContactUs = (ListView)findViewById(R.id.lvContactUs);
        lvContactUs.setAdapter(myContactUsAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_contact_us, menu);
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
