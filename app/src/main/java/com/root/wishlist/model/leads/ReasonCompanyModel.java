package com.root.wishlist.model.leads;

import android.content.Context;

import com.root.wishlist.util.RetrofitSingletonClass;
import com.root.wishlist.util.globalValues.NetworkConnection;
import com.root.wishlist.util.globalValues.RetrofitClass;
import com.root.wishlist.pojo.Reasoncompany;
import com.root.wishlist.R;
import com.root.wishlist.interfaces.WishListApiProcess;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


interface reason {
    interface OnUpdateReason {
        void getReasonUpdate(String handle, String businessDiscussion, String businessAdditional);

        void networkMessage(String message);
    }

    void setReason(Context context, String token, OnUpdateReason onUpdateReason);
}

public class ReasonCompanyModel implements reason {



    @Override
    public void setReason(final Context context, String token, final OnUpdateReason onUpdateReason) {

        try {
            if (NetworkConnection.isNetworkAvailable(context)) {

                RetrofitSingletonClass retrofitSingletonClass = RetrofitSingletonClass.getInstance(context);
                RetrofitClass retrofitClass = RetrofitSingletonClass.getRetrofitClassSingleInstance();

                WishListApiProcess wishListApiProcess = retrofitClass.getClient().create(WishListApiProcess.class);
                Call<Reasoncompany> theirInterestCall = wishListApiProcess.getReasoncompanyCall(token);
                theirInterestCall.enqueue(new Callback<Reasoncompany>() {
                    @Override
                    public void onResponse(Call<Reasoncompany> call, Response<Reasoncompany> response) {

                        int code = response.code();
                        String message = response.message();
                        if (code == 200) {
                            String name = response.body().getHandle();
                            Reasoncompany reasoncompany = response.body();
                            onUpdateReason.getReasonUpdate(response.body().getHandle(), response.body().getBusinessDiscussion(), response.body().getBusinessAdditional());
                        }
                    }

                    @Override
                    public void onFailure(Call<Reasoncompany> call, Throwable t) {
                        onUpdateReason.networkMessage(context.getString(R.string.networkconnection));

                    }
                });

            } else {
                onUpdateReason.networkMessage(context.getString(R.string.networkconnection));
            }
        } catch (Exception e) {
            onUpdateReason.networkMessage(context.getString(R.string.networkconnection));
        }
    }
}

