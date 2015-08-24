package com.example.giangdam.buydesireex1.menumore;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.giangdam.buydesireex1.R;

/**
 * Created by Giang.Dam on 8/24/2015.
 */
public class MenuMoreByRegionFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.menumore_byregion_fragment, container, false);
        return view;
    }
}
