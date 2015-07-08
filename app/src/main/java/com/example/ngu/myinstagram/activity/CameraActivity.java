package com.example.ngu.myinstagram.activity;


import android.net.Uri;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.ngu.myinstagram.MainActivity;
import com.example.ngu.myinstagram.R;
import com.example.ngu.myinstagram.adapter.CameraPagerAdapter;
import com.example.ngu.myinstagram.camera.layout.SlidingTabLayout;

public class CameraActivity extends ActionBarActivity {
    public static int height, width;
    ViewPager vp_camera;
    SlidingTabLayout sliding_tab_camera;
    CameraPagerAdapter pagerAdapter;
    String titles[] = {"GALLERY", "PHOTO", "VIDEO"};
    int numberOfTabs = 3;
    //int num = 0;
    private Uri fileUri;
    ImageButton bt_close_camera;
    TextView tv_camera_header_tittle;
    RelativeLayout rl_root_camera, rl_header_camera;
public  CameraActivity instance(){
    return CameraActivity.this;
}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        //Thay doi spinner thay vi textViewTittle
        rl_root_camera = (RelativeLayout) findViewById(R.id.rl_root_camera);
        rl_header_camera = (RelativeLayout) findViewById(R.id.rl_header_camera);

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

        bt_close_camera = (ImageButton) findViewById(R.id.bt_close_camera);
        bt_close_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }

        });
        //TextView doi chu theo trang thai
        tv_camera_header_tittle = (TextView) findViewById(R.id.tv_camera_header_tittle);
        sliding_tab_camera.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0: {
                        setTextForTextView(tv_camera_header_tittle, "gallery");
                        break;
                    }
                    case 1: {
                        setTextForTextView(tv_camera_header_tittle, "photo");
                        break;
                    }
                    case 2: {
                        setTextForTextView(tv_camera_header_tittle, "video");
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
                switch (num) {
                    case 0: {
                        setTextForTextView(tv_camera_header_tittle, "gallery");
                        break;
                    }
                    case 1: {
                        setTextForTextView(tv_camera_header_tittle, "photo");
                        break;
                    }
                    case 2: {
                        setTextForTextView(tv_camera_header_tittle, "video");
                        break;
                    }
                }
                return false;
            }
        });


        //lay do cao man hinh, dung cho cat hinh preview
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        height = displaymetrics.heightPixels;
        Log.e("------", "height:" + height);
        width = displaymetrics.widthPixels;
        Log.e("------", "width:" + width);


    }

    public static int getHeight() {
        return height;
    }

    public static int getWidth() {
        return width;
    }

//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
//        finish();
//    }
    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        MainActivity.setTabHost();
    }

    public void setTextForTextView(TextView tv, String text) {
        tv.setText(text);
    }

}


