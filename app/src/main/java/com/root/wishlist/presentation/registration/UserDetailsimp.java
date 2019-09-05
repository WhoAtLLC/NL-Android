package com.root.wishlist.presentation.registration;

import android.content.Context;

import com.root.wishlist.database.SharedDatabase;
import com.root.wishlist.model.registration.RegistrationStep2;
import com.root.wishlist.model.registration.UserDetailsmodel;
import com.root.wishlist.util.globalValues.NetworkConnection;
import com.root.wishlist.view.UserDetailsView;

import okhttp3.MultipartBody;

public class UserDetailsimp implements UserDetailsPresentation, UserDetailsmodel.onUserDetailsCheck {

    UserDetailsmodel userDetailsmodel;
    Context context;
    UserDetailsView userDetailsView;
    String firstName, lastName, screenName;
    MultipartBody.Part profileImage;
    RegistrationStep2 registrationStep2;
    SharedDatabase sharedDatabase;

    public UserDetailsimp(UserDetailsView userDetailsView, Context context) {
        this.context = context;
        this.userDetailsView = userDetailsView;
        userDetailsmodel = new UserDetailsmodel();
        registrationStep2 = new RegistrationStep2(context);
        sharedDatabase = new SharedDatabase(context);
    }


    @Override
    public void userDetails(MultipartBody.Part profileImage, String firstName, String lastName, String screenName) {
        this.profileImage = profileImage;
        this.firstName = firstName;
        this.lastName = lastName;
        this.screenName = screenName;
        userDetailsmodel.userDetailschecked(profileImage, firstName, lastName, screenName, this);
    }

    @Override
    public void firstName(String fname) {
        if (userDetailsView != null) {
            userDetailsView.setFirstnameError(fname);
        }

    }

    @Override
    public void lastName(String lname) {
        if (userDetailsView != null) {
            userDetailsView.setLastnameError(lname);
        }
    }

    @Override
    public void screenName(String snmae) {
        if (userDetailsView != null) {
            userDetailsView.setNicknameError(snmae);
        }
    }

    @Override
    public void sucess() {

        if (userDetailsView != null) {
            if (NetworkConnection.isNetworkAvailable(context))
                try {

                    registrationStep2.userDetails(profileImage, "token " + sharedDatabase.getToken(), firstName, lastName, screenName);
                } catch (Exception e) {
                    userDetailsView.checkNetworkConnection("No Internet connection!");
                }
        } else {
            userDetailsView.checkNetworkConnection("No Internet connection!");
        }
    }
}


