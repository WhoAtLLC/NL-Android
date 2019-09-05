package com.root.wishlist.presentation.registration;


import android.content.Context;

import com.root.wishlist.model.registration.FacebookUserModel;
import com.root.wishlist.view.FacebookUser;

import java.util.HashMap;

public class FacebookTokenPresentation implements FacebookToken, FacebookUserModel.userToken {

    FacebookUser facebookUser;
    Context context;
    FacebookUserModel facebookUserModel;

    public FacebookTokenPresentation(FacebookUser facebookUser, Context context) {
        this.context = context;
        this.facebookUser = facebookUser;
        facebookUserModel = new FacebookUserModel();
    }


    @Override
    public void setUserToken(String token) {
        facebookUser.userToken(token);
    }

    @Override
    public void userToken(HashMap<String, String> token) {

        facebookUserModel.getusertoken(context,token, this);
    }
}
