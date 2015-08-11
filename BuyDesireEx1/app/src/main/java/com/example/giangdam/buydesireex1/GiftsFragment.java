package com.example.giangdam.buydesireex1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Giang.Dam on 8/5/2015.
 */
public class GiftsFragment extends android.support.v4.app.Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.gifts_fragment,container,false);
        return  view;
    }
}
