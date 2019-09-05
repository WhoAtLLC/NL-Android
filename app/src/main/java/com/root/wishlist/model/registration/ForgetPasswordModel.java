package com.root.wishlist.model.registration;


import android.os.Handler;
import android.text.TextUtils;

public class ForgetPasswordModel implements ForgetPassword {
    @Override
    public void forgetPassword(final String useremail, final setOnForgetPasswordListener listener) {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                boolean error = false;
                if (TextUtils.isEmpty(useremail)) {
                    listener.userEmailError("Please specify the email id");
                    error = true;
                } else if (!useremail.matches(emailPattern)) {
                    listener.userEmailError("Please specify the email id");
                    error = true;
                } else if (!error) {
                    listener.sucess();
                }

            }
        }, 1000);
    }
}
