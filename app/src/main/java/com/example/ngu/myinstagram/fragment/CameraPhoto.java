package com.example.ngu.myinstagram.fragment;

import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.ngu.myinstagram.R;
import com.example.ngu.myinstagram.helper.CameraPreview;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CameraPhoto extends Fragment {
    public Camera mCamera;
    private CameraPreview mPreview;
    ImageButton button_capture;
    public static final int MEDIA_TYPE_IMAGE = 1;
    public static final int MEDIA_TYPE_VIDEO = 2;
    View rootView;
    RelativeLayout rl_root_photo,rl_header_photo;
    FrameLayout fl_shoot_photo;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_camera_photo, container, false);
        button_capture = (ImageButton) rootView.findViewById(R.id.button_capture);
        rl_root_photo=(RelativeLayout) rootView.findViewById(R.id.rl_root_photo);
        rl_header_photo=(RelativeLayout) rootView.findViewById(R.id.rl_header_photo);
        fl_shoot_photo=(FrameLayout) rootView.findViewById(R.id.fl_shoot_photo);

        if (checkCameraHardware(getActivity())) {
            //Create an instance of Camera
            mCamera = getCameraInstance();
            Toast.makeText(getActivity(), "camera is available :-)", Toast.LENGTH_SHORT).show();
            // Create our Preview view and set it as the content of our activity.
            mPreview = new CameraPreview(this.getActivity(), mCamera);
            FrameLayout preview = (FrameLayout) rootView.findViewById(R.id.camera_preview);
            //rootView.getWidth();
            preview.addView(mPreview);
        } else {
            Toast.makeText(getActivity(), "camera not available o.O", Toast.LENGTH_SHORT).show();
        }
        int heightOfShootLayout = button_capture.getHeight();//rl_header_photo.getHeight()
        Log.e("------", heightOfShootLayout + "");
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        button_capture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isExternalStorageReadable() && isExternalStorageWritable()) {
                    //mCamera.takePicture(null, null, mPicture);
                    takePhoto(mCamera);
                    //releaseCamera();
                    Log.e("------", "OKAY External Storage");
                } else {
                    Log.e("------", "couldn't find External Storage");
                }
                button_capture.setEnabled(false);
            }
        });


    }

    /**
     * Check if this device has a camera
     */
    private boolean checkCameraHardware(Context context) {
        if (context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
            // this device has a camera
            return true;
        } else {
            // no camera on this device
            return false;
        }
    }

    /**
     * A safe way to get an instance of the Camera object.
     */
    public static Camera getCameraInstance() {
        Camera c = null;
        try {
            c = Camera.open(); // attempt to get a Camera instance
        } catch (Exception e) {
            // Camera is not available (in use or does not exist)
        }
        return c; // returns null if camera is unavailable
    }

    @Override
    public void onPause() {
        super.onPause();
        releaseCamera();//neu khong lan sau truy cap camera ung dung se chet
    }

    private static PictureCallback mPicture = new PictureCallback() {
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
                Log.e("-----creating FOS", "Okay");
            } catch (FileNotFoundException e) {
                Log.e("-------File not found: ", e.getMessage());
            } catch (IOException e) {
                Log.e("-------accessing", e.getMessage());
            }
        }
    };

    /**
     * Create a file Uri for saving an image or video
     */
    private static Uri getOutputMediaFileUri(int type) {
        return Uri.fromFile(getOutputMediaFile(type));
    }

    /**
     * Create a File for saving an image or video
     */
    private static File getOutputMediaFile(int type) {
        // To be safe, you should check that the SDCard is mounted
        // using Environment.getExternalStorageState() before doing this.
        Log.e("------", Environment.getExternalStorageState().toString());
        Log.e("------", Environment.getRootDirectory().toString());
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "MyCameraApp");//MyCameraApp
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

    private void releaseCamera() {
        if (mCamera != null) {
            mCamera.release();        // release the camera for other applications
            mCamera = null;
        }
    }

    /* Checks if external storage is available for read and write */
    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    /* Checks if external storage is available to at least read */
    public boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }
    public static void takePhoto(Camera mCamera) {
        mCamera.startPreview();
        mCamera.takePicture(null, null, mPicture);
    }
}