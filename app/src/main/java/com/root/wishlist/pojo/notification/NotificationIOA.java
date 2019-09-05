package com.root.wishlist.pojo.notification;


import com.google.gson.annotations.SerializedName;
import com.root.wishlist.pojo.members.Notifications;

import java.util.ArrayList;
import java.util.List;

public class NotificationIOA {
    @SerializedName("inbound")
    private List<Inbound> inbound = new ArrayList<Inbound>();
    @SerializedName("outbound")
    private List<Outbound> outbound = new ArrayList<Outbound>();
    @SerializedName("notifications")
    private Notifications notifications;
    @SerializedName("archived")
    private List<Archived> archived = new ArrayList<Archived>();

    public List<Archived> getArchived() {
        return archived;
    }

    public void setArchived(List<Archived> archived) {
        this.archived = archived;
    }

    public List<Inbound> getInbound() {
        return inbound;
    }

    public void setInbound(List<Inbound> inbound) {
        this.inbound = inbound;
    }

    public Notifications getNotifications() {
        return notifications;
    }

    public void setNotifications(Notifications notifications) {
        this.notifications = notifications;
    }

    public List<Outbound> getOutbound() {
        return outbound;
    }

    public void setOutbound(List<Outbound> outbound) {
        this.outbound = outbound;
    }


}
