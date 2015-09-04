package com.example.giangdam.buydesireex1;

import android.content.Intent;
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
public class HelpFragment extends android.support.v4.app.Fragment {

    String[] string_list_help;
    MyRecommendationsAdapter myRecommendationsAdapter ;  // custom adapter
    ArrayList<String> arrayListHelp;//array list string list menu
    ListView lvListHelp; //listview contain menu list

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.help_fragment,container,false);


        arrayListHelp = new ArrayList<String>();
        string_list_help = getResources().getStringArray(R.array.listhelp);
        Collections.addAll(arrayListHelp, string_list_help); // replace for for() or foreach
        myRecommendationsAdapter = new MyRecommendationsAdapter(getActivity(),R.layout.custom_list_recommendations,arrayListHelp);
        lvListHelp = (ListView)view.findViewById(R.id.lvListHelp);
        lvListHelp.setAdapter(myRecommendationsAdapter);


        lvListHelp.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        Intent intentAbout = new Intent(getActivity(),AboutActivity.class);
                        startActivity(intentAbout);
                        break;
                    case 1:
                        Intent intentTermPolicies = new Intent(getActivity(),TermsPoliciesActivity.class);
                        startActivity(intentTermPolicies);
                        break;
                    case 2:
                        Intent intentFAQs = new Intent(getActivity(),FAQsActivity.class);
                        startActivity(intentFAQs);
                        break;
                    case 3:
                        Intent intentContactUs = new Intent(getActivity(),ContactUsActivity.class);
                        startActivity(intentContactUs);
                        break;
                    default:
                        break;
                }
            }
        });

        return  view;
    }
}
