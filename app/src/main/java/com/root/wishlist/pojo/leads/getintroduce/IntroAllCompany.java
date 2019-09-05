package com.root.wishlist.pojo.leads.getintroduce;

import com.google.gson.annotations.SerializedName;
import com.root.wishlist.pojo.mywishlist.Notifications;

//import org.codehaus.jackson.annotate.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class IntroAllCompany {
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

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public List<CompanyResults> getResults() {
        return results;
    }

    public void setResults(List<CompanyResults> results) {
        this.results = results;
    }

    public Notifications getNotifications() {
        return notifications;
    }

    public void setNotifications(Notifications notifications) {
        this.notifications = notifications;
    }

    @SerializedName("count")
    private Integer count;
    @SerializedName("next")
    private String next;
    @SerializedName("previous")
    private String previous;
    @SerializedName("results")
    private List<CompanyResults> results = new ArrayList<CompanyResults>();
    @SerializedName("notifications")
    private Notifications notifications;


}
