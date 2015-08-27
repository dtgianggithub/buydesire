package com.example.giangdam.buydesireex1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.giangdam.buydesireex1.savedesire.MySaveDesireAdapter;

import java.util.ArrayList;

public class SaveDesireActivity extends AppCompatActivity  {

    ArrayList<String> listDesire,listSeeDesire;
    MySaveDesireAdapter mySaveDesireAdapter_Desire,mySaveDesireAdapter_SeeDesire;
    ListView lvDesireList,lvSeeDesireList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(android.R.style.Theme_Holo_Light_Dialog_NoActionBar_MinWidth);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_desire);

        lvDesireList = (ListView)findViewById(R.id.lvDesireList);
        lvSeeDesireList = (ListView)findViewById(R.id.lvSeeDesireList);

        listDesire = new ArrayList<>();
        listDesire.add("Default Desire List");
        mySaveDesireAdapter_Desire = new MySaveDesireAdapter(this,R.layout.custom_list_savedesire,listDesire,0);
        lvDesireList.setAdapter(mySaveDesireAdapter_Desire);

        listSeeDesire = new ArrayList<>();
        listSeeDesire.add("My Friends");
        listSeeDesire.add("Everyone");
        listSeeDesire.add("Only me");
        mySaveDesireAdapter_SeeDesire = new MySaveDesireAdapter(this,R.layout.custom_list_savedesire,listSeeDesire,1);
        lvSeeDesireList.setAdapter(mySaveDesireAdapter_SeeDesire);


        lvSeeDesireList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                for (int i = 0; i < listSeeDesire.size(); i++) {
                    if (i != position) {
                        View anotherView = parent.getChildAt(i);
                        ImageView viewnotSelected = (ImageView) anotherView.findViewById(R.id.imgsavedesirechoosen);
                        viewnotSelected.setImageResource(android.R.color.transparent);
                    }
                }

                ImageView viewSelected = (ImageView) view.findViewById(R.id.imgsavedesirechoosen);
                viewSelected.setImageResource(R.drawable.ic_save_desirelist_selete);

            }
        });

        lvDesireList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                for (int i = 0; i < listDesire.size(); i++) {
                    if (i != position) {
                        View anotherView = parent.getChildAt(i);
                        ImageView viewnotSelected = (ImageView) anotherView.findViewById(R.id.imgsavedesirechoosen);
                        viewnotSelected.setImageResource(android.R.color.transparent);
                    }
                }

                ImageView viewSelected = (ImageView) view.findViewById(R.id.imgsavedesirechoosen);
                viewSelected.setImageResource(R.drawable.ic_save_desirelist_selete);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_save_desire, menu);
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
