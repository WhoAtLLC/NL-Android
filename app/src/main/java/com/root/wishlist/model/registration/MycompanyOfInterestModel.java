package com.root.wishlist.model.registration;

import android.content.Context;
import android.util.Log;

import com.root.wishlist.util.RetrofitSingletonClass;
import com.root.wishlist.util.globalValues.RetrofitClass;
import com.root.wishlist.interfaces.WishListApiProcess;

import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

interface MyCmpanyOfinterest {
    interface OnInterestload {
        void OnSucess(String message);
    }

    void myCompanyInterest(Context context, String token, HashMap<String, Object> stringObjectHashMap, OnInterestload onInterestload);
}

public class MycompanyOfInterestModel implements MyCmpanyOfinterest {


    @Override
    public void myCompanyInterest(Context context, String token, HashMap<String, Object> stringObjectHashMap, final OnInterestload onInterestload) {

        RetrofitSingletonClass retrofitSingletonClass = RetrofitSingletonClass.getInstance(context);
        RetrofitClass retrofitClass = RetrofitSingletonClass.getRetrofitClassSingleInstance();

        WishListApiProcess wishListApiProcess = retrofitClass.getClient().create(WishListApiProcess.class);
        Call<ResponseBody> selectnetworkBeanCall = wishListApiProcess.companiesOfinterest(token, stringObjectHashMap);
        selectnetworkBeanCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                int errorcode = response.code();

                String sucessmessage = "";
                if (errorcode == 201) {
                    sucessmessage = response.message();
                } else {
                    sucessmessage = "not upload";
                }
                onInterestload.OnSucess(sucessmessage);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

                t.printStackTrace();
                Log.e("exception", t.getMessage());

            }
        });
    }
}
