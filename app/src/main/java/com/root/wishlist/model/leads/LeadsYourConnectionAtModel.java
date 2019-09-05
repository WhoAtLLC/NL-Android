package com.root.wishlist.model.leads;

import android.content.Context;

import com.root.wishlist.util.RetrofitSingletonClass;
import com.root.wishlist.util.globalValues.NetworkConnection;
import com.root.wishlist.util.globalValues.RetrofitClass;
import com.root.wishlist.pojo.leads.YourConnectionAtResult;
import com.root.wishlist.pojo.leads.YourConnectionAtbean;
import com.root.wishlist.pojo.leads.YourConnectionpossibleto;
import com.root.wishlist.interfaces.WishListApiProcess;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

interface LeadsYourConnectionAtInt {

    void yourConnectionAt(Context context, int pageNumber, int userId, String token, OnYourConnectionListener onYourConnectionListener);

    interface OnYourConnectionListener {
        void getConnectionAt(List<YourConnectionAtResult> leadsCompanyLists, int totalCount);

        void networkMessage();
    }
}

public class LeadsYourConnectionAtModel implements LeadsYourConnectionAtInt {

    List<YourConnectionpossibleto> yourConnectionpossibletos = new ArrayList<>();
    List<YourConnectionAtResult> yourConnectionAtResults = new ArrayList<>();


    @Override
    public void yourConnectionAt(Context context, int pagenumber, int userId, String token, final OnYourConnectionListener onYourConnectionListener) {

        try {
            if (NetworkConnection.isNetworkAvailable(context)) {

                RetrofitSingletonClass retrofitSingletonClass = RetrofitSingletonClass.getInstance(context);
                RetrofitClass retrofitClass = RetrofitSingletonClass.getRetrofitClassSingleInstance();

                WishListApiProcess wishListApiProcess = retrofitClass.getClient().create(WishListApiProcess.class);
                Call<YourConnectionAtbean> leadsCompanyListCall = wishListApiProcess.getYourConnectionAtList(token, userId, pagenumber);
                leadsCompanyListCall.enqueue(new Callback<YourConnectionAtbean>() {
                    @Override
                    public void onResponse(Call<YourConnectionAtbean> call, Response<YourConnectionAtbean> response) {

                        int errorCode = response.code();
                        if (errorCode == 200) {

                            List<YourConnectionAtResult> results = response.body().getResults();

                           /* for (int i = 0; i < results.size(); i++) {
                                for (int j = 0; j < results.get(i).getConnectionpossibleto().size(); j++) {
                                    if (results.get(i).getConnectionpossibleto().get(j).getHandle() != null) {

                                        yourConnectionpossibletos.add(results.get(i).getConnectionpossibleto().get(j));


                                    }
                                }

                            }*/
                            for (int i = 0; i < results.size(); i++) {

                                String connection = results.get(i).getConnectionname();
                                String title = results.get(i).getTitle();
                                int connectioncount = results.get(i).getConnectioncount();

                                if(yourConnectionpossibletos.size()==0)
                                    yourConnectionpossibletos.addAll(results.get(i).getConnectionpossibleto());

                                yourConnectionAtResults.add(new YourConnectionAtResult(connection, title, connectioncount, yourConnectionpossibletos));
                            }
                            onYourConnectionListener.getConnectionAt(yourConnectionAtResults, response.body().getCount());


                        }

                    }

                    @Override
                    public void onFailure(Call<YourConnectionAtbean> call, Throwable t) {
                        onYourConnectionListener.networkMessage();
                        //t.printStackTrace();
                    }
                });
            } else {
                onYourConnectionListener.networkMessage();
            }
        } catch (Exception e) {
            onYourConnectionListener.networkMessage();
        }


    }
}