package com.root.wishlist.model.registration;

import android.content.Context;
import android.os.Handler;
import android.util.Log;

import com.root.wishlist.database.SharedDatabase;
import com.root.wishlist.util.RetrofitSingletonClass;
import com.root.wishlist.util.globalValues.RetrofitClass;
import com.root.wishlist.pojo.SelectnetworkBean;
import com.root.wishlist.interfaces.WishListApiProcess;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SelectNetwork implements SelectNetworkInt {
    String networkName = null;
    Context context;
    SharedDatabase sharedDatabase;

    public SelectNetwork(Context context) {
        this.context = context;
        sharedDatabase = new SharedDatabase(context);
    }


    @Override
    public void selectedNetwork(final String network, final selectNetwork selectNetwork) {
        String message = networkUpdate(network);
        new Handler().post(new Runnable() {
            @Override
            public void run() {

                RetrofitSingletonClass retrofitSingletonClass = RetrofitSingletonClass.getInstance(context);
                RetrofitClass retrofitClass = RetrofitSingletonClass.getRetrofitClassSingleInstance();

                final WishListApiProcess wishListApiProcess = retrofitClass.getClient().create(WishListApiProcess.class);
                Call<SelectnetworkBean> selectnetworkBeanCall = wishListApiProcess.userNetwork("Token " + sharedDatabase.getToken(), network);
                selectnetworkBeanCall.enqueue(new Callback<SelectnetworkBean>() {
                    @Override
                    public void onResponse(Call<SelectnetworkBean> call, Response<SelectnetworkBean> response) {
                        int errorcode = response.code();
                        String message = response.message();
                        if (errorcode == 200) {
                            selectNetwork.setOnSelectnetwor(response.body().getNetworkStatus());
                            Log.d("message", response.body().getNetworkStatus());
                            sharedDatabase.addStep("VerificationScree");
                        } else {
                            selectNetwork.setOnSelectnetwor("false");
                        }
                    }

                    @Override
                    public void onFailure(Call<SelectnetworkBean> call, Throwable t) {

                        Log.e("exception", t.getMessage());

                    }
                });

            }
        });

    }

    @Override
    public void getSelectedNetwork(String network, final selectNetwork selectNetwork) {
        RetrofitSingletonClass retrofitSingletonClass = RetrofitSingletonClass.getInstance(context);
        RetrofitClass retrofitClass = RetrofitSingletonClass.getRetrofitClassSingleInstance();

        final WishListApiProcess wishListApiProcess = retrofitClass.getClient().create(WishListApiProcess.class);
        Call<SelectnetworkBean> selectnetworkBeanCall = wishListApiProcess.selectednetwork("Token " + sharedDatabase.getToken());
        selectnetworkBeanCall.enqueue(new Callback<SelectnetworkBean>() {
            @Override
            public void onResponse(Call<SelectnetworkBean> call, Response<SelectnetworkBean> response) {

                int errorcode = response.code();
                if (errorcode == 200) {
                    selectNetwork.setOnSelectnetwor(response.body().getNetworkStatus());
                }
            }

            @Override
            public void onFailure(Call<SelectnetworkBean> call, Throwable t) {

                Log.e("exception", t.getMessage());

            }
        });
    }


    public String networkUpdate(String network) {


        return network;
    }
}
