package com.root.wishlist.model.registration;

import android.os.Handler;
import android.text.TextUtils;

public class RegistrationStep1ModelImp implements RegistrationStep1Model {


    @Override
    public void userlogin(final String userName, final String userPassword, final OnRegistrationFinishedListener listener) {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                boolean error = false;
                if (TextUtils.isEmpty(userName)) {
                    listener.userNameError("Please enter valid email.");
                    error = true;
                } else if (!userName.matches(emailPattern)) {
                    listener.userNameError("Please enter valid email.");
                } else if (TextUtils.isEmpty(userPassword)) {
                    listener.userPasswordError("Please enter password.");
                    error = true;
                } else if (userPassword.length() < 8) {
                    listener.userPasswordError("Password should be at least 8 characters.");
                    error = true;
                } else if (!error) {
                    listener.sucess();
                }

            }
        }, 1000);

    }
}
