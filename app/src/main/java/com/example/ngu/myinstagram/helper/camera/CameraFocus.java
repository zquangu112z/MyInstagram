package com.example.ngu.myinstagram.helper.camera;

import android.graphics.Rect;
import android.hardware.Camera;
import android.os.AsyncTask;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ngu on 7/7/2015.
 */
public class CameraFocus extends AsyncTask<Camera, Void,Void> {
    @Override
    protected Void doInBackground(Camera... param) {
        Camera mCamera=param[0];
        // get Camera parameters
        final Camera.Parameters params = mCamera.getParameters();
        //focus
        List<String> focusModes = params.getSupportedFocusModes();
        for (int i=0;i<focusModes.size();i++){
            Log.e("------", "focusModes contains:" + i + " " + focusModes.get(i));
        }
        params.setFocusMode(Camera.Parameters.FOCUS_MODE_AUTO);

        //focus area
        if (params.getMaxNumMeteringAreas() > 0){ // check that metering areas are supported
            List<Camera.Area> meteringAreas = new ArrayList<Camera.Area>();
            Rect areaRect1 = new Rect(-100, -100, 100, 100);    // specify an area in center of image
            meteringAreas.add(new Camera.Area(areaRect1, 600)); // set weight to 60%
            Rect areaRect2 = new Rect(800, -1000, 1000, -800);  // specify an area in upper right of image
            meteringAreas.add(new Camera.Area(areaRect2, 400)); // set weight to 40%
            params.setMeteringAreas(meteringAreas);
        }
        //picture size
        params.setPictureSize(640, 480);//thay doi kich co anh: 4:3
        //chieu cua anh luu
        params.setRotation(90);

        mCamera.setParameters(params);
        return null;
    }
}
