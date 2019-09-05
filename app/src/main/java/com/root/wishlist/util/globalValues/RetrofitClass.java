package com.root.wishlist.util.globalValues;

import android.content.Context;

import com.root.wishlist.database.SharedDatabase;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClass {

    Context mContext;
    private Retrofit retrofit;
    private String BASE_URL;

/*    public static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(sharedDatabase.getTargetServer()).client(configureTimeouts())
            .addConverterFactory(GsonConverterFactory.create())
            .build();*/

    public RetrofitClass(Context context) {
        mContext = context;
        BASE_URL = new Constants(mContext).BASE_URL;
        retrofit = null;
    }


    public static OkHttpClient configureTimeouts() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .readTimeout(90, TimeUnit.SECONDS)
                .build();
        return okHttpClient;
    }

    public Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL).client(configureTimeouts())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }


}