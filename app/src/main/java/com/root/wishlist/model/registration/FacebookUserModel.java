package com.root.wishlist.model.registration;

import android.content.Context;

import com.root.wishlist.util.RetrofitSingletonClass;
import com.root.wishlist.util.globalValues.RetrofitClass;
import com.root.wishlist.pojo.FbResult;
import com.root.wishlist.interfaces.WishListApiProcess;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

interface fbuserToken {
    interface userToken {
        void setUserToken(String token);
    }

    void getusertoken(Context context, HashMap<String, String> stringHashMap, userToken userToken);
}

public class FacebookUserModel implements fbuserToken {


    @Override
    public void getusertoken(Context context, HashMap<String, String> oathKey, final userToken userToken) {

        RetrofitSingletonClass retrofitSingletonClass = RetrofitSingletonClass.getInstance(context);
        RetrofitClass retrofitClass = RetrofitSingletonClass.getRetrofitClassSingleInstance();

        WishListApiProcess wishListApiProcess = retrofitClass.getClient().create(WishListApiProcess.class);

        Call<FbResult> searchActivityCall = wishListApiProcess.FB_RESULT_CALL(oathKey);
        searchActivityCall.enqueue(new Callback<FbResult>() {
            @Override
            public void onResponse(Call<FbResult> call, Response<FbResult> response) {
                int errorCode = response.code();
                String message = "";
                if (errorCode == 400) {
                    message = response.message();
                } else if (errorCode == 404) {
                    message = "failure";
                } else if (errorCode == 500) {
                    message = "failure";
                }
                userToken.setUserToken(message);
            }

            @Override
            public void onFailure(Call<FbResult> call, Throwable t) {

            }
        });

    }
}
