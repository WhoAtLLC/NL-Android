package com.root.wishlist.model.registration;


import android.content.Context;
import android.util.Log;

import com.root.wishlist.util.RetrofitSingletonClass;
import com.root.wishlist.util.globalValues.RetrofitClass;
import com.root.wishlist.pojo.EmailStatus;
import com.root.wishlist.interfaces.WishListApiProcess;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

interface EmailVerification {

    interface OnCheckedStatus {
        void sendStatus(boolean status);

        void resendEmail(boolean status);
    }

    void getEmailStatus(Context context, String token, OnCheckedStatus onCheckedStatus);

    void reEmail(Context context, String token, OnCheckedStatus onCheckedStatus);
}

public class EmailVerificationStatusModel implements EmailVerification {

    @Override
    public void getEmailStatus(Context context,String token, final OnCheckedStatus onCheckedStatus) {

        RetrofitSingletonClass retrofitSingletonClass = RetrofitSingletonClass.getInstance(context);
        RetrofitClass retrofitClass = RetrofitSingletonClass.getRetrofitClassSingleInstance();

        WishListApiProcess wishListApiProcess = retrofitClass.getClient().create(WishListApiProcess.class);

        Call<EmailStatus> statusCall = wishListApiProcess.getUserStatus(token);
        statusCall.enqueue(new Callback<EmailStatus>() {
            @Override
            public void onResponse(Call<EmailStatus> call, Response<EmailStatus> response) {

                boolean status = false;
                int code = response.code();
                String message = response.message();
                if (code == 200) {
                    status = response.body().getResult();
                    Log.d("stat", String.valueOf(status));

                }
                onCheckedStatus.sendStatus(status);
            }

            @Override
            public void onFailure(Call<EmailStatus> call, Throwable t) {

                t.printStackTrace();
            }
        });
    }
    @Override
    public void reEmail(Context context, String token, final OnCheckedStatus onCheckedStatus) {
        RetrofitSingletonClass retrofitSingletonClass = RetrofitSingletonClass.getInstance(context);
        RetrofitClass retrofitClass = RetrofitSingletonClass.getRetrofitClassSingleInstance();
        WishListApiProcess wishListApiProcess = retrofitClass.getClient().create(WishListApiProcess.class);

        Call<EmailStatus> statusCall = wishListApiProcess.reSendEmail(token);
        statusCall.enqueue(new Callback<EmailStatus>() {
            @Override
            public void onResponse(Call<EmailStatus> call, Response<EmailStatus> response) {

                boolean status = false;
                int code = response.code();
                String message = response.message();
                if (code == 200) {
                    status = response.body().getResult();
                    Log.d("stat", String.valueOf(status));

                }
                onCheckedStatus.resendEmail(status);
            }

            @Override
            public void onFailure(Call<EmailStatus> call, Throwable t) {

            }
        });

    }
}
