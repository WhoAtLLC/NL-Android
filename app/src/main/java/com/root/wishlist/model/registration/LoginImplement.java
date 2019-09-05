package com.root.wishlist.model.registration;

import android.os.Handler;
import android.text.TextUtils;

public class LoginImplement implements Login {
    @Override
    public void userlogin(final String userName, final String userPassword, final OnLoginFinishedListener listener) {


        new Handler().post(new Runnable() {
            @Override
            public void run() {
                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                boolean error=false;
                if(TextUtils.isEmpty(userName))
                {
                    listener.userNameError("Please specify the email id.");
                    error=true;
                }
                else if(!userName.matches(emailPattern))
                {
                    listener.userNameError("Please specify the email id.");
                }
                else if(TextUtils.isEmpty(userPassword))
                {
                    listener.userPasswordError("Please specify the password.");
                    error=true;
                }
                else if(userPassword.length()<8)
                {
                    listener.userPasswordError("Please specify the password.");
                    error=true;
                }
                else if(!error)
                {

                    listener.sucess();
                }

            }
        });


    }

}
