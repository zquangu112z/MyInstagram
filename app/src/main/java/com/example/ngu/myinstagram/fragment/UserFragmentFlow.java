package com.example.ngu.myinstagram.fragment;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.example.ngu.myinstagram.R;

public class UserFragmentFlow extends Fragment {
    ImageButton bt_user_flow_back;

    int status_bt_user_flow_save_original_picture = 1;
    int status_bt_user_flow_save_video = 0;
    int status_bt_user_flow_privacy_account=0;
    View rootView;
    RelativeLayout rl_user_flow_find_friend_facebook;
    RelativeLayout rl_user_flow_find_friend_contact;
    ImageButton bt_user_flow_save_original_picture;
    ImageButton bt_user_flow_save_video;
    ImageButton bt_user_flow_privacy_account;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_user_flow, container, false);
        //stated button
        bt_user_flow_back = (ImageButton) rootView.findViewById(R.id.bt_user_flow_back);
        rl_user_flow_find_friend_facebook = (RelativeLayout) rootView.findViewById(R.id.rl_user_flow_find_friend_facebook);
        rl_user_flow_find_friend_contact = (RelativeLayout) rootView.findViewById(R.id.rl_user_flow_find_friend_contact);
        bt_user_flow_save_original_picture = (ImageButton) rootView.findViewById(R.id.bt_user_flow_save_original_picture);
        bt_user_flow_save_video = (ImageButton) rootView.findViewById(R.id.bt_user_flow_save_video);
        bt_user_flow_privacy_account=(ImageButton) rootView.findViewById(R.id.bt_user_flow_privacy_account);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bt_user_flow_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                //transaction.remove(getParentFragment());

                transaction.replace(getId(), new UserFragment());
                transaction.commit();
            }
        });


        /*Find Facebook Friends*/
        rl_user_flow_find_friend_facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //To-Do
            }
        });

        /*Find Contacts*/
        rl_user_flow_find_friend_contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //To-Do
            }
        });

        /*Save Original Picture*/
        bt_user_flow_save_original_picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (status_bt_user_flow_save_original_picture % 2 == 1) {//start
                    bt_user_flow_save_original_picture.setBackgroundResource(R.drawable.ic_button_slide_selected);
                    //To-Do
                } else {//stop
                    bt_user_flow_save_original_picture.setBackgroundResource(R.drawable.ic_button_slide_unselected);
                    //To-Do
                }
                status_bt_user_flow_save_original_picture++;
            }
        });
        /*Save Videos After Posting*/
        bt_user_flow_save_video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (status_bt_user_flow_save_video % 2 == 1) {//start
                    bt_user_flow_save_video.setBackgroundResource(R.drawable.ic_button_slide_selected);
                    //To-Do
                } else {//stop
                    bt_user_flow_save_video.setBackgroundResource(R.drawable.ic_button_slide_unselected);
                    //To-Do
                }
                status_bt_user_flow_save_video++;
            }
        });
        /*Privacy Account*/
        bt_user_flow_privacy_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (status_bt_user_flow_privacy_account % 2 == 1) {//start
                    bt_user_flow_privacy_account.setBackgroundResource(R.drawable.ic_button_slide_selected);
                    //To-Do
                } else {//stop
                    bt_user_flow_privacy_account.setBackgroundResource(R.drawable.ic_button_slide_unselected);
                    //To-Do
                }
                status_bt_user_flow_privacy_account++;
            }
        });

    }
}
