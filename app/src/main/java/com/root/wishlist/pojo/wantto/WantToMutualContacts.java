package com.root.wishlist.pojo.wantto;


import com.google.gson.annotations.SerializedName;
import com.root.wishlist.pojo.mywishlist.Notifications;

import java.util.ArrayList;
import java.util.List;

public class WantToMutualContacts {
    @SerializedName("count")
    private Integer count;
    @SerializedName("notifications")
    private Notifications notifications;
    @SerializedName("previous")
    private String previous;
    @SerializedName("results")
    private List<WnatoResult> results = new ArrayList<WnatoResult>();
    @SerializedName("next")
    private String next;

    public String getNext() {
        return next;
    }

    public List<WnatoResult> getResults() {
        return results;
    }

    public Integer getCount() {
        return count;
    }


    public Notifications getNotifications() {
        return notifications;
    }
    public String getPrevious() {
        return previous;
    }


}
