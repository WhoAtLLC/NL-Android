package com.root.wishlist.util;

import com.google.gson.annotations.SerializedName;


public class RegistrationBean {

    @SerializedName("email")
    String email;
    @SerializedName("password")
    String password;
    @SerializedName("notifications[]")
    private String notifications[];
    @SerializedName("unread")
    private String unread;
    @SerializedName("token")
    private String token;
    @SerializedName("detail")
    private String detail;
    @SerializedName("first_name")
    private String first_name;
    @SerializedName("last_name")
    private String last_name;
    @SerializedName("user")
    private String user;
    @SerializedName("handle")
    private String handle;
    @SerializedName("devices[]")
    private String devices[];

    public RegistrationBean(String first_name, String last_name, String user, String handle, String devices[]) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.user = user;
        this.handle = handle;
        this.devices = devices;
    }

    public RegistrationBean(String notifications[], String token, String unread) {

        this.unread = unread;
        this.notifications = notifications;
        this.token = token;
    }

    public RegistrationBean(String notifications[], String detail) {
        this.detail = detail;
        this.notifications = notifications;

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUnread() {
        return unread;
    }

    public void setUnread(String unread) {
        this.unread = unread;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String[] getNotifications() {
        return notifications;
    }

    public void setNotifications(String[] notifications) {
        this.notifications = notifications;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getHandle() {
        return handle;
    }

    public void setHandle(String handle) {
        this.handle = handle;
    }

    public String[] getDevices() {
        return devices;
    }

    public void setDevices(String[] devices) {
        this.devices = devices;
    }
}
