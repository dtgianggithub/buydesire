package com.example.giangdam.buydesireex1.storedetail;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.giangdam.buydesireex1.R;
import com.example.giangdam.buydesireex1.ViewInMapActivity;
import com.google.android.gms.maps.model.LatLng;

/**
 * Created by Giang.Dam on 8/21/2015.
 */
public class StoreDetail_Details extends Fragment  {

    LinearLayout layoutviewinmap;
    LatLng storeLatLng;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.store_detail_fragment_details, container, false);

        layoutviewinmap = (LinearLayout)view.findViewById(R.id.layoutviewinmap);

        Intent intent = getActivity().getIntent();
        Bundle bundle = intent.getBundleExtra("StoreInfoBundle");
        if(!bundle.isEmpty())
        {
            storeLatLng = new LatLng(bundle.getDouble("Latitude"), bundle.getDouble("Longitude"));
        }

        layoutviewinmap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //start new Activity with map
                Intent newintent = new Intent(getActivity(), ViewInMapActivity.class);
                Bundle newbundle = new Bundle();
                if(storeLatLng!= null){
                    newbundle.putDouble("Latitude", storeLatLng.latitude);
                    newbundle.putDouble("Longitude",storeLatLng.longitude);
                    newintent.putExtra("StoreInfoBundle",newbundle);
                }

                startActivity(newintent);
            }
        });

        return view;
    }

}
