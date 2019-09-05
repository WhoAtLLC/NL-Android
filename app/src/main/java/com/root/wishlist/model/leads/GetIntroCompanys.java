package com.root.wishlist.model.leads;

import android.content.Context;
import android.util.Log;

import com.root.wishlist.util.RetrofitSingletonClass;
import com.root.wishlist.util.globalValues.RetrofitClass;
import com.root.wishlist.pojo.leads.getintroduce.IntroAllCompany;
import com.root.wishlist.pojo.leads.getintroduce.IntroCompanies;
import com.root.wishlist.pojo.wantto.CompaniesofInterest;
import com.root.wishlist.pojo.wantto.NotificationWantTo;
import com.root.wishlist.interfaces.WishListApiProcess;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

interface introComapny {
    interface OnIntroCompanyList {
        void getAllIntroComapny(List<IntroCompanies> companyResultses);

        void getWantToCompany(List<CompaniesofInterest> companyResultses);

        void pageNumber(int pNumber);
    }

    void getCompanyDetails(Context context,String token, String username, int pageno, OnIntroCompanyList onIntroCompanyList);

    void getWantToCompanyList(Context context, String token, int userID, int pageno, OnIntroCompanyList onIntroCompanyList);
}

public class GetIntroCompanys implements introComapny {

    @Override
    public void getCompanyDetails(Context context, String token, String username, final int pageno, final OnIntroCompanyList onIntroCompanyList) {

        RetrofitSingletonClass retrofitSingletonClass = RetrofitSingletonClass.getInstance(context);
        RetrofitClass retrofitClass = RetrofitSingletonClass.getRetrofitClassSingleInstance();

        WishListApiProcess wishListApiProcess = retrofitClass.getClient().create(WishListApiProcess.class);
        Call<IntroAllCompany> introAllCompanyCall = wishListApiProcess.getIntroCompany(token, username, pageno);
        introAllCompanyCall.enqueue(new Callback<IntroAllCompany>() {
            @Override
            public void onResponse(Call<IntroAllCompany> call, Response<IntroAllCompany> response) {

                int code = response.code();
                if (code == 200) {

                    String next = response.body().getNext();
                    if (next != null) {
                        try {

                            Log.d("padad", next.substring(next.lastIndexOf("=" + 1)));
                            String page = next.substring(next.lastIndexOf("=" + 1));
                            if (!page.equals("")) {
                                onIntroCompanyList.pageNumber(Integer.parseInt(page));
                                Log.d("pagenumber", page);
                            }
                            Log.d("next", next);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    List<IntroCompanies> results = new ArrayList<IntroCompanies>();
                    int pre = response.body().getCount();
                    String image = null, title = null;
                    Integer id;

                    for (int i = 0; i < response.body().getResults().size(); i++) {

                        image = response.body().getResults().get(i).getCompany().getProfileImageUrl();
                        title = response.body().getResults().get(i).getCompany().getTitle();
                        id = response.body().getResults().get(i).getCompany().getId();
                        results.add(new IntroCompanies(title, image, id));
                    }
                    onIntroCompanyList.getAllIntroComapny(results);

                }
            }

            @Override
            public void onFailure(Call<IntroAllCompany> call, Throwable t) {

            }
        });

    }

    @Override
    public void getWantToCompanyList(Context context, String token, int userID, int pageno, final OnIntroCompanyList onIntroCompanyList) {

        RetrofitSingletonClass retrofitSingletonClass = RetrofitSingletonClass.getInstance(context);
        RetrofitClass retrofitClass = RetrofitSingletonClass.getRetrofitClassSingleInstance();

        final WishListApiProcess wishListApiProcess = retrofitClass.getClient().create(WishListApiProcess.class);
        Call<NotificationWantTo> notificationWantToCall = wishListApiProcess.getNotificationBusiness(token, userID);
        notificationWantToCall.enqueue(new Callback<NotificationWantTo>() {
            @Override
            public void onResponse(Call<NotificationWantTo> call, Response<NotificationWantTo> response) {

                int errorCode = response.code();
                String message = response.message();
                List<CompaniesofInterest> companiesofInterests = new ArrayList<CompaniesofInterest>();
                if (errorCode == 200) {
                    companiesofInterests = response.body().getCompaniesofInterest();
                    String mess = response.body().getHowIntroReason();
                }
                onIntroCompanyList.getWantToCompany(companiesofInterests);

            }

            @Override
            public void onFailure(Call<NotificationWantTo> call, Throwable t) {

            }
        });
    }
}
