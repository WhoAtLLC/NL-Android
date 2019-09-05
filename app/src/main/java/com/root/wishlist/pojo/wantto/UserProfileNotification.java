package com.root.wishlist.pojo.wantto;

import com.google.gson.annotations.SerializedName;
import com.root.wishlist.pojo.mywishlist.Notifications;

import java.util.ArrayList;
import java.util.List;

public class UserProfileNotification {

    @SerializedName("id")
    private Integer id;
    @SerializedName("user")
    private Integer user;
    @SerializedName("first_name")
    private String firstName;
    @SerializedName("last_name")
    private String lastName;
    @SerializedName("email")
    private String email;
    @SerializedName("address")
    private String address;
    @SerializedName("address_two")
    private String addressTwo;
    @SerializedName("city")
    private String city;
    @SerializedName("state")
    private String state;
    @SerializedName("postal_code")
    private String postalCode;
    @SerializedName("country")
    private String country;
    @SerializedName("date_submitted")
    private String dateSubmitted;
    @SerializedName("handle")
    private String handle;
    @SerializedName("title")
    private String title;
    @SerializedName("company")
    private String company;
    @SerializedName("short_bio")
    private String shortBio;
    @SerializedName("bio")
    private String bio;
    @SerializedName("phone")
    private String phone;
    @SerializedName("devices")
    private List<Device> devices = new ArrayList<Device>();
    @SerializedName("image")
    private String image;
    @SerializedName("facebook_url")
    private String facebookUrl;
    @SerializedName("twitter_url")
    private String twitterUrl;
    @SerializedName("business_discussion")
    private String businessDiscussion;
    @SerializedName("business_additional")
    private String businessAdditional;
    @SerializedName("notifications")
    private Notifications notifications;

    public UserProfileNotification(String firstName, String lastName, String handle,
                                   String email, String shortBio, String phone, String image, String businessDiscussion, String businessAdditional) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.handle = handle;
        this.phone = phone;
        this.image = image;
        this.email = email;
        this.shortBio = shortBio;
        this.businessDiscussion = businessDiscussion;
        this.businessAdditional = businessAdditional;
    }

    public Integer getId() {
        return id;
    }

    public Integer getUser() {
        return user;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getAddressTwo() {
        return addressTwo;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getCountry() {
        return country;
    }

    public String getDateSubmitted() {
        return dateSubmitted;
    }

    public String getHandle() {
        return handle;
    }

    public String getTitle() {
        return title;
    }

    public String getCompany() {
        return company;
    }

    public String getShortBio() {
        return shortBio;
    }

    public String getBio() {
        return bio;
    }

    public String getPhone() {
        return phone;
    }

    public List<Device> getDevices() {
        return devices;
    }

    public String getImage() {
        return image;
    }

    public String getFacebookUrl() {
        return facebookUrl;
    }

    public String getTwitterUrl() {
        return twitterUrl;
    }

    public String getBusinessDiscussion() {
        return businessDiscussion;
    }

    public String getBusinessAdditional() {
        return businessAdditional;
    }

    public Notifications getNotifications() {
        return notifications;
    }


}
