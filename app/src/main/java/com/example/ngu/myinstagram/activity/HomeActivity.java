package com.example.ngu.myinstagram.activity;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.example.ngu.myinstagram.R;


public class HomeActivity extends ActionBarActivity {
    ImageButton bt_box_open;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        bt_box_open = (ImageButton) findViewById(R.id.bt_box_open);
        bt_box_open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //To-Do
            }
        });
    }
}
