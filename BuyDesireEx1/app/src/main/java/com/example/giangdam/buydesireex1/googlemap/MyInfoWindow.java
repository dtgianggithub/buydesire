package com.example.giangdam.buydesireex1.googlemap;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

/**
 * Created by Giang.Dam on 8/21/2015.
 */
public class MyInfoWindow implements GoogleMap.InfoWindowAdapter {

    Context context;
    int layoutId;

    public MyInfoWindow(Context context, int layoutId){
        super();
        this.context = context;
        this.layoutId = layoutId;
    }

    @Override
    public View getInfoWindow(Marker marker) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View  view = layoutInflater.inflate(layoutId, null);
        return  view;
    }

    @Override
    public View getInfoContents(Marker marker) {
        return null;
    }
}
