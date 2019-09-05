package com.root.wishlist.pojo.leads.getintroduce;

import com.google.gson.annotations.SerializedName;
import com.root.wishlist.pojo.mywishlist.Notifications;


public class UserDetails {

    public UserDetails(String handle, String shortBio, String bio, String image, String businessDiscussion, String businessAdditional) {
        this.handle = handle;
        this.shortBio = shortBio;
        this.bio = bio;
        this.image = image;
        this.businessDiscussion = businessDiscussion;
        this.businessAdditional = businessAdditional;
    }

    @SerializedName("handle")
    private String handle;
    @SerializedName("short_bio")
    private String shortBio;
    @SerializedName("bio")
    private String bio;
    @SerializedName("image")
    private String image;
    @SerializedName("business_discussion")
    private String businessDiscussion;
    @SerializedName("business_additional")
    private String businessAdditional;
    @SerializedName("notifications")
    private Notifications notifications;


    public String getHandle() {
        return handle;
    }

    public void setHandle(String handle) {
        this.handle = handle;
    }

    public String getShortBio() {
        return shortBio;
    }

    public void setShortBio(String shortBio) {
        this.shortBio = shortBio;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getBusinessDiscussion() {
        return businessDiscussion;
    }

    public void setBusinessDiscussion(String businessDiscussion) {
        this.businessDiscussion = businessDiscussion;
    }

    public String getBusinessAdditional() {
        return businessAdditional;
    }

    public void setBusinessAdditional(String businessAdditional) {
        this.businessAdditional = businessAdditional;
    }

    public Notifications getNotifications() {
        return notifications;
    }

    public void setNotifications(Notifications notifications) {
        this.notifications = notifications;
    }


}
