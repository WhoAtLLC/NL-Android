package com.root.wishlist.view;


import com.root.wishlist.pojo.mywishlist.Result;

import java.util.List;

public interface MyWishListView {

    void companiesofInterest(List<Result> companiesofInterest, String myBusinessDiscussion, String myBusinessOtherInfo);

    void navigateToNext(String next);

    void networkError(String connection);
}
