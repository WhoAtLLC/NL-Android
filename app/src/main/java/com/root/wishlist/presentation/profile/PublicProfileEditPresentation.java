package com.root.wishlist.presentation.profile;


import android.content.Context;

import com.root.wishlist.database.SharedDatabase;
import com.root.wishlist.model.profile.PublicProfileEdit;
import com.root.wishlist.view.PublicProfileEditView;

import java.util.HashMap;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class PublicProfileEditPresentation implements PublicProfileEditInterface, PublicProfileEdit.OnSucess {
    PublicProfileEditView publicProfileView;
    Context context;
    PublicProfileEdit publicProfileEdit;
    SharedDatabase sharedDatabase;

    public PublicProfileEditPresentation(Context context, PublicProfileEditView publicProfileView) {
        this.context = context;
        this.publicProfileView = publicProfileView;
        publicProfileEdit = new PublicProfileEdit();
        sharedDatabase = new SharedDatabase(context);
    }


    @Override
    public void sucess(String message) {

        if (publicProfileView != null) {
            publicProfileView.sucess(message);
        }
    }

    @Override
    public void editProfile(MultipartBody.Part imageFileBody, HashMap<String, RequestBody> map) {

        publicProfileEdit.editProfile(context,"token "+sharedDatabase.getToken(), imageFileBody,map, this);
    }
}
