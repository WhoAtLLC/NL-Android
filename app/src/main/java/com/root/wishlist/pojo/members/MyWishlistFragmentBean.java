package com.root.wishlist.pojo.members;


import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class MyWishlistFragmentBean {

    @SerializedName("count")
    private Integer count;
    @SerializedName("notifications")
    private Notifications notifications;
    @SerializedName("previous")
    private String previous;
    @SerializedName("results")
    private List<Result> results = new ArrayList<Result>();
    @SerializedName("next")
    private String next;
    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public Notifications getNotifications() {
        return notifications;
    }

    public void setNotifications(Notifications notifications) {
        this.notifications = notifications;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

}
