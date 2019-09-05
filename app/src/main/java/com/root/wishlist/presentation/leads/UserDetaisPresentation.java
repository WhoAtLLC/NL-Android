package com.root.wishlist.presentation.leads;

import android.content.Context;

import com.root.wishlist.database.SharedDatabase;
import com.root.wishlist.model.leads.UserDetailsModule;
import com.root.wishlist.view.UserInformationView;


public class UserDetaisPresentation implements UserDetailsInterface, UserDetailsModule.SetuserDetails {

    UserDetailsModule userDetailsModule;
    SharedDatabase sharedDatabase;
    Context context;
    UserInformationView userDetailsView;

    public UserDetaisPresentation(UserInformationView userDetailsView, Context context) {
        this.context = context;
        this.userDetailsView = userDetailsView;
        sharedDatabase = new SharedDatabase(context);
        userDetailsModule = new UserDetailsModule();
    }

    @Override
    public void setUserDetails(String handle, String shortBio, String bio, String image, String businessDiscussion, String businessAdditional) {
        if (userDetailsView != null) {
            userDetailsView.setUserInformation(handle, shortBio, bio, image, businessDiscussion, businessAdditional);
        }
    }

    @Override
    public void setNotificationProfile(String title,String firstName, String lastName, String handle, String email, String shortBio, String phone, String image, String businessDiscussion, String businessAdditional,String bio) {

        if (userDetailsView != null) {
            userDetailsView.getUserProfile(title,firstName, lastName, handle,
                    email, shortBio, phone, image, businessDiscussion, businessAdditional,bio);
        }
    }


    @Override
    public void setUser(String username) {
        userDetailsModule.getUserDetail(context,username, "Token " + sharedDatabase.getToken(), this);
    }

    @Override
    public void notificationUser(String username) {

        userDetailsModule.getNotificationProfile(context,username,"token "+sharedDatabase.getToken(),this);
    }

    @Override
    public void memberUserDetails(String userName) {

    }
}