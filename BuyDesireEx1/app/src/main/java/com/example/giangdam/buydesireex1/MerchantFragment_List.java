package com.example.giangdam.buydesireex1;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Giang.Dam on 8/5/2015.
 */
public class MerchantFragment_List extends Fragment {


    String[] string_merchant_list;
    MyMerchantListAdapter myMerchantListAdapter ;  // custom adapter
    ArrayList<String> arrayListMerchantList;//array list string list menu
    ListView lvMerchantList; //listview contain menu list

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.merchants_fragment_list,container,false);


        arrayListMerchantList = new ArrayList<String>();
        string_merchant_list = getResources().getStringArray(R.array.merchants_list);
        Collections.addAll(arrayListMerchantList, string_merchant_list); // replace for for() or foreach
        myMerchantListAdapter = new MyMerchantListAdapter(getActivity(),R.layout.custom_list_merchant_list,arrayListMerchantList);
        lvMerchantList = (ListView)view.findViewById(R.id.lvMerchantList);
        lvMerchantList.setAdapter(myMerchantListAdapter);

        return  view;
    }




}
