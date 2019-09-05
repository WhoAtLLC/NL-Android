package com.root.wishlist.pojo.profile;

import com.google.gson.annotations.SerializedName;


public class Device {
    @SerializedName("id")
    private Integer id;
    @SerializedName("udid")
    private String udid;
    @SerializedName("device_type")
    private String deviceType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getPrimary() {
        return primary;
    }

    public void setPrimary(Boolean primary) {
        this.primary = primary;
    }

    public Integer getProfile() {
        return profile;
    }

    public void setProfile(Integer profile) {
        this.profile = profile;
    }

    public String getUdid() {
        return udid;
    }

    public void setUdid(String udid) {
        this.udid = udid;
    }

    @SerializedName("primary")
    private Boolean primary;
    @SerializedName("profile")
    private Integer profile;

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }
}
