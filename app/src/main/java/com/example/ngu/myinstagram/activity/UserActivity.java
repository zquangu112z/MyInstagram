package com.example.ngu.myinstagram.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.ngu.myinstagram.MainActivity;
import com.example.ngu.myinstagram.R;
import com.example.ngu.myinstagram.fragment.UserFragment;

public class UserActivity extends ActionBarActivity {
Fragment fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        FragmentManager fragmentManager=getFragmentManager();
        FragmentTransaction transaction=fragmentManager.beginTransaction();
        transaction.add(R.id.fg_fragment_user, new UserFragment());
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        MainActivity.setTabHost();
    }
}
