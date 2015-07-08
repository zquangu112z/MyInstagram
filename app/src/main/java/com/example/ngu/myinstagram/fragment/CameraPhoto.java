package com.example.ngu.myinstagram.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
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
import com.example.ngu.myinstagram.activity.CameraActivity;
import com.example.ngu.myinstagram.activity.EditPictureActivity;
import com.example.ngu.myinstagram.helper.CameraPreview;
import com.example.ngu.myinstagram.helper.SavePictureTask;
import com.example.ngu.myinstagram.helper.camera.MyFaceDetectionListener;
import com.example.ngu.myinstagram.model.DataPicture;

import java.util.concurrent.atomic.AtomicBoolean;

//import java.util.ArrayList;


public class CameraPhoto extends Fragment {
    public Camera mCamera;
    private CameraPreview mPreview;
    FrameLayout preview;
    ImageButton button_capture;
    public static final int MEDIA_TYPE_IMAGE = 1;
    public static final int MEDIA_TYPE_VIDEO = 2;
    View rootView;
    RelativeLayout rl_root_photo, rl_header_photo;
    FrameLayout fl_shoot_photo;
    ImageButton ic_flash;
    int flash_state = 1;
    byte dataPicture[];
    Handler handler;
    AtomicBoolean isRunning;


    BroadcastReceiver broadcastReceiver = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        IntentFilter intentFilter = new IntentFilter("hello");
        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Intent intentGoToEditPicture = new Intent(getActivity(), EditPictureActivity.class);
                startActivity(intentGoToEditPicture);

            }
        };
        getActivity().registerReceiver(broadcastReceiver, intentFilter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().unregisterReceiver(broadcastReceiver);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_camera_photo, container, false);
        button_capture = (ImageButton) rootView.findViewById(R.id.button_capture);
        rl_root_photo = (RelativeLayout) rootView.findViewById(R.id.rl_root_photo);
        //rl_header_photo = (RelativeLayout) rootView.findViewById(R.id.rl_header_photo);
        fl_shoot_photo = (FrameLayout) rootView.findViewById(R.id.fl_shoot_photo);
        ic_flash = (ImageButton) rootView.findViewById(R.id.ic_flash);

        if (checkCameraHardware(getActivity())) {
            //Create an instance of Camera
            mCamera = getCameraInstance();
            mCamera.setFaceDetectionListener(new MyFaceDetectionListener());

            Toast.makeText(getActivity(), "camera is available :-)", Toast.LENGTH_SHORT).show();
            // Create our Preview view and set it as the content of our activity.
            mPreview = new CameraPreview(this.getActivity(), mCamera);
            preview = (FrameLayout) rootView.findViewById(R.id.camera_preview);

            preview.addView(mPreview);

        } else {
            Toast.makeText(getActivity(), "camera not available o.O", Toast.LENGTH_SHORT).show();
        }


        //how to create an W=H shape?
        int height = CameraActivity.getHeight() - 250 - CameraActivity.getWidth();
        //TO-DO
// Gets the layout params that will allow you to resize the layout
        ViewGroup.LayoutParams params = fl_shoot_photo.getLayoutParams();
// Changes the height and width to the specified *pixels*
        params.height = height;


        return rootView;
    }

    @Override
    public void onViewCreated(View view, final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        button_capture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isExternalStorageReadable() && isExternalStorageWritable()) {
                    //takePhoto(mCamera);
                    final SavePictureTask savePictureTask = new SavePictureTask(getActivity());
                    savePictureTask.execute(mCamera);


                    //waiting for savePictureTask complete
//                    final Thread waiting = new Thread(new Runnable() {
//                        @Override
//                        public void run() {
//                            while (savePictureTask.getStatus() == AsyncTask.Status.RUNNING) {
//                                SystemClock.sleep(1000);
//                                Log.e("------", "running");
//                            }
//                            Intent intentGoToEditPicture = new Intent(getActivity(), EditPictureActivity.class);
//                            startActivity(intentGoToEditPicture);
//                            Log.e("------", "finished");
//                        }
//                    });
//                    waiting.start();


                    Log.e("------", "OKAY External Storage");
                } else {
                    Log.e("------", "couldn't find External Storage");
                }
                button_capture.setEnabled(false);
//broadcast


            }
        });


/**
 * nut flash
 */
        //final Camera.Parameters params=mCamera.getParameters();
        final Camera.Parameters params = mPreview.getParams();
        ic_flash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flash_state = flash_state + 1;
                if (flash_state % 2 == 0) {
                    ic_flash.setBackgroundResource(R.drawable.ic_flash_enable);
                    params.setFlashMode("on");
                    mCamera.setParameters(params);
                } else {
                    ic_flash.setBackgroundResource(R.drawable.ic_flash);
                    params.setFlashMode("off");
                    mCamera.setParameters(params);
                }

            }
        });
    }


    /**
     * A safe way to get an instance of the Camera object.
     */
    private static Camera getCameraInstance() {
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


    /*release the camera for other applications*/
    private void releaseCamera() {
        if (mCamera != null) {
            mCamera.release();
            mCamera = null;
        }
    }

    /* Checks if external storage is available for read and write */
    private boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    /* Checks if external storage is available to at least read */
    private boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }

    /* Check if this device has a camera     */
    private boolean checkCameraHardware(Context context) {
        if (context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
            // this device has a camera
            return true;
        } else {
            // no camera on this device
            return false;
        }
    }

}
