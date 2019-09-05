package com.root.wishlist.presentation.registration;

import android.content.Context;

import com.root.wishlist.database.SharedDatabase;
import com.root.wishlist.model.registration.MyWishListmodel;
import com.root.wishlist.pojo.mywishlist.Result;
import com.root.wishlist.view.MyWishListView;

import java.util.HashMap;
import java.util.List;

public class MyWishListpresentation implements MyWishListPresentationint, MyWishListmodel.OnClickMybusiness {

    MyWishListView myWishListView;
    MyWishListmodel mywishListinterface;
    SharedDatabase sharedDatabase;
    Context context;

    public MyWishListpresentation(MyWishListView myWishListView, Context context) {
        this.myWishListView = myWishListView;
        mywishListinterface = new MyWishListmodel();
        sharedDatabase = new SharedDatabase(context);
        this.context=context;
    }

    @Override
    public void myWishlistCompany(HashMap<String, Object> stringObjectHashMap) {
        mywishListinterface.myWishListCompany(context,"token " + sharedDatabase.getToken(), stringObjectHashMap, this);
    }

    @Override
    public void companiesOfInterest() {


        mywishListinterface.companiesofInterest(context,"token " + sharedDatabase.getToken(), this);

    }

    @Override
    public void companiesofInterest(List<Result> companiesofInterest, String
            myBusinessDiscussion, String myBusinessOtherInfo) {

        if (myWishListView != null) {
            myWishListView.companiesofInterest(companiesofInterest, myBusinessDiscussion, myBusinessOtherInfo);
        }
    }

    @Override
    public void onSucess(String message) {
        if (myWishListView != null) {
            myWishListView.navigateToNext(message);
        }
    }

}
