package com.root.wishlist.searvices;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.root.wishlist.database.SharedDatabase;
import com.root.wishlist.util.RetrofitSingletonClass;
import com.root.wishlist.util.globalValues.NetworkConnection;
import com.root.wishlist.util.globalValues.RetrofitClass;
import com.root.wishlist.interfaces.WishListApiProcess;
import com.root.wishlist.pojo.notification.Inbound;
import com.root.wishlist.pojo.notification.NotificationIOA;
import com.root.wishlist.pojo.notification.Outbound;
import com.root.wishlist.view.NotificationIOAView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationServices extends AsyncTask<Void, Void, List<Inbound>> {
    Context context;
    SharedDatabase sharedDatabase;
    List<Inbound> inbounds;
    NotificationIOAView notificationIOAView;

    public NotificationServices(Context context) {
        super();
        this.context = context;
        sharedDatabase = new SharedDatabase(context);
    }



    @Override
    protected List<Inbound> doInBackground(Void... params) {

        try {
            if (NetworkConnection.isNetworkAvailable(context)) {

                RetrofitSingletonClass retrofitSingletonClass = RetrofitSingletonClass.getInstance(context);
                RetrofitClass retrofitClass = RetrofitSingletonClass.getRetrofitClassSingleInstance();

                WishListApiProcess myApiEndpointInterface = retrofitClass.getClient().create(WishListApiProcess.class);
                Call<NotificationIOA> companyOfInterestBeanCall = myApiEndpointInterface.getNotificationIOA("Token " + sharedDatabase.getToken());
                companyOfInterestBeanCall.enqueue(new Callback<NotificationIOA>() {
                    @Override
                    public void onResponse(Call<NotificationIOA> call, Response<NotificationIOA> response) {
                        List<Outbound> outboundList1 = new ArrayList<Outbound>();
                        int statusCode = response.code();
                        if (statusCode == 200) {
                            inbounds = response.body().getInbound();
                            Log.d("dandjhau","adkajid");
                            return;
                        }
                    }

                    @Override
                    public void onFailure(Call<NotificationIOA> call, Throwable t) {
                        Log.d("message", t.getMessage());
                    }
                });
            } else {
            }
        } catch (Exception e) {
        }

        return inbounds;
    }
    @Override
    protected void onPostExecute(List<Inbound> inboundList) {


        /*IncomingNotifications incomingNotifications = (IncomingNotifications) context;
        incomingNotifications.getIncomingNotification(inboundList);*/
    }
}
