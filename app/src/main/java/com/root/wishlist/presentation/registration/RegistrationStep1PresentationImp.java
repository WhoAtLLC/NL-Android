package com.root.wishlist.presentation.registration;


import android.content.Context;

import com.root.wishlist.util.globalValues.NetworkConnection;
import com.root.wishlist.model.registration.RegistrationStep1Model;
import com.root.wishlist.model.registration.RegistrationStep1ModelImp;
import com.root.wishlist.view.RegistrationStep1View;
import com.root.wishlist.model.registration.RegistrationStep1;

public class RegistrationStep1PresentationImp implements RegistrationStep1Presentation, RegistrationStep1Model.OnRegistrationFinishedListener {

    RegistrationStep1ModelImp registrationStep1ModelImp;
    RegistrationStep1View registrationStep1View;
    RegistrationStep1 registrationStep1;
    String username, userpassword;
    Context context;


    public RegistrationStep1PresentationImp(RegistrationStep1View registrationStep1View, Context context) {
        this.context = context;
        this.registrationStep1View = registrationStep1View;
        registrationStep1ModelImp = new RegistrationStep1ModelImp();
        registrationStep1 = new RegistrationStep1(context);


    }

    @Override
    public void registrationValidate(String username, String userpassword) {
        if (registrationStep1View != null) {

        }
        this.username = username;
        this.userpassword = userpassword;
        registrationStep1ModelImp.userlogin(username, userpassword, this);

    }

    @Override
    public void userNameError(String username) {
        if (registrationStep1View != null) {
            registrationStep1View.setUsernameError(username);
        }
    }

    @Override
    public void userPasswordError(String userpassword) {

        if (registrationStep1View != null) {
            registrationStep1View.setPasswordError(userpassword);
        }
    }

    @Override
    public void sucess() {
        if (registrationStep1View != null) {

            if (NetworkConnection.isNetworkAvailable(context)) {
                try {
                    registrationStep1.resisterStep1(username, userpassword);
                } catch (Exception e) {
                    registrationStep1View.setNetworkMessage("No Internet connection");
                }
            } else {
                registrationStep1View.setNetworkMessage("No Internet connection");
            }
        }
    }
}
