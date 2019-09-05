package com.root.wishlist.pojo.wantto;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;


public class Approved {

    @SerializedName("id")
    private Integer id;
    @SerializedName("slug")
    private String slug;
    @SerializedName("date_created")
    private String dateCreated;
    @SerializedName("date_changed")
    private String dateChanged;
    @SerializedName("graph_id")
    private Object graphId;
    @SerializedName("title_raw")
    private String titleRaw;
    @SerializedName("first_name")
    private String firstName;
    @SerializedName("middle_name")
    private String middleName;
    @SerializedName("last_name")
    private String lastName;
    @SerializedName("handle")
    private String handle;
    @SerializedName("email")
    private String email;
    @SerializedName("short_bio")
    private String shortBio;
    @SerializedName("bio")
    private String bio;
    @SerializedName("phone")
    private String phone;
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
    @SerializedName("facebook_url")
    private String facebookUrl;
    @SerializedName("twitter_url")
    private String twitterUrl;
    @SerializedName("date_submitted")
    private String dateSubmitted;
    @SerializedName("approved")
    private Integer approved;
    @SerializedName("last_import")
    private Integer lastImport;
    @SerializedName("welcomed")
    private Object welcomed;


    public Integer getId() {
        return id;
    }

    public String getSlug() {
        return slug;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public String getDateChanged() {
        return dateChanged;
    }

    public Object getGraphId() {
        return graphId;
    }

    public String getTitleRaw() {
        return titleRaw;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getHandle() {
        return handle;
    }

    public String getEmail() {
        return email;
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

    public String getFacebookUrl() {
        return facebookUrl;
    }

    public String getTwitterUrl() {
        return twitterUrl;
    }

    public String getDateSubmitted() {
        return dateSubmitted;
    }

    public Integer getApproved() {
        return approved;
    }

    public Integer getLastImport() {
        return lastImport;
    }

    public Object getWelcomed() {
        return welcomed;
    }

    public Integer getUser() {
        return user;
    }

    public List<Object> getTitles() {
        return titles;
    }

    @SerializedName("user")
    private Integer user;
    @SerializedName("titles")
    private List<Object> titles = new ArrayList<Object>();
}
