package com.example.giangdam.buydesireex1;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by Giang.Dam on 8/5/2015.
 */
public class ShoppingBagFragment extends android.support.v4.app.Fragment {

    Button btnDiscoverDesire;


    @Override
    public View onCreateView(LayoutInflater inflater,  final ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.shoppingbag_fragment,container,false);

        btnDiscoverDesire = (Button)view.findViewById(R.id.btnDiscoverDesire);

        btnDiscoverDesire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DesiresFragment desiresFragment = new DesiresFragment();
                android.support.v4.app.FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                fragmentTransaction.replace(R.id.container, desiresFragment);
                fragmentTransaction.commit();


            }
        });

        return  view;
    }
}
