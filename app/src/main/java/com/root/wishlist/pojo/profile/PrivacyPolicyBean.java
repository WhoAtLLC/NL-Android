package com.root.wishlist.pojo.profile;

import com.google.gson.annotations.SerializedName;
import com.root.wishlist.pojo.mywishlist.Notifications;

public class PrivacyPolicyBean {
    @SerializedName("content")
    private Content content;
    @SerializedName("notifications")
    private Notifications notifications;

    @SerializedName("content")
    public Content getContent() {
        return content;
    }


    @SerializedName("content")
    public void setContent(Content content) {
        this.content = content;
    }


    @SerializedName("notifications")
    public Notifications getNotifications() {
        return notifications;
    }


    @SerializedName("notifications")
    public void setNotifications(Notifications notifications) {
        this.notifications = notifications;
    }


}
