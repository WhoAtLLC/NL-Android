package com.root.wishlist.model.registration;


import android.content.Context;

import com.root.wishlist.util.RetrofitSingletonClass;
import com.root.wishlist.util.globalValues.NetworkConnection;
import com.root.wishlist.util.globalValues.RetrofitClass;
import com.root.wishlist.util.globalValues.WishlistProgressDialog;
import com.root.wishlist.interfaces.UpdateMessage;
import com.root.wishlist.interfaces.WishListApiProcess;
import com.root.wishlist.pojo.registration.ForgetpasswordBean;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgetPasswordProcess {

    Context context;
    WishlistProgressDialog wishlistProgressDialog;

    public ForgetPasswordProcess(Context context) {
        this.context = context;
        wishlistProgressDialog = new WishlistProgressDialog(context);
    }


    public void getForgetPassword(String email) {


        wishlistProgressDialog.dialogShow();
        if (NetworkConnection.isNetworkAvailable(context)) {
            try {

                RetrofitSingletonClass retrofitSingletonClass = RetrofitSingletonClass.getInstance(context);
                RetrofitClass retrofitClass = RetrofitSingletonClass.getRetrofitClassSingleInstance();

                WishListApiProcess wishListApiProcess = retrofitClass.getClient().create(WishListApiProcess.class);
                Call<ForgetpasswordBean> userCall = wishListApiProcess.forgetPassword(email);
                userCall.enqueue(new Callback<ForgetpasswordBean>() {
                    @Override
                    public void onResponse(Call<ForgetpasswordBean> call, Response<ForgetpasswordBean> response) {

                        wishlistProgressDialog.dismissDialog();
                        int errorCode = response.code();
                        wishlistProgressDialog.dismissDialog();
                        String message = "";

                        if (errorCode == 201) {
                            message = response.message();
                        } else if (errorCode == 400) {

                            message = "No record of that email. Please try again or contact support@whoat.io.";
                        }

                        UpdateMessage updateMessage = (UpdateMessage) context;
                        updateMessage.loginStatus(message);
                    }

                    @Override
                    public void onFailure(Call<ForgetpasswordBean> call, Throwable t) {

                    }
                });
            } catch (Exception e) {
                wishlistProgressDialog.dismissDialog();
                UpdateMessage updateMessage = (UpdateMessage) context;
                updateMessage.loginStatus("Int");
            }
        } else {
            wishlistProgressDialog.dismissDialog();
            UpdateMessage updateMessage = (UpdateMessage) context;
            updateMessage.loginStatus("Int");
        }
    }
}
