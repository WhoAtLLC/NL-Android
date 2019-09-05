package com.root.wishlist.pojo;


import com.google.gson.annotations.SerializedName;
import com.root.wishlist.pojo.mywishlist.Notifications;

public class SelectnetworkBean {

    @SerializedName("network_status")
    private String networkStatus;
    @SerializedName("notifications")
    private Notifications notifications;

    public String getNetworkStatus() {
        return networkStatus;
    }

    public void setNetworkStatus(String networkStatus) {
        this.networkStatus = networkStatus;
    }

    public Notifications getNotifications() {
        return notifications;
    }

    public void setNotifications(Notifications notifications) {
        this.notifications = notifications;
    }


}
