package com.example.ngu.myinstagram.helper;

/**
 * Created by Ngu on 6/30/2015.
 */

import android.content.Context;
import android.graphics.Rect;
import android.hardware.Camera;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * A basic Camera preview class
 */
public class CameraPreview extends SurfaceView implements SurfaceHolder.Callback {
    private static final String TAG = "TAG----";
    private SurfaceHolder mHolder;
    private Camera mCamera;
    public Camera.Parameters params;

    public Camera.Parameters getParams() {
        return params;
    }

    public CameraPreview(Context context, Camera camera) {
        super(context);
        mCamera = camera;
        // get Camera parameters
        params = mCamera.getParameters();

        // Install a SurfaceHolder.Callback so we get notified when the
        // underlying surface is created and destroyed.
        mHolder = getHolder();
        mHolder.addCallback(this);
        // deprecated setting, but required on Android versions prior to 3.0
        mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
    }


    public void surfaceDestroyed(SurfaceHolder holder) {
        // empty. Take care of releasing the Camera preview in your activity.
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        // The Surface has been created, now tell the camera where to draw the preview.
        try {

            mCamera.setDisplayOrientation(90);
            mCamera.setPreviewDisplay(holder);

            mCamera.startPreview();
            //step 2
            startFaceDetection();
        } catch (IOException e) {
            Log.d(TAG, "Error setting camera preview: " + e.getMessage());
        }
        Toast.makeText(getContext(), "chup di", Toast.LENGTH_SHORT).show();
        Log.e("------", "surfaceCreated");
//
    }

    public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) {
        // If your preview can change or rotate, take care of those events here.
        // Make sure to stop the preview before resizing or reformatting it.
        Log.e("------", "surfaceChanged");
        if (mHolder.getSurface() == null) {
            // preview surface does not exist
            return;
        }

        // stop preview before making changes
        try {
            mCamera.stopPreview();
        } catch (Exception e) {
            // ignore: tried to stop a non-existent preview
        }

        // set preview size and make any resize, rotate or
        // reformatting changes here
/**
 * Camera Features
 */
        //focus
        List<String> focusModes = params.getSupportedFocusModes();
        for (int i = 0; i < focusModes.size(); i++) {
            Log.e("------", "focusModes contains:" + i + " " + focusModes.get(i));
        }
        params.setFocusMode(Camera.Parameters.FOCUS_MODE_AUTO);
        List<Camera.Size> previewSizes = params.getSupportedPreviewSizes();
        Log.e("------", previewSizes.toString());
        //focus area
        if (params.getMaxNumMeteringAreas() > 0) { // check that metering areas are supported
            List<Camera.Area> meteringAreas = new ArrayList<Camera.Area>();

            Rect areaRect1 = new Rect(-100, -100, 100, 100);    // specify an area in center of image
            meteringAreas.add(new Camera.Area(areaRect1, 600)); // set weight to 60%
            params.setMeteringAreas(meteringAreas);
        }
        //picture size
        params.setPictureSize(640, 480);//thay doi kich co anh: 4:3
        //chieu cua anh luu
        params.setRotation(90);

        mCamera.setParameters(params);
//        CameraFocus x=new CameraFocus();
//        x.execute(mCamera);
        /**
         * Camera Features
         */


        // start preview with new settings
        try {
            //mCamera.setDisplayOrientation(0);
            mCamera.setPreviewDisplay(mHolder);
            mCamera.startPreview();
            //step 2
            //mCamera.setFaceDetectionListener(new MyFaceDetectionListener());
            startFaceDetection();

        } catch (Exception e) {
            Log.d(TAG, "Error starting camera preview: " + e.getMessage());
        }
    }

    public void startFaceDetection() {
        // Try starting Face Detection
        //Camera.Parameters params = mCamera.getParameters();

        // start face detection only *after* preview has started
        if (params.getMaxNumDetectedFaces() > 0) {
            // camera supports face detection, so can start it:
            mCamera.startFaceDetection();
            Log.e("------", "detect ok");
        }
    }
}