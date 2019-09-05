package com.root.wishlist.pojo;


import com.google.gson.annotations.SerializedName;
import com.root.wishlist.pojo.mywishlist.Notifications;

public class EmailStatus {

    @SerializedName("notifications")
    private Notifications notifications;
    @SerializedName("result")
    private Boolean result;

    public Notifications getNotifications() {
        return notifications;
    }

    public void setNotifications(Notifications notifications) {
        this.notifications = notifications;
    }

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }


}
