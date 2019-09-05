package com.root.wishlist.pojo.registration;


import com.google.gson.annotations.SerializedName;
import com.root.wishlist.pojo.members.Notifications;

public class UploadContact {

    @SerializedName("notifications")
    private Notifications notifications;
    @SerializedName("contact")
    private ContactMessage contact;

    public Notifications getNotifications() {
        return notifications;
    }

    public ContactMessage getContact() {
        return contact;
    }


}
