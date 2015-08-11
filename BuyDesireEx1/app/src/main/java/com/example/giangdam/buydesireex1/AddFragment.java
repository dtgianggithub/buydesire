package com.example.giangdam.buydesireex1;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Giang.Dam on 8/5/2015.
 */
public class AddFragment  extends android.support.v4.app.Fragment{




    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_fragment,container,false);

        // create Intent to take a picture and return control to the calling application
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivity(intent);
        return  view;
    }
}
