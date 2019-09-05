package com.root.wishlist.model.notification;


import android.content.Context;
import android.util.Log;

import com.root.wishlist.R;
import com.root.wishlist.util.RetrofitSingletonClass;
import com.root.wishlist.util.globalValues.NetworkConnection;
import com.root.wishlist.util.globalValues.RetrofitClass;
import com.root.wishlist.interfaces.IOAModel;
import com.root.wishlist.interfaces.WishListApiProcess;
import com.root.wishlist.pojo.notification.NotificationIOA;
import com.root.wishlist.pojo.notification.Outbound;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class NotificationIOAModel implements IOAModel {
    Context context;


    @Override
    public void notificationIOAModel(final Context context, String token, final OnNotificationIOAOnListener onNotificationIOAOnListener) {

        try {
            if (NetworkConnection.isNetworkAvailable(context)) {
                this.context = context;

                RetrofitSingletonClass retrofitSingletonClass = RetrofitSingletonClass.getInstance(context);
                RetrofitClass retrofitClass = RetrofitSingletonClass.getRetrofitClassSingleInstance();

                WishListApiProcess myApiEndpointInterface = retrofitClass.getClient().create(WishListApiProcess.class);
                Call<NotificationIOA> companyOfInterestBeanCall = myApiEndpointInterface.getNotificationIOA(token);
                companyOfInterestBeanCall.enqueue(new Callback<NotificationIOA>() {
                    @Override
                    public void onResponse(Call<NotificationIOA> call, Response<NotificationIOA> response) {
                        List<Outbound> outboundList = new ArrayList<Outbound>();
                        List<Outbound> outboundList1 = new ArrayList<Outbound>();
                        int statusCode = response.code();
                        if (statusCode == 200) {
                            onNotificationIOAOnListener.getInBound(response.body().getInbound());
                            //onNotificationIOAOnListener.getOutBound(response.body().getOutbound());
                            onNotificationIOAOnListener.getArchived(response.body().getArchived());
                            String requestorcompanyname;
                            String prospectdesignation;
                            String requestordesignation;
                            Outbound outbound;
                            outboundList = response.body().getOutbound();
                            for (int i = 0; i < outboundList.size(); i++) {
                                try {
                                    int requestId = outboundList.get(i).getRequestID();
                                    boolean highlight = outboundList.get(i).getRecipientRead();
                                    String requestorname = outboundList.get(i).getContactusername();
                                    String prospectname = outboundList.get(i).getProspectname();
                                    String status = outboundList.get(i).getStatus();
                                    String contactshortbio = outboundList.get(i).getContactshortbio();
                                    String prospectcompanyname = outboundList.get(i).getProspectcompanyname().get(0);
                                    if (!outboundList.get(i).getProspectdesignation().isEmpty()) {
                                        prospectdesignation = outboundList.get(i).getProspectdesignation().get(0);
                                    } else {
                                        prospectdesignation = "";
                                    }
                                    if (!outboundList.get(i).getContactdesignation().isEmpty()) {
                                        requestordesignation = outboundList.get(i).getContactdesignation().get(0);
                                    } else {
                                        requestordesignation = "";
                                    }

                                    if (!outboundList.get(i).getContactcompanyname().isEmpty()) {
                                        requestorcompanyname = outboundList.get(i).getContactcompanyname().get(0);
                                    } else {
                                        requestorcompanyname = "";
                                    }
                                    outbound = new Outbound(requestId, status, requestorname, prospectname, prospectdesignation, requestordesignation, prospectcompanyname, requestorcompanyname, highlight, contactshortbio, outboundList.get(i).getContactowner());
                                    outboundList1.add(outbound);

                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }
                            }
                            onNotificationIOAOnListener.getOutBound(outboundList1);

                        }
                    }

                    @Override
                    public void onFailure(Call<NotificationIOA> call, Throwable t) {
                        Log.d("message", t.getMessage());
                        onNotificationIOAOnListener.netWorkmessage(context.getString(R.string.networkconnection));
                    }
                });
            } else {
                onNotificationIOAOnListener.netWorkmessage(context.getString(R.string.networkconnection));
            }
        } catch (Exception e) {
            onNotificationIOAOnListener.netWorkmessage(context.getString(R.string.networkconnection));
        }

    }


}
