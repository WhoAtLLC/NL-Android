package com.root.wishlist.model.leads;

import android.content.Context;
import android.util.Log;

import com.root.wishlist.R;
import com.root.wishlist.database.SharedDatabase;
import com.root.wishlist.util.RetrofitSingletonClass;
import com.root.wishlist.util.globalValues.NetworkConnection;
import com.root.wishlist.util.globalValues.RetrofitClass;
import com.root.wishlist.interfaces.WishListApiProcess;
import com.root.wishlist.pojo.leads.MemberLeads;
import com.root.wishlist.pojo.leads.MemberResult;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


interface LeadsCompanyIListInt {

    interface OnLoadCompanyList {
        void getCompanyList(List<MemberResult> leadsCompanyLists, int next);

        void newtWorkMessage(String message);
    }

    void companyList(int pageNumber, String token, OnLoadCompanyList onLoadCompanyList, Context context);
}

public class LeadsCompanyIListModel implements LeadsCompanyIListInt {

    SharedDatabase sharedDatabase;
    String nextPage = "";


    @Override
    public void companyList(int pageNumber, String token, final OnLoadCompanyList onLoadCompanyList, final Context context) {
        sharedDatabase=new SharedDatabase(context);
        try {
            if (NetworkConnection.isNetworkAvailable(context)) {

                RetrofitSingletonClass retrofitSingletonClass=RetrofitSingletonClass.getInstance(context);
                RetrofitClass retrofitClass=RetrofitSingletonClass.getRetrofitClassSingleInstance();

                WishListApiProcess wishListApiProcess=retrofitClass.getClient().create(WishListApiProcess.class);
                final Call<MemberLeads> leadsCompanyListCall=wishListApiProcess.getLeadsCompanyList(token, pageNumber);
                leadsCompanyListCall.enqueue(new Callback<MemberLeads>() {
                    @Override
                    public void onResponse(Call<MemberLeads> call, Response<MemberLeads> response) {


                        int errorCode=response.code();
                        if (errorCode == 200) {
                            String next=response.body().getNext();
                            if (!next.equals("")) {                                                     //due to crash in mailinator ids
                                nextPage=next.substring(next.lastIndexOf("=") + 1);
                                sharedDatabase.setNextpage(Integer.valueOf(nextPage));
                            } else
                                sharedDatabase.setNextpage(1);
                            String message=response.body().getNext();
                            Log.d("pagenumber", nextPage);
                            Log.d("message", nextPage);
                           // sharedDatabase.setNextpage(Integer.valueOf(nextPage));
                            //if (nextPage.equals("2"))sharedDatabase.saveLeadsCompany(response.body().getResults());

                            onLoadCompanyList.getCompanyList(response.body().getResults(), 1);
                        }

                    }

                    @Override
                    public void onFailure(Call<MemberLeads> call, Throwable t) {
                        onLoadCompanyList.newtWorkMessage(context.getString(R.string.networkconnection));
                        //t.printStackTrace();
                    }
                });
            } else {
                onLoadCompanyList.newtWorkMessage(context.getString(R.string.networkconnection));
            }
        } catch (Exception e) {
            onLoadCompanyList.newtWorkMessage(context.getString(R.string.networkconnection));
        }

    }
}
