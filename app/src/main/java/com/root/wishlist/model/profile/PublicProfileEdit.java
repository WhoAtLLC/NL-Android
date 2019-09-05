package com.root.wishlist.model.profile;

import android.content.Context;

import com.root.wishlist.util.RetrofitSingletonClass;
import com.root.wishlist.util.globalValues.RetrofitClass;
import com.root.wishlist.pojo.profile.PrivateProfileBean;
import com.root.wishlist.interfaces.WishListApiProcess;

import java.util.HashMap;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

interface editProfile {
    interface OnSucess {
        void sucess(String message);
    }

    void editProfile(Context context, String token, MultipartBody.Part imageFileBody, HashMap<String, RequestBody> map, OnSucess onSucess);
}

public class PublicProfileEdit implements editProfile {

    @Override
    public void editProfile(Context context, String token, MultipartBody.Part imageFileBody, HashMap<String, RequestBody> map,
                            final OnSucess onSucess) {

        RetrofitSingletonClass retrofitSingletonClass = RetrofitSingletonClass.getInstance(context);
        RetrofitClass retrofitClass = RetrofitSingletonClass.getRetrofitClassSingleInstance();


        WishListApiProcess myApiEndpointInterface = retrofitClass.getClient().create(WishListApiProcess.class);
        Call<PrivateProfileBean> companyOfInterestBeanCall = myApiEndpointInterface.getPrivateProfileEdit(token, imageFileBody, map);
        companyOfInterestBeanCall.enqueue(new Callback<PrivateProfileBean>() {
            @Override
            public void onResponse(Call<PrivateProfileBean> call, Response<PrivateProfileBean> response) {

                int statusCode = response.code();
                if (statusCode == 200) {
                    onSucess.sucess("Upload Successfully");

                }
            }

            @Override
            public void onFailure(Call<PrivateProfileBean> call, Throwable t) {

                t.printStackTrace();
            }
        });
    }
}

