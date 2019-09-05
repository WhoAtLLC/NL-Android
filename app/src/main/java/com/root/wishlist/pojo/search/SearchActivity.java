package com.root.wishlist.pojo.search;


import com.google.gson.annotations.SerializedName;
import com.root.wishlist.pojo.members.Notifications;

//import org.codehaus.jackson.annotate.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity {

    @SerializedName("count")
    private Integer count;
    @SerializedName("notifications")
    private Notifications notifications;
    @SerializedName("previous")
    private String previous;
    @SerializedName("results")
    private List<SearchResult> results = new ArrayList<SearchResult>();
    @SerializedName("next")
    private String next;


    public Integer getCount() {
        return count;
    }

    public Notifications getNotifications() {
        return notifications;
    }

    public String getPrevious() {
        return previous;
    }

    public List<SearchResult> getResults() {
        return results;
    }

    public String getNext() {
        return next;
    }


}
