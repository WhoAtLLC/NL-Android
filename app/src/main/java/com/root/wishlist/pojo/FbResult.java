package com.root.wishlist.pojo;

import com.google.gson.annotations.SerializedName;

public class FbResult {

    @SerializedName("result")
    private String result;

    public FbResult(String result) {
        this.result = result;

    }

    public String getResult() {
        return result;
    }
}
