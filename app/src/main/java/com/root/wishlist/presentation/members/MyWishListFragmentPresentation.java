package com.root.wishlist.presentation.members;


import android.content.Context;

import com.root.wishlist.database.SharedDatabase;
import com.root.wishlist.model.members.MyWishListFragmentModel;
import com.root.wishlist.pojo.members.Result;
import com.root.wishlist.view.membersview.MywishListFragmentView;

import java.util.List;

public class MyWishListFragmentPresentation implements MyWishListFragmentInterface, MyWishListFragmentModel.OnMyWishListFragment {

    MyWishListFragmentModel myWishListFragmentModel;
    Context context;
    SharedDatabase sharedDatabase;
    MywishListFragmentView mywishListFragmentView;

    public MyWishListFragmentPresentation(MywishListFragmentView mywishListFragmentView, Context context) {
        this.context = context;
        this.mywishListFragmentView = mywishListFragmentView;
        myWishListFragmentModel = new MyWishListFragmentModel();
        sharedDatabase = new SharedDatabase(context);

    }


    @Override
    public void getMyWishList(List<Result> myWishlistFragmentBeans) {
        if(myWishlistFragmentBeans!=null){
            mywishListFragmentView.getMyWishList(myWishlistFragmentBeans);
        }


    }

    @Override
    public void loadMyWishListFragment(String  UserName) {
        if (mywishListFragmentView != null) {

        }
        String token="Token "+sharedDatabase.getToken();
        myWishListFragmentModel.myWishListFragment(context,UserName,token, this);


    }
}