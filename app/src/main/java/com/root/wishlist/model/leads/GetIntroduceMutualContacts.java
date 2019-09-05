package com.root.wishlist.model.leads;

import android.content.Context;
import android.util.Log;

import com.root.wishlist.R;
import com.root.wishlist.util.RetrofitSingletonClass;
import com.root.wishlist.util.globalValues.NetworkConnection;
import com.root.wishlist.util.globalValues.RetrofitClass;
import com.root.wishlist.interfaces.WishListApiProcess;
import com.root.wishlist.pojo.leads.getintroduce.IntoMatualContacts;
import com.root.wishlist.pojo.leads.getintroduce.IntroResult;
import com.root.wishlist.pojo.wantto.WantToMutualContacts;
import com.root.wishlist.pojo.wantto.WnatoResult;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

interface MatualContacts {
    interface OnSetMatualContacts {
        void setMutualContacts(List<IntroResult> companyList);

        void setNotificationMutualContacts(List<WnatoResult> companyList);

        void pagenumber(String pageNumber);

        void newtworkError(String connection);

    }

    void getMutualContacts(Context context, String username, int pageNumber, String token, OnSetMatualContacts introCompany);

    void getNotificationMutualContacts(Context context, int userID, int pageNumber, String token, OnSetMatualContacts introCompany);
}

public class GetIntroduceMutualContacts implements MatualContacts {


    List<IntroResult> itromutualcontacts = new ArrayList<>();

    @Override
    public void getMutualContacts(final Context context, String username, int pageNumber, String token, final OnSetMatualContacts introCompany) {

        try {
            if (NetworkConnection.isNetworkAvailable(context)) {

                RetrofitSingletonClass retrofitSingletonClass = RetrofitSingletonClass.getInstance(context);
                RetrofitClass retrofitClass = RetrofitSingletonClass.getRetrofitClassSingleInstance();


                WishListApiProcess wishListApiProcess = retrofitClass.getClient().create(WishListApiProcess.class);
                if(username==null)
                    username=" ";
                Call<IntoMatualContacts> leadsCompanyListCall = wishListApiProcess.getIntroMutualContact(token, username, pageNumber);
                leadsCompanyListCall.enqueue(new Callback<IntoMatualContacts>() {
                    @Override
                    public void onResponse(Call<IntoMatualContacts> call, Response<IntoMatualContacts> response) {

                        int errorCode = response.code();
                        if (errorCode == 200) {
                            String message = response.body().getNext();
                            Log.d("page number", message);
                            String pageNumber = message.substring(message.lastIndexOf("=") + 1);
                            Log.d("pageNumbe=", String.valueOf(pageNumber));
                            introCompany.pagenumber(pageNumber);
                            introCompany.setMutualContacts(response.body().getResults());
                        }else{
                            introCompany.setMutualContacts(null);
                        }
                    }

                    @Override
                    public void onFailure(Call<IntoMatualContacts> call, Throwable t) {
                     //   introCompany.newtworkError(context.getString(R.string.networkconnection));
                        t.printStackTrace();
                    }
                });
            } else {
                introCompany.newtworkError(context.getString(R.string.networkconnection));
            }

        } catch (Exception e) {
            introCompany.newtworkError(context.getString(R.string.networkconnection));
        }
    }

    @Override
    public void getNotificationMutualContacts(Context context, int userID, int pageNumber, String token, final OnSetMatualContacts introCompany) {

        try {
            if (NetworkConnection.isNetworkAvailable(context)) {

                RetrofitSingletonClass retrofitSingletonClass = RetrofitSingletonClass.getInstance(context);
                RetrofitClass retrofitClass = RetrofitSingletonClass.getRetrofitClassSingleInstance();


                WishListApiProcess wishListApiProcess = retrofitClass.getClient().create(WishListApiProcess.class);
                Call<WantToMutualContacts> notificationWantToCall = wishListApiProcess.getNotificationMutualContacts(token, userID, pageNumber);
                notificationWantToCall.enqueue(new Callback<WantToMutualContacts>() {
                    @Override
                    public void onResponse(Call<WantToMutualContacts> call, Response<WantToMutualContacts> response) {

                        int errorCode = response.code();
                        String messge = response.message();
                        if (errorCode == 200) {
                            String message = response.body().getNext();
                            Log.d("page number", message);
                            String pageNumber = message.substring(message.lastIndexOf("=") + 1);
                            Log.d("pageNumbe=", String.valueOf(pageNumber));
                            introCompany.pagenumber(pageNumber);
                            introCompany.setNotificationMutualContacts(response.body().getResults());
                        }
                    }

                    @Override
                    public void onFailure(Call<WantToMutualContacts> call, Throwable t) {

                        t.printStackTrace();
                    }
                });
            } else {
                introCompany.newtworkError(context.getString(R.string.networkconnection));
            }

        } catch (Exception e) {
            introCompany.newtworkError(context.getString(R.string.networkconnection));
        }
    }
}
