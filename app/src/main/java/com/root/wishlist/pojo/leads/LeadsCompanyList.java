package com.root.wishlist.pojo.leads;

import com.google.gson.annotations.SerializedName;
import com.root.wishlist.pojo.mywishlist.Notifications;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dal on 20/10/16.
 */
public class LeadsCompanyList {

    @SerializedName("count")
    private Integer count;
    @SerializedName("next")
    private String next;
    @SerializedName("previous")
    private Object previous;
    @SerializedName("results")
    private List<ResultLeads> results = new ArrayList<ResultLeads>();
    @SerializedName("notifications")
    private Notifications notifications;



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

    public Object getPrevious() {
        return previous;
    }

    public void setPrevious(Object previous) {
        this.previous = previous;
    }

    public List<ResultLeads> getResults() {
        return results;
    }

    public void setResults(List<ResultLeads> results) {
        this.results = results;
    }

    public Notifications getNotifications() {
        return notifications;
    }

    public void setNotifications(Notifications notifications) {
        this.notifications = notifications;
    }
}
