package com.root.wishlist.model.registration;


import android.content.Context;

import com.root.wishlist.database.SharedDatabase;
import com.root.wishlist.util.RetrofitSingletonClass;
import com.root.wishlist.util.globalValues.NetworkConnection;
import com.root.wishlist.util.globalValues.RetrofitClass;
import com.root.wishlist.util.globalValues.WishlistProgressDialog;
import com.root.wishlist.interfaces.UpdateMessage;
import com.root.wishlist.interfaces.WishListApiProcess;
import com.root.wishlist.pojo.registration.Loginbean;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginProcess {

    Context context;
    SharedDatabase sharedDatabase;
    WishlistProgressDialog wishlistProgressDialog;

    public LoginProcess(Context context) {
        this.context = context;
        wishlistProgressDialog = new WishlistProgressDialog(context);
        sharedDatabase = new SharedDatabase(context);

    }

    public void loginProcess(String email, String password) {


        wishlistProgressDialog.dialogShow();
        try {
            if (NetworkConnection.isNetworkAvailable(context)) {

                /*RetrofitSingletonClass retrofitSingletonClass = RetrofitSingletonClass.getInstance(context);
                RetrofitClass retrofitClass = RetrofitSingletonClass.getRetrofitClassSingleInstance();*/

                WishListApiProcess wishListApiProcess = new RetrofitClass(context).getClient().create(WishListApiProcess.class);
                Call<Loginbean> userCall = wishListApiProcess.userLogin(email, password);
                userCall.enqueue(new Callback<Loginbean>() {
                                     @Override
                                     public void onResponse(Call<Loginbean> call, Response<Loginbean> response) {
                                         int errorCode = response.code();
                                         wishlistProgressDialog.dismissDialog();
                                         String errormessage = null;
                                         try {
                                             if (errorCode == 200) {
                                                 List<String> notification = response.body().getNonFieldErrors();
                                                 String token = response.body().getToken();
                                                 String step = response.body().getStep();
                                                 sharedDatabase.userToken(token);
                                                 sharedDatabase.addStep(step);
                                                 errormessage = step;

                                             } else {

                                                 String mess = response.errorBody().string();
                                                 JSONObject jsonObject = new JSONObject(mess);
                                                 JSONArray jsonArray = jsonObject.getJSONArray("non_field_errors");
                                                 for (int i = 0; i < jsonArray.length(); i++) {

                                                     errormessage = jsonArray.getString(i);
                                                 }

                                             }
                                             UpdateMessage updateMessage = (UpdateMessage) context;
                                             updateMessage.loginStatus(errormessage);
                                         } catch (Exception e) {
                                             e.printStackTrace();
                                         }
                                     }

                                     @Override
                                     public void onFailure(Call<Loginbean> call, Throwable t) {

                                         t.printStackTrace();
                                         UpdateMessage updateMessage = (UpdateMessage) context;
                                         updateMessage.loginStatus("INC");
                                     }
                                 }

                );

            } else {
                wishlistProgressDialog.dismissDialog();
                UpdateMessage updateMessage = (UpdateMessage) context;
                updateMessage.loginStatus("INC");
            }

        } catch (Exception e) {
            wishlistProgressDialog.dismissDialog();
            UpdateMessage updateMessage = (UpdateMessage) context;
            updateMessage.loginStatus("INC");
        }

    }
}
