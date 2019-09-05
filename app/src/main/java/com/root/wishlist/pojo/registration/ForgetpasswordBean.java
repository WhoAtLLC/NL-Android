package com.root.wishlist.pojo.registration;

import com.google.gson.annotations.SerializedName;
import com.root.wishlist.pojo.mywishlist.Notifications;

public class ForgetpasswordBean {


    @SerializedName("notifications")
    private Notifications notifications;
    @SerializedName("detail")
    private String detail;

    public ForgetpasswordBean(Notifications notifications, String detail) {
        this.detail=detail;
        this.notifications=notifications;
    }

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


}
