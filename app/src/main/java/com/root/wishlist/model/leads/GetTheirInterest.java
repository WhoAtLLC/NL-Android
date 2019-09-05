package com.root.wishlist.model.leads;

import android.content.Context;

import com.root.wishlist.util.RetrofitSingletonClass;
import com.root.wishlist.util.globalValues.NetworkConnection;
import com.root.wishlist.util.globalValues.RetrofitClass;
import com.root.wishlist.pojo.leads.getintroduce.TheirInterestBean;
import com.root.wishlist.pojo.wantto.Comment;
import com.root.wishlist.pojo.wantto.NotificationWantTo;
import com.root.wishlist.R;
import com.root.wishlist.interfaces.WishListApiProcess;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

interface theirInterest {
    interface OnTheirInterestClick {
        void getTheirInterest(String handle, String shortBio, String bio, String image, String businessDiscussion, String businessAdditional);

        void networkmessage(String connection);

        void getWanttoBusinessInfo(String status, String whyIntroReason, String howIntroReason, List<Comment> comments);
    }

    void setInterest(Context context, String username, String token, OnTheirInterestClick onTheirInterestClick);

    void wantToBusinessInfo(Context context, int userID, String token, OnTheirInterestClick onTheirInterestClick);
}

public class GetTheirInterest implements theirInterest {

    @Override
    public void setInterest(final Context context, String username, String token, final OnTheirInterestClick onTheirInterestClick) {

        try {
            if (NetworkConnection.isNetworkAvailable(context)) {

                RetrofitSingletonClass retrofitSingletonClass = RetrofitSingletonClass.getInstance(context);
                RetrofitClass retrofitClass = RetrofitSingletonClass.getRetrofitClassSingleInstance();

                WishListApiProcess wishListApiProcess = retrofitClass.getClient().create(WishListApiProcess.class);
                if(username==null)
                    username=" ";
                Call<TheirInterestBean> theirInterestCall = wishListApiProcess.getTheirInterest(token, username);
                theirInterestCall.enqueue(new Callback<TheirInterestBean>() {
                    @Override
                    public void onResponse(Call<TheirInterestBean> call, Response<TheirInterestBean> response) {

                        int code = response.code();
                        String message = response.message();
                        if (code == 200) {
                            String name = response.body().getHandle();
                            onTheirInterestClick.getTheirInterest(response.body().getHandle(), response.body().getShortBio(), response.body().getBio(), response.body().getImage(), response.body().getBusinessDiscussion(), response.body().getBusinessAdditional());
                        }
                        else
                        {
                            onTheirInterestClick.getTheirInterest(null,null,null,null,null,null);
                        }
                    }

                    @Override
                    public void onFailure(Call<TheirInterestBean> call, Throwable t) {
                       // onTheirInterestClick.networkmessage(context.getString(R.string.networkconnection));
                    }
                });
            } else {
                onTheirInterestClick.networkmessage(context.getString(R.string.networkconnection));
            }

        } catch (Exception e) {
            onTheirInterestClick.networkmessage(context.getString(R.string.networkconnection));

        }

    }

    @Override
    public void wantToBusinessInfo(final Context context, int userID, String token, final OnTheirInterestClick onTheirInterestClick) {


        try {
            if (NetworkConnection.isNetworkAvailable(context)) {

                RetrofitSingletonClass retrofitSingletonClass = RetrofitSingletonClass.getInstance(context);
                RetrofitClass retrofitClass = RetrofitSingletonClass.getRetrofitClassSingleInstance();

                WishListApiProcess wishListApiProcess = retrofitClass.getClient().create(WishListApiProcess.class);
                Call<NotificationWantTo> notificationWantToCall = wishListApiProcess.getNotificationBusiness(token, userID);
                notificationWantToCall.enqueue(new Callback<NotificationWantTo>() {
                    @Override
                    public void onResponse(Call<NotificationWantTo> call, Response<NotificationWantTo> response) {

                        int errorCode = response.code();
                        String whyIntroReason = "", howIntroReason = "";
                        String status = "";
                        List<Comment> comments = null;
                        String message = response.message();
                        if (errorCode == 200) {
                            whyIntroReason = response.body().getWhyIntroReason();
                            howIntroReason = response.body().getHowIntroReason();
                            comments = response.body().getComments();
                            status = response.body().getStatus();
                        }
                        onTheirInterestClick.getWanttoBusinessInfo(status, whyIntroReason, howIntroReason, comments);

                    }

                    @Override
                    public void onFailure(Call<NotificationWantTo> call, Throwable t) {

                        t.printStackTrace();
                        onTheirInterestClick.networkmessage(context.getString(R.string.networkconnection));
                    }
                });
            } else {
                onTheirInterestClick.networkmessage(context.getString(R.string.networkconnection));
            }

        } catch (Exception e) {
            onTheirInterestClick.networkmessage(context.getString(R.string.networkconnection));

        }
    }

}
