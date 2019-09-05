package com.root.wishlist.pojo.leads.getintroduce;


import com.google.gson.annotations.SerializedName;
import com.root.wishlist.pojo.mywishlist.Notifications;

public class ReasonBean {

    public Notifications getNotifications() {
        return notifications;
    }

    public void setNotifications(Notifications notifications) {
        this.notifications = notifications;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    @SerializedName("Notifications")
    private Notifications notifications;
    @SerializedName("detail")
    private String detail;
}
