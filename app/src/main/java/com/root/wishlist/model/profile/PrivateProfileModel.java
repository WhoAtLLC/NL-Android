package com.root.wishlist.model.profile;

import android.content.Context;

import com.root.wishlist.util.RetrofitSingletonClass;
import com.root.wishlist.util.globalValues.NetworkConnection;
import com.root.wishlist.util.globalValues.RetrofitClass;
import com.root.wishlist.pojo.profile.PrivateProfileBean;
import com.root.wishlist.R;
import com.root.wishlist.interfaces.WishListApiProcess;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


interface PrivateProfile {
    interface OnPrivateProfileListener {

        void setPrivateProfilePicture(String privateProfilePicture);

        void setPrivateTitle(String privateTitle);

        void setPrivateFirstName(String privateFirstName);

        void setPrivatetBio(String privatetBio);

        void setPrivateEmail(String privateEmail);

        void setPrivateComapny(String privateComapny);

        void setPrivatePhone(String privatePhone);

        void setPrivateShortBio(String shortBio);

        void setPrivateBusinessDiscussion(String businessDiscussion);

        void setPrivateBusinessAdditional(String businessAdditional);

        void setPrivateHandle(String privateHandle);

        void setNetworkMessage(String connection);
    }

    void privateProfileInfo(Context context, String token, OnPrivateProfileListener onPrivateProfileListener);
}

public class PrivateProfileModel implements PrivateProfile {


    @Override
    public void privateProfileInfo(final Context context, String token, final OnPrivateProfileListener onPrivateProfileListener) {

        try {
            if (NetworkConnection.isNetworkAvailable(context)) {

                RetrofitSingletonClass retrofitSingletonClass = RetrofitSingletonClass.getInstance(context);
                RetrofitClass retrofitClass = RetrofitSingletonClass.getRetrofitClassSingleInstance();

                WishListApiProcess myApiEndpointInterface = retrofitClass.getClient().create(WishListApiProcess.class);
                Call<PrivateProfileBean> companyOfInterestBeanCall = myApiEndpointInterface.getPrivateProfileFragment(token);
                companyOfInterestBeanCall.enqueue(new Callback<PrivateProfileBean>() {
                    @Override
                    public void onResponse(Call<PrivateProfileBean> call, Response<PrivateProfileBean> response) {

                        int statusCode = response.code();
                        if (statusCode == 200) {
                            onPrivateProfileListener.setPrivateProfilePicture(response.body().getImage());
                            onPrivateProfileListener.setPrivateFirstName(response.body().getFirstName() + " " + response.body().getLastName());
                            onPrivateProfileListener.setPrivateTitle(response.body().getTitle());
                            onPrivateProfileListener.setPrivateComapny(response.body().getCompany());
                            onPrivateProfileListener.setPrivatePhone(response.body().getPhone());
                            onPrivateProfileListener.setPrivateEmail(response.body().getEmail());
                            onPrivateProfileListener.setPrivatetBio(response.body().getBio());
                            onPrivateProfileListener.setPrivateShortBio(response.body().getShortBio());
                            onPrivateProfileListener.setPrivateBusinessDiscussion(response.body().getBusinessDiscussion());
                            onPrivateProfileListener.setPrivateBusinessAdditional(response.body().getBusinessAdditional());
                            onPrivateProfileListener.setPrivateHandle(response.body().getHandle());

                        }
                    }

                    @Override
                    public void onFailure(Call<PrivateProfileBean> call, Throwable t) {

                        t.printStackTrace();
                        onPrivateProfileListener.setNetworkMessage(context.getString(R.string.networkconnection));
                    }
                });
            } else {
                onPrivateProfileListener.setNetworkMessage(context.getString(R.string.networkconnection));
            }
        } catch (Exception e) {
            onPrivateProfileListener.setNetworkMessage(context.getString(R.string.networkconnection));
        }
    }
}
