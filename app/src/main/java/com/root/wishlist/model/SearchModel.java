package com.root.wishlist.model;


import android.content.Context;

import com.root.wishlist.util.RetrofitSingletonClass;
import com.root.wishlist.util.globalValues.NetworkConnection;
import com.root.wishlist.util.globalValues.RetrofitClass;
import com.root.wishlist.pojo.search.SearchActivity;
import com.root.wishlist.pojo.search.SearchResult;
import com.root.wishlist.R;
import com.root.wishlist.interfaces.WishListApiProcess;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

interface SearchData {
    interface FindSearchData {
        void getSearchResult(List<SearchResult> searchResults);

        void getmessage(String message);

    }

    void sendSearchQuery(Context context, String token, String searhdata, FindSearchData findSearchData);
}

public class SearchModel implements SearchData {


    @Override
    public void sendSearchQuery(final Context context, String token, String searhdata, final FindSearchData findSearchData) {

        try {
            if (NetworkConnection.isNetworkAvailable(context)) {

                RetrofitSingletonClass retrofitSingletonClass = RetrofitSingletonClass.getInstance(context);
                RetrofitClass retrofitClass = RetrofitSingletonClass.getRetrofitClassSingleInstance();


                WishListApiProcess wishListApiProcess = retrofitClass.getClient().create(WishListApiProcess.class);
                Call<SearchActivity> searchActivityCall = wishListApiProcess.SEARCH_ACTIVITY_CALL(token, searhdata);
                searchActivityCall.enqueue(new Callback<SearchActivity>() {
                    @Override
                    public void onResponse(Call<SearchActivity> call, Response<SearchActivity> response) {

                        int errorCode = response.code();
                        if (errorCode == 200) {
                            List<SearchResult> searchResults = response.body().getResults();
                            findSearchData.getSearchResult(response.body().getResults());
                        } else {
                            //findSearchData.getmessage(context.getString(R.string.networkconnection));
                        }
                    }

                    @Override
                    public void onFailure(Call<SearchActivity> call, Throwable t) {
                        findSearchData.getmessage(context.getString(R.string.networkconnection));
                    }
                });
            } else {
                findSearchData.getmessage(context.getString(R.string.networkconnection));
            }
        } catch (Exception e) {
            findSearchData.getmessage(context.getString(R.string.networkconnection));
        }

    }
}
