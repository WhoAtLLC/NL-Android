package com.root.wishlist.pojo;

import com.google.gson.annotations.SerializedName;
import com.root.wishlist.pojo.mywishlist.Notifications;
import com.root.wishlist.pojo.mywishlist.Result;

import java.util.ArrayList;
import java.util.List;

public class MyWishListInterest {

    @SerializedName("myBusinessDiscussion")
    private String myBusinessDiscussion;
    @SerializedName("myBusinessOtherInfo")
    private String myBusinessOtherInfo;

    @SerializedName("companiesofInterest")
    //private List<CompaniesofInterest> companiesofInterest = new ArrayList<CompaniesofInterest>();
    private List<Result> companiesofInterest = new ArrayList<Result>();
    @SerializedName("notifications")

    private Notifications notifications;

    public String getMyBusinessDiscussion() {
        return myBusinessDiscussion;
    }

    public String getMyBusinessOtherInfo() {
        return myBusinessOtherInfo;
    }

    public List<Result> getCompaniesofInterest() {
        return companiesofInterest;
    }


    public Notifications getNotifications() {
        return notifications;
    }


}
