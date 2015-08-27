package com.example.giangdam.buydesireex1;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.giangdam.buydesireex1.camera.ZxingCodeScannerFragment;

public class CameraPreviewActivity extends AppCompatActivity {

    android.support.v7.widget.Toolbar toolbar;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_preview);

        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.app_bar_camera_preview);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setLogo(R.drawable.ic_nav_logo);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        ZxingCodeScannerFragment zxingCodeScannerFragment = new ZxingCodeScannerFragment();
        fragmentTransaction.replace(R.id.camera_preview, zxingCodeScannerFragment);
        fragmentTransaction.commit();

    }

}
