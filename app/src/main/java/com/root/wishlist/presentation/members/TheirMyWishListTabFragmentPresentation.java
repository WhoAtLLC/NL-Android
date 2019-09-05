package com.root.wishlist.presentation.members;


import android.content.Context;

import com.root.wishlist.database.SharedDatabase;
import com.root.wishlist.model.members.TheirWishlistFragmentTabModel;
import com.root.wishlist.pojo.members.Result;
import com.root.wishlist.view.membersview.MywishListFragmentView;

import java.util.List;

public class TheirMyWishListTabFragmentPresentation implements TheirWishListFragmentInterface, TheirWishlistFragmentTabModel.OnTheirWishListFragment {
    TheirWishlistFragmentTabModel theirWishlistFragmentTabModel;
    Context context;
    SharedDatabase sharedDatabase;
    MywishListFragmentView mywishListFragmentView;

    public TheirMyWishListTabFragmentPresentation(MywishListFragmentView mywishListFragmentView, Context context) {
        this.context = context;
        this.mywishListFragmentView = mywishListFragmentView;
        theirWishlistFragmentTabModel = new TheirWishlistFragmentTabModel();
        sharedDatabase = new SharedDatabase(context);

    }

    @Override
    public void loadTheirWishListFragment(String UserName) {
        if (mywishListFragmentView != null) {

        }
        String token = "Token " + sharedDatabase.getToken();
        theirWishlistFragmentTabModel.theirWishListFragment(context, UserName, token, this);


    }

    @Override
    public void getTheirWishList(List<Result> resultList) {
        if (resultList != null) {
            mywishListFragmentView.getMyWishList(resultList);
        }
    }

    @Override
    public void networkError(String message) {

        mywishListFragmentView.networError(message);

    }
}
