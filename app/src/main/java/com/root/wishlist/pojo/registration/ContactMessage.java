package com.root.wishlist.pojo.registration;

import com.google.gson.annotations.SerializedName;

public class ContactMessage {

    public String getResult() {
        return result;
    }

    @SerializedName("result")
    private String result;
}
