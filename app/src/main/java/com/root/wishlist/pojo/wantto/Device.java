package com.root.wishlist.pojo.wantto;


import com.google.gson.annotations.SerializedName;

public class Device {

    @SerializedName("id")
    private Integer id;
    @SerializedName("udid")
    private String udid;
    @SerializedName("device_type")
    private String deviceType;
    @SerializedName("primary")
    private Boolean primary;
    @SerializedName("profile")
    private Integer profile;

    public Integer getId() {
        return id;
    }

    public String getUdid() {
        return udid;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public Boolean getPrimary() {
        return primary;
    }

    public Integer getProfile() {
        return profile;
    }


}
