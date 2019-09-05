package com.root.wishlist.model.registration;

import android.content.Context;

import com.root.wishlist.database.SharedDatabase;
import com.root.wishlist.interfaces.UpdateMessage;
import com.root.wishlist.interfaces.WishListApiProcess;
import com.root.wishlist.util.RegistrationBean;
import com.root.wishlist.util.RetrofitSingletonClass;
import com.root.wishlist.util.globalValues.RetrofitClass;
import com.root.wishlist.util.globalValues.WishlistProgressDialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrationStep2 {
    Context context;
    SharedDatabase sharedDatabase;
    WishlistProgressDialog wishlistProgressDialog;

    public RegistrationStep2(Context context) {
        this.context = context;
        wishlistProgressDialog = new WishlistProgressDialog(context);
        sharedDatabase = new SharedDatabase(context);
    }


    public void userDetails(MultipartBody.Part profileImage, String token, String firstname, String lastname, String screenname) {

        RetrofitSingletonClass retrofitSingletonClass = RetrofitSingletonClass.getInstance(context);
        RetrofitClass retrofitClass = RetrofitSingletonClass.getRetrofitClassSingleInstance();

        WishListApiProcess myApiEndpointInterface = retrofitClass.getClient().create(WishListApiProcess.class);
        wishlistProgressDialog.dialogShow();
        Call<RegistrationBean> userCall = myApiEndpointInterface.registrationStep2(token, firstname, lastname, screenname, profileImage);
        userCall.enqueue(new Callback<RegistrationBean>() {
            @Override
            public void onResponse(Call<RegistrationBean> call, Response<RegistrationBean> response) {
                wishlistProgressDialog.dismissDialog();
                int errorcode = response.code();
                String res = null;
                String message = null;
                if (errorcode == 200 || errorcode == 201) {
                    RegistrationBean user = response.body();
                    String userId = response.body().getUser();
                    String usermessage = response.body().getHandle();
                    sharedDatabase.userId(userId);
                    message = "success";
                    sharedDatabase.addStep("contacts");
                } else if (errorcode == 500) {
                    message = "Screen name should unique.";
                } else {
                    try {
                        res = response.errorBody().string();
                        JSONObject jsonObject = new JSONObject(res);
                        JSONArray first_name = jsonObject.getJSONArray("handle");
                        message = first_name.getString(0);
                        message = first_name.getString(0);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                UpdateMessage updateMessage = (UpdateMessage) context;
                updateMessage.loginStatus(message);
            }

            @Override
            public void onFailure(Call<RegistrationBean> call, Throwable t) {

                String exceptio = t.getLocalizedMessage();
                t.printStackTrace();

            }
        });
    }


}
