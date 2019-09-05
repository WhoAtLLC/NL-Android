package com.root.wishlist.model.registration;

import android.content.Context;
import android.util.Log;

import com.root.wishlist.util.RetrofitSingletonClass;
import com.root.wishlist.util.globalValues.RetrofitClass;
import com.root.wishlist.pojo.MyWishListInterest;
import com.root.wishlist.pojo.mywishlist.Result;
import com.root.wishlist.interfaces.WishListApiProcess;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


interface MywishListinterface {

    interface OnClickMybusiness {
        void companiesofInterest(List<Result> companiesofInterest, String myBusinessDiscussion, String myBusinessOtherInfo);

        void onSucess(String message);

    }

    void companiesofInterest(Context context,String token, OnClickMybusiness onClickMybusiness);

    //void myWishListinfo(String token, HashMap<String, Object> stringObjectHashMap, OnClickMybusiness onClickMybusiness);

    void myWishListCompany(Context context, String token, HashMap<String, Object> stringObjectHashMap, OnClickMybusiness onClickMybusiness);
}

public class MyWishListmodel implements MywishListinterface {


    @Override
    public void companiesofInterest(Context context, String token, final OnClickMybusiness onClickMybusiness) {

        RetrofitSingletonClass retrofitSingletonClass = RetrofitSingletonClass.getInstance(context);
        RetrofitClass retrofitClass = RetrofitSingletonClass.getRetrofitClassSingleInstance();

        final WishListApiProcess wishListApiProcess = retrofitClass.getClient().create(WishListApiProcess.class);
        Call<MyWishListInterest> selectnetworkBeanCall = wishListApiProcess.MY_WISH_LIST_INTEREST_CALL(token);
        selectnetworkBeanCall.enqueue(new Callback<MyWishListInterest>() {
            @Override
            public void onResponse(Call<MyWishListInterest> call, Response<MyWishListInterest> response) {

                List<Result> companiesofInterest = new ArrayList<>();
                int errorcode = response.code();
                String message = response.message();
                String sucessmessage = "";
                if (errorcode == 200) {

                    onClickMybusiness.companiesofInterest(response.body().getCompaniesofInterest(), response.body().getMyBusinessDiscussion(), response.body().getMyBusinessOtherInfo());
                }
            }

            @Override
            public void onFailure(Call<MyWishListInterest> call, Throwable t) {

                t.printStackTrace();
                Log.e("exception", t.getMessage());

            }
        });
    }

    @Override
    public void myWishListCompany(Context context, final String token, final HashMap<String, Object> stringObjectHashMap, final OnClickMybusiness onClickMybusiness) {

        RetrofitSingletonClass retrofitSingletonClass = RetrofitSingletonClass.getInstance(context);
        RetrofitClass retrofitClass = RetrofitSingletonClass.getRetrofitClassSingleInstance();

        WishListApiProcess wishListApiProcess = retrofitClass.getClient().create(WishListApiProcess.class);
        Call<ResponseBody> selectnetworkBeanCall = wishListApiProcess.RESPONSE_BODY_CALL(token, stringObjectHashMap);
        selectnetworkBeanCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                int errorcode = response.code();
                String message = response.message();
                String sucessmessage = "";
                if (errorcode == 206) {
                    sucessmessage = response.message();

                    Log.d("kgndskg", sucessmessage);

                } else {
                    sucessmessage = "not upload";
                }
                onClickMybusiness.onSucess(sucessmessage);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

                t.printStackTrace();
                Log.e("exception", t.getMessage());

            }
        });
    }


}
