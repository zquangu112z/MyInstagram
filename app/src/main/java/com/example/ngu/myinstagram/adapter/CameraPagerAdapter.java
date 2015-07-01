/*
* Created by Nicholas 06/05/2015 */

package com.example.ngu.myinstagram.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.ngu.myinstagram.fragment.CameraVideo;
import com.example.ngu.myinstagram.fragment.CameraGallery;
import com.example.ngu.myinstagram.fragment.CameraPhoto;


public class CameraPagerAdapter extends FragmentStatePagerAdapter {

    CharSequence Titles[];
    int NumbOfTabs;

//    private int[] drawablesIds = {
////            R.drawable.ic_action_map,//icon_tab_1_map,
////            R.drawable.ic_action_cloud,//icon_tab_2_notification,
////            R.drawable.ic_action_search,
////            R.drawable.ic_action_person//icon_tab_4_user,
//    };
//
//    public int getDrawableId(int position) {
//        //Here is only example for getting tab drawables
//        return drawablesIds[position];
//    }

    public CameraPagerAdapter(FragmentManager fm, CharSequence mTitles[],
                              int mNumbOfTabsumb) {
        super(fm);
        this.Titles = mTitles;
        this.NumbOfTabs = mNumbOfTabsumb;
    }

    // This method return the fragment for the every position in the View Pager

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return new CameraGallery();
            case 1:
                return new CameraPhoto();
            case 2:
                return new CameraVideo();
        }

        return null;
    }


    // This method return the titles for the Tabs in the Tab Strip

    @Override
    public CharSequence getPageTitle(int position) {
        return Titles[position];
    }

    // This method return the Number of tabs for the tabs Strip

    @Override
    public int getCount() {
        return NumbOfTabs;
    }

    @Override
    public int getItemPosition(Object object) {
        return super.getItemPosition(object);
    }
}
