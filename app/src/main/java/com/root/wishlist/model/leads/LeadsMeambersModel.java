package com.root.wishlist.model.leads;

import android.content.Context;

import com.root.wishlist.util.RetrofitSingletonClass;
import com.root.wishlist.util.globalValues.RetrofitClass;
import com.root.wishlist.pojo.leads.LeadsCompanyList;
import com.root.wishlist.pojo.leads.ResultLeads;
import com.root.wishlist.interfaces.WishListApiProcess;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

interface LeadsMeambersModelInterface {

    interface OnLoadmembersList {
        void getCompanyList(List<ResultLeads> leadsCompanyLists);
    }

    void companyList(Context context,Integer pageNumber, String token, OnLoadmembersList onLoadCompanyList);
}

public class LeadsMeambersModel implements LeadsMeambersModelInterface {


    @Override
    public void companyList(Context context, Integer pageNumber,String token, final OnLoadmembersList onLoadCompanyList)
    {
        RetrofitSingletonClass retrofitSingletonClass = RetrofitSingletonClass.getInstance(context);
        RetrofitClass retrofitClass = RetrofitSingletonClass.getRetrofitClassSingleInstance();

        WishListApiProcess wishListApiProcess = retrofitClass.getClient().create(WishListApiProcess.class);
        Call<LeadsCompanyList> leadsCompanyListCall = wishListApiProcess.getMembersWishlist(token);
        leadsCompanyListCall.enqueue(new Callback<LeadsCompanyList>() {
            @Override
            public void onResponse(Call<LeadsCompanyList> call, Response<LeadsCompanyList> response) {

                int errorCode = response.code();
                if(errorCode==200) {
                    String message = response.body().getNext();
                    onLoadCompanyList.getCompanyList(response.body().getResults());
                }

            }

            @Override
            public void onFailure(Call<LeadsCompanyList> call, Throwable t) {

                t.printStackTrace();
            }
        });


    }
}
