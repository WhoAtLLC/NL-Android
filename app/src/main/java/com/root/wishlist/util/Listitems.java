package com.root.wishlist.util;

import com.google.gson.annotations.SerializedName;

public class Listitems {

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getDate_created() {
        return date_created;
    }

    public void setDate_created(String date_created) {
        this.date_created = date_created;
    }

    public String getDate_changed() {
        return date_changed;
    }

    public void setDate_changed(String date_changed) {
        this.date_changed = date_changed;
    }

    public String getTitle_raw() {
        return title_raw;
    }

    public void setTitle_raw(String title_raw) {
        this.title_raw = title_raw;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getShort_bio() {
        return short_bio;
    }

    public void setShort_bio(String short_bio) {
        this.short_bio = short_bio;
    }

    public String getHandle() {
        return handle;
    }

    public void setHandle(String handle) {
        this.handle = handle;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress_two() {
        return address_two;
    }

    public void setAddress_two(String address_two) {
        this.address_two = address_two;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostal_code() {
        return postal_code;
    }

    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getFacebook_url() {
        return facebook_url;
    }

    public void setFacebook_url(String facebook_url) {
        this.facebook_url = facebook_url;
    }

    public String getTwitter_url() {
        return twitter_url;
    }

    public void setTwitter_url(String twitter_url) {
        this.twitter_url = twitter_url;
    }

    public String getDate_submitted() {
        return date_submitted;
    }

    public void setDate_submitted(String date_submitted) {
        this.date_submitted = date_submitted;
    }

    public String getApproved() {
        return approved;
    }

    public void setApproved(String approved) {
        this.approved = approved;
    }

    public String getLast_import() {
        return last_import;
    }

    public void setLast_import(String last_import) {
        this.last_import = last_import;
    }

    public String getWelcomed() {
        return welcomed;
    }

    public void setWelcomed(String welcomed) {
        this.welcomed = welcomed;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getTitles() {
        return titles;
    }

    public void setTitles(String titles) {
        this.titles = titles;
    }

    @SerializedName("id")
    private int id;
    @SerializedName("slug")
    private String slug;
    @SerializedName("date_created")
    private String date_created;
    @SerializedName("date_changed")
    private String date_changed;
    @SerializedName("title_raw")
    private String title_raw;
    @SerializedName("first_name")
    private String first_name;
    @SerializedName("last_name")
    private String last_name;
    @SerializedName("handle")
    private String handle;
    @SerializedName("email")
    private String email;
    @SerializedName("short_bio")
    private String short_bio;
    @SerializedName("bio")
    private String bio;
    @SerializedName("phone")
    private String phone;
    @SerializedName("address")
    private String address;
    @SerializedName("address_two")
    private String address_two;
    @SerializedName("city")
    private String city;
    @SerializedName("state")
    private String state;
    @SerializedName("postal_code")
    private String postal_code;
    @SerializedName("country")
    private String country;
    @SerializedName("facebook_url")
    private String facebook_url;
    @SerializedName("twitter_url")
    private String twitter_url;
    @SerializedName("date_submitted")
    private String date_submitted;
    @SerializedName("approved")
    private String approved;
    @SerializedName("last_import")
    private String last_import;
    @SerializedName("welcomed")
    private String welcomed;
    @SerializedName("user")
    private String user;
    @SerializedName("titles")
    private String titles;

}
