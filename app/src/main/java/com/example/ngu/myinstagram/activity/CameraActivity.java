package com.example.ngu.myinstagram.activity;

import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ngu.myinstagram.MainActivity;
import com.example.ngu.myinstagram.R;
import com.example.ngu.myinstagram.adapter.CameraPagerAdapter;
import com.example.ngu.myinstagram.camera.layout.SlidingTabLayout;

public class CameraActivity extends ActionBarActivity {
    ViewPager vp_camera;
    SlidingTabLayout sliding_tab_camera;
    CameraPagerAdapter pagerAdapter;
    String titles[] = {"GALLERY", "PHOTO", "VIDEO"};
    int numberOfTabs = 3;
    //int num = 0;
    private Uri fileUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        vp_camera = (ViewPager) findViewById(R.id.vp_camera);
        vp_camera.setCurrentItem(1);
        //Viewpager
        pagerAdapter = new CameraPagerAdapter(getSupportFragmentManager(), titles, numberOfTabs);
        //set ViewPager
        vp_camera.setAdapter(pagerAdapter);
        //Tabs
        sliding_tab_camera = (SlidingTabLayout) findViewById(R.id.sliding_tab_camera);
        sliding_tab_camera.setDistributeEvenly(true);
        sliding_tab_camera.setViewPager(vp_camera);
        // super.onCreate(savedInstanceState);

//        bt_close = (ImageButton) findViewById(R.id.bt_close);
//        bt_close.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });




    }

//    //nut back se ve mainactivity
//    @Override
//    public void onBackPressed() {
//        //super.onBackPressed();
//        //startActivity(new Intent(getApplicationContext(), MainActivity.class));
//        finish();
//    }

    /** Check if this device has a camera */
    private boolean checkCameraHardware(Context context) {
        if (context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)){
            // this device has a camera
            return true;
        } else {
            // no camera on this device
            return false;
        }
    }
    /** A safe way to get an instance of the Camera object. */
    public static Camera getCameraInstance(){
        Camera c = null;
        try {
            c = Camera.open(); // attempt to get a Camera instance
        }
        catch (Exception e){
            // Camera is not available (in use or does not exist)
        }
        return c; // returns null if camera is unavailable
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}


/*//TextView doi chu theo trang thai
        sliding_tab_camera.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0: {
                        tv_camera_header.setText("gallery");
                        break;
                    }
                    case 1: {
                        tv_camera_header.setText("photo");
                        break;
                    }
                    case 2: {
                        tv_camera_header.setText("video");
                        break;
                    }

                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        vp_camera.setOnGenericMotionListener(new View.OnGenericMotionListener() {
            @Override
            public boolean onGenericMotion(View v, MotionEvent event) {
                int num = vp_camera.getCurrentItem();
                Log.e("OnTouch------", "OKAY");
                //tv_camera_header.set
                switch (num) {
                    case 0: {
                        tv_camera_header.setText("gallery");
                        break;
                    }
                    case 1: {
                        tv_camera_header.setText("photo");
                        break;
                    }
                    case 2: {
                        tv_camera_header.setText("video");
                        break;
                    }

                }
                return false;
            }
        });*/
