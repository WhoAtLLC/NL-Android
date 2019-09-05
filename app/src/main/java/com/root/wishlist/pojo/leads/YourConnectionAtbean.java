package com.root.wishlist.pojo.leads;


import com.google.gson.annotations.SerializedName;
import com.root.wishlist.pojo.mywishlist.Notifications;

import java.util.ArrayList;
import java.util.List;

public class YourConnectionAtbean {

    @SerializedName("count")
    private Integer count;
    @SerializedName("notifications")
    private Notifications notifications;
    @SerializedName("previous")
    private String previous;
    @SerializedName("results")
    private List<YourConnectionAtResult> results = new ArrayList<YourConnectionAtResult>();
    @SerializedName("next")
    private String next;
    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Notifications getNotifications() {
        return notifications;
    }

    public void setNotifications(Notifications notifications) {
        this.notifications = notifications;
    }

    public List<YourConnectionAtResult> getResults() {
        return results;
    }

    public void setResults(List<YourConnectionAtResult> results) {
        this.results = results;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }


}
