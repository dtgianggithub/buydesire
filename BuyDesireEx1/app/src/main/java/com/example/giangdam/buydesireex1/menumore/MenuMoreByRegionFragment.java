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
public class MenuMoreByRegionFragment extends Fragment {

    ListView lvmenumore_byregion;
    ArrayList<String> arrayList;
    MyMenuMoreByRegionAdapter myMenuMoreByRegionAdapter;
    String[] string_menumore_region;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.menumore_byregion_fragment, container, false);

        //do something here
        lvmenumore_byregion = (ListView)view.findViewById(R.id.lvmenumore_byregion);

        string_menumore_region = getActivity().getResources().getStringArray(R.array.menumore_byregion);
        arrayList = new ArrayList<>();
        Collections.addAll(arrayList, string_menumore_region); // replace for for() or foreach
        myMenuMoreByRegionAdapter = new MyMenuMoreByRegionAdapter(getActivity().getBaseContext(),R.layout.custom_list_menumore_byregion,arrayList);
        lvmenumore_byregion.setAdapter(myMenuMoreByRegionAdapter);
        return view;
    }
}
