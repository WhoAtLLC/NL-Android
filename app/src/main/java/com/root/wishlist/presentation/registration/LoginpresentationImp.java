package com.root.wishlist.presentation.registration;

import android.content.Context;

import com.root.wishlist.util.globalValues.NetworkConnection;
import com.root.wishlist.model.registration.Login;
import com.root.wishlist.model.registration.LoginImplement;
import com.root.wishlist.view.LoginView;
import com.root.wishlist.model.registration.LoginProcess;


public class LoginpresentationImp implements LoginPresentationInt, Login.OnLoginFinishedListener {

    Login loginImplement;
    LoginView loginView;
    LoginProcess loginProcess;
    String username, userpassword;
    Context context;

    public LoginpresentationImp(LoginView loginView, Context context) {
        this.context = context;
        this.loginView = loginView;
        loginImplement = new LoginImplement();
        loginProcess = new LoginProcess(context);

    }

    @Override
    public void loginValidate(String username, String userpassword) {
        if (loginView != null) {

        }
        this.username = username;
        this.userpassword = userpassword;
        loginImplement.userlogin(username, userpassword, this);
    }


    @Override
    public void userNameError(String username) {
        if (loginView != null) {
            loginView.setUsernameError(username);
        }

    }

    @Override
    public void userPasswordError(String userpassword) {
        if (loginView != null) {
            loginView.setPasswordError(userpassword);
        }
    }

    @Override
    public void sucess() {
        if (loginView != null) {
            if (NetworkConnection.isNetworkAvailable(context)) {
                try {

                    loginProcess.loginProcess(username, userpassword);
                } catch (Exception e) {
                    loginView.checkNetworkConnection("No Internet connection!");
                }
            } else {
                loginView.checkNetworkConnection("No Internet connection!");
            }
        }

    }


}
