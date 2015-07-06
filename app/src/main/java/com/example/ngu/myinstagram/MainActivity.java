package com.example.ngu.myinstagram;

import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.example.ngu.myinstagram.activity.CameraActivity;
import com.example.ngu.myinstagram.activity.HomeActivity;
import com.example.ngu.myinstagram.activity.NotificationActivity;
import com.example.ngu.myinstagram.activity.SearchActivity;
import com.example.ngu.myinstagram.activity.UserActivity;


public class MainActivity extends TabActivity {
    int tab_num = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //loadTabs();
        final TabHost tabHost = (TabHost) findViewById(android.R.id.tabhost);
        tabHost.setup();
        TabHost.TabSpec spec1, spec2, spec3, spec4, spec5;
        spec1 = tabHost.newTabSpec("tab_id_1").setIndicator("", getApplicationContext().getResources().getDrawable(R.drawable.tab_home_selector)).setContent(new Intent().setClass(this, HomeActivity.class));
        spec2 = tabHost.newTabSpec("tab_id_2").setIndicator("", getApplicationContext().getResources().getDrawable(R.drawable.tab_search_selector)).setContent(new Intent().setClass(this, SearchActivity.class));
        spec3 = tabHost.newTabSpec("tab_id_3").setIndicator("", getApplicationContext().getResources().getDrawable(R.drawable.tab_camera_selector)).setContent(new Intent().setClass(this, NotificationActivity.class));//new Intent().setClass(this, CameraActivity.class)
        spec4 = tabHost.newTabSpec("tab_id_4").setIndicator("", getApplicationContext().getResources().getDrawable(R.drawable.tab_notification_selector)).setContent(new Intent().setClass(this, NotificationActivity.class));
        spec5 = tabHost.newTabSpec("tab_id_5").setIndicator("", getApplicationContext().getResources().getDrawable(R.drawable.tab_user_selector)).setContent(new Intent().setClass(this, UserActivity.class));
        tabHost.addTab(spec1);
        tabHost.addTab(spec2);
        tabHost.addTab(spec3);
        tabHost.addTab(spec4);
        tabHost.addTab(spec5);
        setTabColor(tabHost);

        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                setTabColor(tabHost);

                //sau khi o cameara activity ve thi tra ve dung activity truoc do
                if (tabHost.getCurrentTab() == 2) {

                    tabHost.setCurrentTab(tab_num);
                    startActivity(new Intent(getApplicationContext(), CameraActivity.class));
                }
                tab_num = tabHost.getCurrentTab();
            }
        });

    }

    //doi mau cho tab
    public static void setTabColor(TabHost tabhost) {
        for (int i = 0; i < tabhost.getTabWidget().getChildCount(); i++) {
            tabhost.getTabWidget().getChildAt(i).setBackgroundColor(Color.parseColor("#232728")); //unselected
        }
        tabhost.getTabWidget().getChildAt(2).setBackgroundColor(Color.parseColor("#105687"));// camera unselected
        if (tabhost.getCurrentTab() == 2) {
            tabhost.getTabWidget().getChildAt(tabhost.getCurrentTab()).setBackgroundColor(Color.parseColor("#138ade"));//camera selected
        } else {
            tabhost.getTabWidget().getChildAt(tabhost.getCurrentTab()).setBackgroundColor(Color.parseColor("#060807")); // selected
        }
    }

}
