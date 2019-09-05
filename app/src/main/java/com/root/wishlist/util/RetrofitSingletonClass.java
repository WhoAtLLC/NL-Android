package com.root.wishlist.util;

import android.content.Context;

import com.root.wishlist.util.globalValues.RetrofitClass;

public class RetrofitSingletonClass {
    private static RetrofitSingletonClass single_instance = null;
    private static RetrofitClass retrofitClassSingleInstance = null;
    private static Context mContext;

    // variable of type String


    // private constructor restricted to this class itself
    private RetrofitSingletonClass(Context context)
    {
        mContext = context;
    }

    // static method to create instance of Singleton class
    public static RetrofitSingletonClass getInstance(Context context)
    {
        if (single_instance == null)
        {
            single_instance = new RetrofitSingletonClass(context);
            retrofitClassSingleInstance = new RetrofitClass(context);

        }


        return single_instance;
    }

    public static RetrofitClass getRetrofitClassSingleInstance(){
       /* if(retrofitClassSingleInstance == null)
        {

        }*/
        return retrofitClassSingleInstance;
    }
}
