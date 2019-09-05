package com.root.wishlist.model.members;


import android.content.Context;

import com.root.wishlist.util.RetrofitSingletonClass;
import com.root.wishlist.util.globalValues.RetrofitClass;
import com.root.wishlist.pojo.members.MyWishlistFragmentBean;
import com.root.wishlist.pojo.members.Result;
import com.root.wishlist.interfaces.WishListApiProcess;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

interface MyWishListFragment {
    interface OnMyWishListFragment {

        void getMyWishList(List<Result> myWishlistFragmentBeans);

    }

   void myWishListFragment(Context context,String UserName, String token, final OnMyWishListFragment onMyWishListFragment);
}
public class MyWishListFragmentModel implements MyWishListFragment{

    @Override
    public void myWishListFragment(Context context,String UserName,String token, final OnMyWishListFragment onMyWishListFragment) {
        RetrofitSingletonClass retrofitSingletonClass = RetrofitSingletonClass.getInstance(context);
        RetrofitClass retrofitClass = RetrofitSingletonClass.getRetrofitClassSingleInstance();

        WishListApiProcess myApiEndpointInterface = retrofitClass.getClient().create(WishListApiProcess.class);

        Call<MyWishlistFragmentBean> companyOfInterestBeanCall = myApiEndpointInterface.getMembersMyWishListFragment(token,UserName);
        companyOfInterestBeanCall.enqueue(new Callback<MyWishlistFragmentBean>() {
            @Override
            public void onResponse(Call<MyWishlistFragmentBean> call, Response<MyWishlistFragmentBean> response) {

                int statusCode = response.code();
                if (statusCode == 200) {

                    onMyWishListFragment.getMyWishList(response.body().getResults());


                }
            }

            @Override
            public void onFailure(Call<MyWishlistFragmentBean> call, Throwable t) {

                t.printStackTrace();
            }
        });
    }
}
