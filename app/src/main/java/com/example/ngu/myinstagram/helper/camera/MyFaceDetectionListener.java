package com.example.ngu.myinstagram.helper.camera;

import android.hardware.Camera;
import android.util.Log;

/**
 * Created by Ngu on 7/7/2015.
 */
public class MyFaceDetectionListener implements Camera.FaceDetectionListener {

    @Override
    public void onFaceDetection(Camera.Face[] faces, Camera camera) {
        if (faces.length > 0){
            Log.d("FaceDetection", "face detected: " + faces.length +
                    " Face 1 Location X: " + faces[0].rect.centerX() +
                    "Y: " + faces[0].rect.centerY());
        }
    }
}
