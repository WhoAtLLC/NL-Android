package com.root.wishlist.model.registration;

import android.content.Context;
import android.content.SharedPreferences;

import com.root.wishlist.database.CompanyListDatabase;
import com.root.wishlist.database.SharedDatabase;
import com.root.wishlist.util.RetrofitSingletonClass;
import com.root.wishlist.util.globalValues.RetrofitClass;
import com.root.wishlist.util.globalValues.WishlistProgressDialog;
import com.root.wishlist.interfaces.UpdateMessage;
import com.root.wishlist.interfaces.WishListApiProcess;
import com.root.wishlist.util.RegistrationBean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RegistrationStep1 {
    Context context;
    SharedDatabase sharedDatabase;
    CompanyListDatabase companyListDatabase;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    public static final String SHAREDDATABASE = "wishlist";
    WishlistProgressDialog wishlistProgressDialog;

    public RegistrationStep1(Context context) {
        this.context = context;
        wishlistProgressDialog = new WishlistProgressDialog(context);
        sharedDatabase = new SharedDatabase(context);
        companyListDatabase = new CompanyListDatabase(context);
        sharedPreferences = context.getSharedPreferences(SHAREDDATABASE, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

    }

    public void resisterStep1(final String email, String password) {
        RetrofitSingletonClass retrofitSingletonClass = RetrofitSingletonClass.getInstance(context);
        RetrofitClass retrofitClass = RetrofitSingletonClass.getRetrofitClassSingleInstance();

        WishListApiProcess myApiEndpointInterface = retrofitClass.getClient().create(WishListApiProcess.class);
        wishlistProgressDialog.dialogShow();
        Call<RegistrationBean> userCall = myApiEndpointInterface.registrationStep1(email, password);
        userCall.enqueue(new Callback<RegistrationBean>() {
            @Override
            public void onResponse(Call<RegistrationBean> call, Response<RegistrationBean> response) {

                int errorcode = response.code();
                String res = null;
                String message = null;
                if (errorcode == 201) {
                    String token = response.body().getToken();
                    if (token.equals(sharedDatabase.getToken())) {

                        sharedDatabase.userToken(token);
                        sharedDatabase.setMyBusinessMessage("");
                        editor.clear();
                        editor.commit();
                    } else {
                        companyListDatabase.deletetable();
                        sharedDatabase.userToken(token);
                        sharedDatabase.setMyBusinessMessage("");
                        sharedDatabase.addStep("step2");
                    }
                    message = "success";

                } else {
                    try {
                        res = response.errorBody().string();
                        JSONObject jsonObject = new JSONObject(res);
                        JSONArray jsonElements = jsonObject.getJSONArray("email");
                        String errorMessage = jsonElements.getString(0);
                        message = "Email already registered, please try a new one.";
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                wishlistProgressDialog.dismissDialog();
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
