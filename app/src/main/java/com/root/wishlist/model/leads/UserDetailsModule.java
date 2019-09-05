package com.root.wishlist.model.leads;


import android.content.Context;

import com.root.wishlist.util.RetrofitSingletonClass;
import com.root.wishlist.util.globalValues.RetrofitClass;
import com.root.wishlist.pojo.leads.getintroduce.UserDetails;
import com.root.wishlist.pojo.wantto.UserProfileNotification;
import com.root.wishlist.interfaces.WishListApiProcess;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

interface userDetailsInt {
    interface SetuserDetails {
        void setUserDetails(String handle, String shortBio, String bio, String image, String businessDiscussion, String businessAdditional);

        void setNotificationProfile(String title, String firstName, String lastName, String handle,
                                    String email, String shortBio, String phone, String image, String businessDiscussion, String businessAdditional,String bio);
    }

    void getUserDetail(Context context,String username, String token, SetuserDetails setuserDetails);

    void getNotificationProfile(Context context, String username, String token, SetuserDetails setuserDetails);

    void getMemberUserDetails(Context context, String username, String token, SetuserDetails setuserDetails);
}

public class UserDetailsModule implements userDetailsInt {


    @Override
    public void getUserDetail(Context context, String username, String token, final SetuserDetails setuserDetails) {

        RetrofitSingletonClass retrofitSingletonClass = RetrofitSingletonClass.getInstance(context);
        RetrofitClass retrofitClass = RetrofitSingletonClass.getRetrofitClassSingleInstance();

        WishListApiProcess wishListApiProcess = retrofitClass.getClient().create(WishListApiProcess.class);
        Call<UserDetails> theirInterestCall = wishListApiProcess.getUserDetails(token, username);
        theirInterestCall.enqueue(new Callback<UserDetails>() {
            @Override
            public void onResponse(Call<UserDetails> call, Response<UserDetails> response) {

                int code = response.code();
                String message = response.message();
                if (code == 200) {
                    String name = response.body().getHandle();
                    setuserDetails.setUserDetails(response.body().getHandle(), response.body().getShortBio(), response.body().getBio(), response.body().getImage(), response.body().getBusinessDiscussion(), response.body().getBusinessAdditional());
                }
            }

            @Override
            public void onFailure(Call<UserDetails> call, Throwable t) {

            }
        });
    }

    @Override
    public void getNotificationProfile(Context context, String username, String token, final SetuserDetails setuserDetails) {

        RetrofitSingletonClass retrofitSingletonClass = RetrofitSingletonClass.getInstance(context);
        RetrofitClass retrofitClass = RetrofitSingletonClass.getRetrofitClassSingleInstance();

        WishListApiProcess wishListApiProcess = retrofitClass.getClient().create(WishListApiProcess.class);
        Call<UserProfileNotification> userProfileNotificationCall = wishListApiProcess.getUserProfile(token, username);
        userProfileNotificationCall.enqueue(new Callback<UserProfileNotification>() {
            @Override
            public void onResponse(Call<UserProfileNotification> call, Response<UserProfileNotification> response) {

                int code = response.code();
                String message = response.message();
                if (code == 200) {
                    String name = response.body().getHandle();
                    setuserDetails.setNotificationProfile(response.body().getTitle(), response.body().getFirstName(), response.body().getLastName(),
                            response.body().getHandle(), response.body().getEmail(), response.body().getShortBio(), response.body().getPhone(), response.body().getImage(), response.body().getBusinessDiscussion(), response.body().getBusinessAdditional(),response.body().getBio());
                }
            }

            @Override
            public void onFailure(Call<UserProfileNotification> call, Throwable t) {

            }
        });
    }

    @Override
    public void getMemberUserDetails(Context context, String username, String token, final SetuserDetails setuserDetails) {

        RetrofitSingletonClass retrofitSingletonClass = RetrofitSingletonClass.getInstance(context);
        RetrofitClass retrofitClass = RetrofitSingletonClass.getRetrofitClassSingleInstance();

        WishListApiProcess wishListApiProcess = retrofitClass.getClient().create(WishListApiProcess.class);
        Call<UserDetails> theirInterestCall = wishListApiProcess.getUserDetails(token, username);
        theirInterestCall.enqueue(new Callback<UserDetails>() {
            @Override
            public void onResponse(Call<UserDetails> call, Response<UserDetails> response) {

                int code = response.code();
                String message = response.message();
                if (code == 200) {
                    String name = response.body().getHandle();
                    setuserDetails.setUserDetails(response.body().getHandle(), response.body().getShortBio(), response.body().getBio(), response.body().getImage(), response.body().getBusinessDiscussion(), response.body().getBusinessAdditional());
                }
            }

            @Override
            public void onFailure(Call<UserDetails> call, Throwable t) {

            }
        });

    }
}