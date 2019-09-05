package com.root.wishlist.model.leads;

import android.content.Context;

import com.root.wishlist.util.RetrofitSingletonClass;
import com.root.wishlist.util.globalValues.RetrofitClass;
import com.root.wishlist.pojo.leads.getintroduce.RequesttomeetBean;
import com.root.wishlist.interfaces.WishListApiProcess;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

interface ResonMessage {
    interface OnReasonSend {
        void returnMessage(String message);
    }

    void getReasonMessage(Context context, String token, HashMap<String, Object> stringObjectHashMap);
}

public class ResonToDetails implements ResonMessage {


    @Override
    public void getReasonMessage(Context context,String token, HashMap<String, Object> stringObjectHashMap) {

        RetrofitSingletonClass retrofitSingletonClass = RetrofitSingletonClass.getInstance(context);
        RetrofitClass retrofitClass = RetrofitSingletonClass.getRetrofitClassSingleInstance();

        WishListApiProcess wishListApiProcess = retrofitClass.getClient().create(WishListApiProcess.class);
        Call<RequesttomeetBean> sendReason = wishListApiProcess.sendReason(token, stringObjectHashMap);
        sendReason.enqueue(new Callback<RequesttomeetBean>() {
            @Override
            public void onResponse(Call<RequesttomeetBean> call, Response<RequesttomeetBean> response) {

                int errorCode = response.code();
                if (errorCode == 201) {
                    String message = response.message();
                    int requestID = response.body().getRequestID();
                    String status = response.body().getStatus();
                    String category = response.body().getCategory();
                    int author = response.body().getAuthor();
                }
            }

            @Override
            public void onFailure(Call<RequesttomeetBean> call, Throwable t) {

            }
        });
    }
}
