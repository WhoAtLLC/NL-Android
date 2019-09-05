package com.root.wishlist.presentation.registration;


import android.content.Context;

import com.root.wishlist.database.SharedDatabase;
import com.root.wishlist.model.registration.MycompanyOfInterestModel;
import com.root.wishlist.view.MyWishListView;

import java.util.HashMap;

public class MyCompanyOfInterestPresentation implements CompaniesOfInterestInterface, MycompanyOfInterestModel.OnInterestload {
    Context context;
    SharedDatabase sharedDatabase;
    MycompanyOfInterestModel mycompanyOfInterestModel;
    MyWishListView myWishListView;

    public MyCompanyOfInterestPresentation(Context context, MyWishListView myWishListView) {
        this.myWishListView = myWishListView;
        this.context = context;
        sharedDatabase = new SharedDatabase(context);
        mycompanyOfInterestModel = new MycompanyOfInterestModel();
    }

    @Override
    public void myWishListInterest(HashMap<String, Object> stringObjectHashMap) {
        mycompanyOfInterestModel.myCompanyInterest(context,"Token " + sharedDatabase.getToken(), stringObjectHashMap, this);
    }

    @Override
    public void OnSucess(String message) {
        if (myWishListView != null) {
            myWishListView.navigateToNext(message);
        }
    }
}
