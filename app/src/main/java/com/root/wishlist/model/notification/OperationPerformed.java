package com.root.wishlist.model.notification;

import android.content.Context;

import com.root.wishlist.util.RetrofitSingletonClass;
import com.root.wishlist.util.globalValues.RetrofitClass;
import com.root.wishlist.pojo.wantto.NotificationWantTo;
import com.root.wishlist.interfaces.WishListApiProcess;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

interface Operation {
    interface OnPerformedOperation {
        void getarchived(boolean archive);
        void getdecline(boolean decline);
        void getStatus(String status);
    }

    void onArchive(Context context, String token, int id, HashMap<String, Object> stringObjectHashMap, OnPerformedOperation onPerformedOperation);

    void onDelete(Context context, String token, int id, HashMap<String, Object> stringObjectHashMap, OnPerformedOperation onPerformedOperation);

    void onAccept(Context context, String token, int id, HashMap<String, Object> stringObjectHashMap, OnPerformedOperation onPerformedOperation);
}

public class OperationPerformed implements Operation {

    @Override
    public void onArchive(Context context,String token, int id, HashMap<String, Object> stringObjectHashMap, final OnPerformedOperation onPerformedOperation) {
        RetrofitSingletonClass retrofitSingletonClass = RetrofitSingletonClass.getInstance(context);
        RetrofitClass retrofitClass = RetrofitSingletonClass.getRetrofitClassSingleInstance();

        WishListApiProcess wishListApiProcess = retrofitClass.getClient().create(WishListApiProcess.class);
        Call<NotificationWantTo> NotificationWantToCall = wishListApiProcess.getArchive(token, id, stringObjectHashMap);
        NotificationWantToCall.enqueue(new Callback<NotificationWantTo>() {
            @Override
            public void onResponse(Call<NotificationWantTo> call, Response<NotificationWantTo> response) {

                int errorCode = response.code();
                if (errorCode == 200) {
                    onPerformedOperation.getarchived(response.body().getArchived());
                }
            }

            @Override
            public void onFailure(Call<NotificationWantTo> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    @Override
    public void onDelete(Context context, String token, int id, HashMap<String, Object> stringObjectHashMap, final OnPerformedOperation onPerformedOperation) {

        RetrofitSingletonClass retrofitSingletonClass = RetrofitSingletonClass.getInstance(context);
        RetrofitClass retrofitClass = RetrofitSingletonClass.getRetrofitClassSingleInstance();

        WishListApiProcess wishListApiProcess = retrofitClass.getClient().create(WishListApiProcess.class);
        Call<NotificationWantTo> NotificationWantToCall = wishListApiProcess.getdeleted(token, id, stringObjectHashMap);
        NotificationWantToCall.enqueue(new Callback<NotificationWantTo>() {
            @Override
            public void onResponse(Call<NotificationWantTo> call, Response<NotificationWantTo> response) {

                int errorCode = response.code();
                if (errorCode == 200) {
                    onPerformedOperation.getdecline(response.body().getDeletedRecipient());
                }

            }

            @Override
            public void onFailure(Call<NotificationWantTo> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    @Override
    public void onAccept(Context context, String token, int id, HashMap<String, Object> stringObjectHashMap, final OnPerformedOperation onPerformedOperation) {

        RetrofitSingletonClass retrofitSingletonClass = RetrofitSingletonClass.getInstance(context);
        RetrofitClass retrofitClass = RetrofitSingletonClass.getRetrofitClassSingleInstance();

        WishListApiProcess wishListApiProcess = retrofitClass.getClient().create(WishListApiProcess.class);
        Call<NotificationWantTo> NotificationWantToCall = wishListApiProcess.getAccepted(token, id, stringObjectHashMap);
        NotificationWantToCall.enqueue(new Callback<NotificationWantTo>() {
            @Override
            public void onResponse(Call<NotificationWantTo> call, Response<NotificationWantTo> response) {

                int errorCode = response.code();
                if (errorCode == 200) {
                    onPerformedOperation.getStatus(response.body().getStatus());
                }

            }

            @Override
            public void onFailure(Call<NotificationWantTo> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
