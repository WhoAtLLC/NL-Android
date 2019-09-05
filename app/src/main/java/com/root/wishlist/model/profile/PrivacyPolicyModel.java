package com.root.wishlist.model.profile;

import android.content.Context;

import com.root.wishlist.util.RetrofitSingletonClass;
import com.root.wishlist.util.globalValues.RetrofitClass;
import com.root.wishlist.pojo.profile.PrivacyPolicyBean;
import com.root.wishlist.interfaces.WishListApiProcess;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


interface PrivacyPolicy {
    interface OnPrivacyPolicyOnListener {

        void setId(int id);

        void setSlug(String slug);

        void setDateCreated(String dateCreated);

        void setDateChanged(String dateChanged);

        void setGraphId(Object graphId);

        void setLabel(String label);

        void setContent(String content);
    }

    void privacyPolicyModel(Context context, OnPrivacyPolicyOnListener onPrivacyPolicyOnListener);

    void termandConditionModel(Context context, OnPrivacyPolicyOnListener onPrivacyPolicyOnListener);

}

public class PrivacyPolicyModel implements PrivacyPolicy {

    @Override
    public void privacyPolicyModel(Context context, final OnPrivacyPolicyOnListener onPrivacyPolicyOnListener) {

        RetrofitSingletonClass retrofitSingletonClass = RetrofitSingletonClass.getInstance(context);
        RetrofitClass retrofitClass = RetrofitSingletonClass.getRetrofitClassSingleInstance();

        WishListApiProcess myApiEndpointInterface = retrofitClass.getClient().create(WishListApiProcess.class);

        Call<PrivacyPolicyBean> companyOfInterestBeanCall = myApiEndpointInterface.getPrivacyPolicybean();
        companyOfInterestBeanCall.enqueue(new Callback<PrivacyPolicyBean>() {
            @Override
            public void onResponse(Call<PrivacyPolicyBean> call, Response<PrivacyPolicyBean> response) {

                int statusCode = response.code();
                if (statusCode == 200) {
                    onPrivacyPolicyOnListener.setId(response.body().getContent().getId());
                    onPrivacyPolicyOnListener.setSlug(response.body().getContent().getSlug());
                    onPrivacyPolicyOnListener.setDateCreated(response.body().getContent().getDateCreated());
                    onPrivacyPolicyOnListener.setDateChanged(response.body().getContent().getDateChanged());
                    onPrivacyPolicyOnListener.setGraphId(response.body().getContent().getGraphId());
                    onPrivacyPolicyOnListener.setLabel(response.body().getContent().getLabel());
                    onPrivacyPolicyOnListener.setContent(response.body().getContent().getContent());

                }
            }

            @Override
            public void onFailure(Call<PrivacyPolicyBean> call, Throwable t) {

                t.printStackTrace();
            }
        });
    }

    @Override
    public void termandConditionModel(Context context, final OnPrivacyPolicyOnListener onPrivacyPolicyOnListener) {

        RetrofitSingletonClass retrofitSingletonClass = RetrofitSingletonClass.getInstance(context);
        RetrofitClass retrofitClass = RetrofitSingletonClass.getRetrofitClassSingleInstance();

        WishListApiProcess myApiEndpointInterface = retrofitClass.getClient().create(WishListApiProcess.class);

        Call<PrivacyPolicyBean> companyOfInterestBeanCall = myApiEndpointInterface.getTermAndCondition();
        companyOfInterestBeanCall.enqueue(new Callback<PrivacyPolicyBean>() {
            @Override
            public void onResponse(Call<PrivacyPolicyBean> call, Response<PrivacyPolicyBean> response) {
                int statusCode = response.code();
                if (statusCode == 200) {
                    onPrivacyPolicyOnListener.setId(response.body().getContent().getId());
                    onPrivacyPolicyOnListener.setSlug(response.body().getContent().getSlug());
                    onPrivacyPolicyOnListener.setDateCreated(response.body().getContent().getDateCreated());
                    onPrivacyPolicyOnListener.setDateChanged(response.body().getContent().getDateChanged());
                    onPrivacyPolicyOnListener.setGraphId(response.body().getContent().getGraphId());
                    onPrivacyPolicyOnListener.setLabel(response.body().getContent().getLabel());
                    onPrivacyPolicyOnListener.setContent(response.body().getContent().getContent());

                }
            }

            @Override
            public void onFailure(Call<PrivacyPolicyBean> call, Throwable t) {

                t.printStackTrace();
            }
        });
    }


}