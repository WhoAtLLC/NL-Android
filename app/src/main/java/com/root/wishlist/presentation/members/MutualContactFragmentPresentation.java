package com.root.wishlist.presentation.members;


import android.content.Context;

import com.root.wishlist.database.SharedDatabase;
import com.root.wishlist.model.members.MutualContactWishListFragmentModel;
import com.root.wishlist.pojo.members.MutualContactResult;
import com.root.wishlist.view.membersview.MutualContactView;

import java.util.List;

public class MutualContactFragmentPresentation implements MutualContactFragmentInterface, MutualContactWishListFragmentModel.OnMutualContactListFragment {
    MutualContactView mMutualContactView;
    Context context;
    MutualContactWishListFragmentModel mutualContactWishListFragmentModel;
    SharedDatabase sharedDatabase;

    public MutualContactFragmentPresentation(MutualContactView mutualContactView, Context context) {
        this.mMutualContactView = mutualContactView;
        this.context = context;
        sharedDatabase = new SharedDatabase(context);
        mutualContactWishListFragmentModel = new MutualContactWishListFragmentModel();


    }

    @Override
    public void loadMyMutualContactFragment(int pageNumber, String UserName) {
        if (mMutualContactView != null) {

        }

        mutualContactWishListFragmentModel.mutualContactListFragment(context,pageNumber, UserName, "Token " + sharedDatabase.getToken(), this);
    }

    @Override
    public void getMutualFragmentList(List<MutualContactResult> myWishlistFragmentBeans) {
        if (myWishlistFragmentBeans != null) {
            mMutualContactView.getMutualContactWishList(myWishlistFragmentBeans);
        }
    }
}
