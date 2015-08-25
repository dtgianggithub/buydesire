package com.example.giangdam.buydesireex1.camera;

import android.content.Context;
import android.hardware.Camera;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;

/**
 * Created by Giang.Dam on 8/25/2015.
 */
public class CameraPreview extends SurfaceView implements SurfaceHolder.Callback {


    private SurfaceHolder mHolder;
    private Camera mCamera;

    public CameraPreview(Context context, Camera camera) {
        super(context);

        this.mCamera = camera;

        // Install a SurfaceHolder.Callback so we get notified when the
        // underlying surface is created and destroyed.
        mHolder = getHolder();
        mHolder.addCallback(this);
        // deprecated setting, but required on Android versions prior to 3.0
        mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);


    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        //The surface has been created, now tell the camrea where to draw the preview
        try{
            mCamera.setPreviewDisplay(holder);
            mCamera.startPreview();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            //If your preview can change or rotate, take care of those events herer.
        //Make sure to stop preview before resize or refortmating it.

        if(mHolder.getSurface() == null){
            //preview surface does not exist
            return;
        }

        //stop preview before making change
        try {
            mCamera.stopPreview();
        }catch (Exception e){
            //ignore: tried to stop a non-existent preview
        }


        //set preview size and make any resize, rotate or reformating here.
        try {
            mCamera.setPreviewDisplay(mHolder);
            mCamera.startPreview();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        // empty. Take care of releasing the Camera preview in your activity.
    }
}
