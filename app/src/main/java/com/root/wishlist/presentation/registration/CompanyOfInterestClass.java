package com.root.wishlist.presentation.registration;

import android.content.Context;
import android.util.Log;

import com.root.wishlist.util.RetrofitSingletonClass;
import com.root.wishlist.util.globalValues.RetrofitClass;
import com.root.wishlist.pojo.mywishlist.Company;
import com.root.wishlist.pojo.mywishlist.Result;
import com.root.wishlist.interfaces.WishListApiProcess;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

interface CompanyOfInterestList {

    interface OnLoadCompanyList {
        void getCompanyList(List<Result> companyLists, int next);
    }

    void companyList(Context context, int pageNumber, String token, OnLoadCompanyList onLoadCompanyList);
}

public class CompanyOfInterestClass implements CompanyOfInterestList {
    List<Result> companyLists = new ArrayList<>();


    @Override
    public void companyList(Context context, int pageNumber, String token, final OnLoadCompanyList onLoadCompanyList) {

        RetrofitSingletonClass retrofitSingletonClass = RetrofitSingletonClass.getInstance(context);
        RetrofitClass retrofitClass = RetrofitSingletonClass.getRetrofitClassSingleInstance();

        WishListApiProcess myApiEndpointInterface = retrofitClass.getClient().create(WishListApiProcess.class);
        Call<Company> companyOfInterestBeanCall = myApiEndpointInterface.getCompanyDetails(token, pageNumber);
        companyOfInterestBeanCall.enqueue(new Callback<Company>() {
            @Override
            public void onResponse(Call<Company> call, Response<Company> response) {

                int errorCode = response.code();
                if (errorCode == 200) {
                    List<Result> list = response.body().getResults();
                    String pageValue = response.body().getNext();
                    Log.d("nextpage",pageValue);
                    int nextpage = Integer.parseInt(pageValue.substring(pageValue.lastIndexOf("=") + 1));
                    for (int i = 0; i < list.size(); i++) {
                        String profileImage = list.get(i).getProfileImageUrl();
                        String title = list.get(i).getTitle();
                        int companyid = list.get(i).getId();
                        companyLists.add(new Result(title, profileImage, companyid));
                    }
                    onLoadCompanyList.getCompanyList(companyLists, nextpage);
                }
            }

            @Override
            public void onFailure(Call<Company> call, Throwable t) {

                t.printStackTrace();
            }
        });
    }



}
