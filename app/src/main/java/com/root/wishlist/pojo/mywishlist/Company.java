package com.root.wishlist.pojo.mywishlist;

/*import org.codehaus.jackson.annotate.SerializedName;
import org.codehaus.jackson.annotate.SerializedNameOrder;*/

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/*@SerializedNameOrder({
        "count",
        "next",
        "previous",
        "results",
        "notifications"
})*/
public class Company
{
    @SerializedName("count")
    private Integer count;
    @SerializedName("next")
    private String next;
    @SerializedName("previous")
    private String previous;
    @SerializedName("results")
    private List<Result> results = new ArrayList<Result>();
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

    public Notifications getNotifications() {
        return notifications;
    }

    public void setNotifications(Notifications notifications) {
        this.notifications = notifications;
    }


}