package com.example.ngu.myinstagram.activity;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.ngu.myinstagram.MainActivity;
import com.example.ngu.myinstagram.R;


public class SearchActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        MainActivity.setTabHost();
    }
}
