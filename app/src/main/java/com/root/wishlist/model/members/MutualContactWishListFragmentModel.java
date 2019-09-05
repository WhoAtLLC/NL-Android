package com.root.wishlist.model.members;


import android.content.Context;

import com.root.wishlist.util.RetrofitSingletonClass;
import com.root.wishlist.util.globalValues.RetrofitClass;
import com.root.wishlist.pojo.members.MutualContactListBeans;
import com.root.wishlist.pojo.members.MutualContactResult;
import com.root.wishlist.interfaces.WishListApiProcess;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


interface MutualContactTabFragment {
    interface OnMutualContactListFragment {

        void getMutualFragmentList(List<MutualContactResult> myWishlistFragmentBeans);

    }

    void mutualContactListFragment(Context context,int pageNumber, String UserName, String token, final OnMutualContactListFragment onMutualContactListFragment);
}


public class MutualContactWishListFragmentModel implements MutualContactTabFragment {

    @Override
    public void mutualContactListFragment(Context context,int pageNumber, String UserName, String token, final OnMutualContactListFragment onMutualContactListFragment) {
        RetrofitSingletonClass retrofitSingletonClass = RetrofitSingletonClass.getInstance(context);
        RetrofitClass retrofitClass = RetrofitSingletonClass.getRetrofitClassSingleInstance();

        WishListApiProcess myApiEndpointInterface = retrofitClass.getClient().create(WishListApiProcess.class);

        Call<MutualContactListBeans> companyOfInterestBeanCall = myApiEndpointInterface.mutualContactMemberFragment(token, UserName, pageNumber);
        companyOfInterestBeanCall.enqueue(new Callback<MutualContactListBeans>() {
            @Override
            public void onResponse(Call<MutualContactListBeans> call, Response<MutualContactListBeans> response) {

                int statusCode = response.code();
                if (statusCode == 200) {

                    onMutualContactListFragment.getMutualFragmentList(response.body().getResults());


                }
            }

            @Override
            public void onFailure(Call<MutualContactListBeans> call, Throwable t) {

                t.printStackTrace();
            }
        });

    }
}