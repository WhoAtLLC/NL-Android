package com.root.wishlist.view.membersview;

import com.root.wishlist.pojo.members.Result;

import java.util.List;

public interface MywishListFragmentView {


    void getMyWishList(List<Result> myWishlistFragmentBeans);

    void networError(String coonection);

}
