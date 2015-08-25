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
public class MenuMoreSortFragment extends Fragment {


    ListView lvmenumore_sort;
    ArrayList<String> arrayList;
    MyMenuMoreByRegionAdapter myMenuMoreByRegionAdapter;
    String[] string_menumore_sort;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.menumore_sort_fragment, container, false);

        //do something here
        lvmenumore_sort = (ListView)view.findViewById(R.id.lvmenumore_sort);

        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        ViewGroup header  = (ViewGroup)inflater.inflate(R.layout.header_menumore_sort, lvmenumore_sort,false);
        lvmenumore_sort.addHeaderView(header, null, false);



        string_menumore_sort = getActivity().getResources().getStringArray(R.array.menumore_sort);
        arrayList = new ArrayList<>();
        Collections.addAll(arrayList, string_menumore_sort); // replace for for() or foreach
        myMenuMoreByRegionAdapter = new MyMenuMoreByRegionAdapter(getActivity().getBaseContext(),R.layout.custom_list_menumore_byregion,arrayList);
        lvmenumore_sort.setAdapter(myMenuMoreByRegionAdapter);

        return view;
    }
}
