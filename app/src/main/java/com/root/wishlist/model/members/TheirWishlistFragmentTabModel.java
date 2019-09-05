package com.root.wishlist.model.members;

import android.content.Context;

import com.root.wishlist.util.RetrofitSingletonClass;
import com.root.wishlist.util.globalValues.NetworkConnection;
import com.root.wishlist.util.globalValues.RetrofitClass;
import com.root.wishlist.pojo.members.MyWishlistFragmentBean;
import com.root.wishlist.pojo.members.Result;
import com.root.wishlist.R;
import com.root.wishlist.interfaces.WishListApiProcess;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


interface TheirWishListFragment {
    interface OnTheirWishListFragment {

        void getTheirWishList(List<Result> resultList);

        void networkError(String message);

    }

    void theirWishListFragment(Context context, String UserName, String token, final OnTheirWishListFragment onTheirWishListFragment);
}


public class TheirWishlistFragmentTabModel implements TheirWishListFragment {

    @Override
    public void theirWishListFragment(final Context context, String UserName, String token, final OnTheirWishListFragment onTheirWishListFragment) {



        try {

            RetrofitSingletonClass retrofitSingletonClass = RetrofitSingletonClass.getInstance(context);
            RetrofitClass retrofitClass = RetrofitSingletonClass.getRetrofitClassSingleInstance();

            if (NetworkConnection.isNetworkAvailable(context)) {


                WishListApiProcess myApiEndpointInterface = retrofitClass.getClient().create(WishListApiProcess.class);
                Call<MyWishlistFragmentBean> companyOfInterestBeanCall = myApiEndpointInterface.getTheirMemberFragment(token, UserName);
                companyOfInterestBeanCall.enqueue(new Callback<MyWishlistFragmentBean>() {
                    @Override
                    public void onResponse(Call<MyWishlistFragmentBean> call, Response<MyWishlistFragmentBean> response) {

                        int statusCode = response.code();
                        if (statusCode == 200) {

                            onTheirWishListFragment.getTheirWishList(response.body().getResults());


                        }
                    }

                    @Override
                    public void onFailure(Call<MyWishlistFragmentBean> call, Throwable t) {

                        t.printStackTrace();
                        onTheirWishListFragment.networkError(context.getString(R.string.networkconnection));
                    }
                });
            } else {
                onTheirWishListFragment.networkError(context.getString(R.string.networkconnection));
            }
        } catch (Exception e) {
            onTheirWishListFragment.networkError(context.getString(R.string.networkconnection));
        }
    }
}
