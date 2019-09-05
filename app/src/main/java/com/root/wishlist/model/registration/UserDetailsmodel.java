package com.root.wishlist.model.registration;

import android.os.Handler;
import android.text.TextUtils;

import okhttp3.MultipartBody;


interface UserDetailsmodelint {
    interface onUserDetailsCheck {
        void firstName(String fname);

        void lastName(String lname);

        void screenName(String snmae);

        void sucess();
    }

    void userDetailschecked(MultipartBody.Part imageFileBody,String firstName, String lastname, String nickname, onUserDetailsCheck onUserDetailsCheck);
}

public class UserDetailsmodel implements UserDetailsmodelint {


    @Override
    public void userDetailschecked(MultipartBody.Part imageFileBody,final String firstName, final String lastName, final String screenName, final onUserDetailsCheck onUserDetailsCheck) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                boolean checked = false;
                if (TextUtils.isEmpty(firstName)) {
                    onUserDetailsCheck.firstName("Please enter your first name.");
                    checked = true;
                } else if (TextUtils.isEmpty(lastName)) {
                    onUserDetailsCheck.lastName("Please enter your last name.");
                    checked = true;
                } else if (TextUtils.isEmpty(screenName)) {
                    onUserDetailsCheck.screenName("Please enter your desired screen name.");
                    checked = true;
                } else if (screenName.length() < 4) {
                    onUserDetailsCheck.screenName("Screen Name should be at least 4 characters.");
                    checked = true;
                } else if (!checked) {
                    onUserDetailsCheck.sucess();
                }
            }
        }, 1000);
    }
}

