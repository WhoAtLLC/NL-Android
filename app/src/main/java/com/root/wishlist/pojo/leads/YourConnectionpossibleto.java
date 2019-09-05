package com.root.wishlist.pojo.leads;

import com.google.gson.annotations.SerializedName;


import java.io.Serializable;

public class YourConnectionpossibleto implements Serializable {


    @SerializedName("count")
    private Integer count;
    @SerializedName("short_bio")
    private String shortBio;
    @SerializedName("contact")
    private Integer contact;
    @SerializedName("handle")
    private String handle;
    @SerializedName("title")
    private String title;

    public YourConnectionpossibleto(String handle, int count, String shortBio) {
        this.count = count;
        this.handle = handle;
        this.shortBio = shortBio;

    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getShortBio() {
        return shortBio;
    }

    public void setShortBio(String shortBio) {
        this.shortBio = shortBio;
    }

    public Integer getContact() {
        return contact;
    }

    public void setContact(Integer contact) {
        this.contact = contact;
    }

    public String getHandle() {
        return handle;
    }

    public void setHandle(String handle) {
        this.handle = handle;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
