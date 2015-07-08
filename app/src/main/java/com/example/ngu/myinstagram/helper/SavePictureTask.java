package com.example.ngu.myinstagram.helper;

import android.app.Activity;
import android.app.Fragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.hardware.Camera;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;


import com.example.ngu.myinstagram.activity.CameraActivity;
import com.example.ngu.myinstagram.fragment.CameraPhoto;
import com.example.ngu.myinstagram.model.DataPicture;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.SynchronousQueue;

/**
 * Created by Ngu on 7/2/2015.
 */
public class SavePictureTask extends AsyncTask<Camera, Void, Void>{
    public static final int MEDIA_TYPE_IMAGE = 1;
    public static final int MEDIA_TYPE_VIDEO = 2;
    static Activity activity_start_edit_activity;

    public SavePictureTask(Activity activity_start_edit_activity) {
        this.activity_start_edit_activity=activity_start_edit_activity;
    }

    @Override
    protected Void doInBackground(Camera... params) {
        Camera camera = params[0];
        takePhoto(camera);
        //camera.release();

        return null;
    }


    //take photo
    public static void takePhoto(Camera mCamera) {
        mCamera.startPreview();
        mCamera.takePicture(null, null, mPicture);

        //startAgain
        // mCamera.startPreview();
    }

    private static File getOutputMediaFile(int type) {
        // To be safe, you should check that the SDCard is mounted
        // using Environment.getExternalStorageState() before doing this.
        Log.e("------", Environment.getExternalStorageState().toString());
        Log.e("------", Environment.getRootDirectory().toString());
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "MyCameraApp");//MyCameraApp
        Log.e("------", Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES).toString());
        // This location works best if you want the created images to be shared
        // between applications and persist after your app has been uninstalled.

        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.d("MyCameraApp", "failed to create directory");
                return null;
            }

        }

        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File mediaFile;
        if (type == MEDIA_TYPE_IMAGE) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                    "IMG_" + timeStamp + ".jpg");
        } else if (type == MEDIA_TYPE_VIDEO) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                    "VID_" + timeStamp + ".mp4");
        } else {
            return null;
        }
        return mediaFile;
    }

    private static Camera.PictureCallback mPicture = new Camera.PictureCallback() {
        @Override
        public void onPictureTaken(byte[] data, Camera camera) {

            File pictureFile = getOutputMediaFile(MEDIA_TYPE_IMAGE);

            Log.e("------", "vao picture");

            if (pictureFile == null) {
                Log.e("------", "creating null");
                return;
            }

            try {
                FileOutputStream fos = new FileOutputStream(pictureFile);
                fos.write(data);
                fos.flush();
                fos.close();
                Log.e("------", "creating FOS Okay");
            } catch (FileNotFoundException e) {
                Log.e("------", "File not found: " + e.getMessage());
            } catch (IOException e) {
                Log.e("------", "accessing" + e.getMessage());
            }

            DataPicture.x.setData(data);
            Log.e("------", "test" + DataPicture.x.getData()[2]);



            //start activity
            Intent intent=new Intent("hello");
            activity_start_edit_activity.sendBroadcast(intent);

        }
    };

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
    }
}
