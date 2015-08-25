package com.example.giangdam.buydesireex1;

import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.giangdam.buydesireex1.camera.CameraPreview;

public class CameraPreviewActivity extends AppCompatActivity {

    android.support.v7.widget.Toolbar toolbar;

    private Camera mCamera;
    private CameraPreview mPreview;




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

        if(checkCameraHardWare(this)){
            Toast.makeText(this,"Connecting camera successully !!",Toast.LENGTH_LONG).show();
            mCamera = getCameraInstance();
            mPreview = new CameraPreview(this,mCamera);
            FrameLayout preview = (FrameLayout)findViewById(R.id.camera_preview);
            preview.addView(mPreview);
        }else{
            Toast.makeText(this,"Error when connect to camera device !! ",Toast.LENGTH_LONG).show();
        }


    }

    //check if this device has a camera
    private  boolean checkCameraHardWare(Context context){
        if(context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)){
            //this device has a camera
            return true;
        }else {
            //no camera on this device
            return false;
        }
    }


    public static android.hardware.Camera getCameraInstance(){
        android.hardware.Camera camera = null;
        try{
            camera = android.hardware.Camera.open();
        }catch (Exception e){
            //Camera is not available
        }

        return  camera;
    }
}
