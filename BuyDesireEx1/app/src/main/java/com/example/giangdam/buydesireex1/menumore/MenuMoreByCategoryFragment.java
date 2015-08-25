package com.example.giangdam.buydesireex1.menumore;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.giangdam.buydesireex1.R;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Giang.Dam on 8/24/2015.
 */
public class MenuMoreByCategoryFragment extends Fragment {

    ListView lvmenumore_bycategory;
    ArrayList<String> arrayList;
    MyMenuMoreByCategoryAdapter myMenuMoreByCategoryAdapter;
    String[] string_menumore_category;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.menumore_bycategory_fragment, container, false);

        //do something here
        lvmenumore_bycategory = (ListView)view.findViewById(R.id.lvmenumore_bycategory);

        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        ViewGroup header  = (ViewGroup)inflater.inflate(R.layout.header_menumore_bycategory, lvmenumore_bycategory,false);
        lvmenumore_bycategory.addHeaderView(header, null, false);
        ViewGroup footer = (ViewGroup)inflater.inflate(R.layout.footer_menumore_bycategory, lvmenumore_bycategory,false);
        lvmenumore_bycategory.addFooterView(footer,null,false);



        string_menumore_category = getActivity().getResources().getStringArray(R.array.menumore_bycategory);
        arrayList = new ArrayList<>();
        Collections.addAll(arrayList, string_menumore_category); // replace for for() or foreach
        myMenuMoreByCategoryAdapter = new MyMenuMoreByCategoryAdapter(getActivity().getBaseContext(),R.layout.custom_list_menumore_bycategory,arrayList);
        lvmenumore_bycategory.setAdapter(myMenuMoreByCategoryAdapter);
        return view;
    }
}
