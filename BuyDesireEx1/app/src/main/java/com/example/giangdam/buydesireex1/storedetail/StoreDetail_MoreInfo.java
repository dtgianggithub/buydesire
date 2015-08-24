package com.example.giangdam.buydesireex1.storedetail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.giangdam.buydesireex1.MyRecommendationsAdapter;
import com.example.giangdam.buydesireex1.R;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Giang.Dam on 8/21/2015.
 */
public class StoreDetail_MoreInfo extends Fragment {

    String[] string_list_help;
    MyRecommendationsAdapter myRecommendationsAdapter ;  // custom adapter
    ArrayList<String> arrayListHelp;//array list string list menu
    ListView lvstoredetailmoreinfo; //listview contain menu list

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.store_detail_fragment_moreinfo, container, false);


        arrayListHelp = new ArrayList<String>();
        string_list_help = getResources().getStringArray(R.array.listmoreinfo);
        Collections.addAll(arrayListHelp, string_list_help); // replace for for() or foreach
        myRecommendationsAdapter = new MyRecommendationsAdapter(getActivity(),R.layout.custom_list_recommendations,arrayListHelp);
        lvstoredetailmoreinfo = (ListView)view.findViewById(R.id.lvstoredetailmoreinfo);
        lvstoredetailmoreinfo.setAdapter(myRecommendationsAdapter);

        return view;
    }

}
