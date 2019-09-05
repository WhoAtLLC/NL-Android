package com.root.wishlist.presentation.leads;


import android.content.Context;

import com.root.wishlist.database.SharedDatabase;
import com.root.wishlist.model.leads.GetTheirInterest;
import com.root.wishlist.pojo.wantto.Comment;
import com.root.wishlist.view.TheirInterestView;

import java.util.List;

public class TheirIntrestPresentation implements TheirInterestInterface, GetTheirInterest.OnTheirInterestClick {

    GetTheirInterest getTheirInterest;
    SharedDatabase sharedDatabase;
    Context context;
    TheirInterestView theirInterestView;

    public TheirIntrestPresentation(TheirInterestView theirInterestView, Context context) {
        this.context = context;
        this.theirInterestView = theirInterestView;
        sharedDatabase = new SharedDatabase(context);
        getTheirInterest = new GetTheirInterest();
    }

    @Override
    public void setTheirInterest(String username) {
        getTheirInterest.setInterest(context, username, "Token " + sharedDatabase.getToken(), this);

    }

    @Override
    public void wantToBusiness(int userId) {
        getTheirInterest.wantToBusinessInfo(context, userId, "token " + sharedDatabase.getToken(), this);
    }

    @Override
    public void getTheirInterest(String handle, String shortBio, String bio, String image, String businessDiscussion, String businessAdditional) {
        if (theirInterestView != null) {
            theirInterestView.message(handle, shortBio, bio, image, businessDiscussion, businessAdditional);
        }
    }

    @Override
    public void networkmessage(String connection) {
        if (theirInterestView != null)
            theirInterestView.networkMessage(connection);

    }

    @Override
    public void getWanttoBusinessInfo(String status, String whyIntroReason, String howIntroReason, List<Comment> comments) {
        if (theirInterestView != null) {
            theirInterestView.businessInfo(status, whyIntroReason, howIntroReason, comments);
        }
    }
}
