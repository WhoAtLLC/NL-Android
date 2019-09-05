package com.root.wishlist.pojo.members;

import com.google.gson.annotations.SerializedName;


public class Notifications {
    @SerializedName("unread")
    private Integer unread;
    public Integer getUnread() {
        return unread;
    }

    public void setUnread(Integer unread) {
        this.unread = unread;
    }


}
