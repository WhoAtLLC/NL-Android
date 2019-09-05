package com.root.wishlist.pojo.wantto;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;


public class WnatoResult {

    @SerializedName("id")
    private Integer id;
    @SerializedName("user")
    private String user;
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
    private List<Object> devices = new ArrayList<Object>();
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

    public WnatoResult(String firstName, String lastName, String title, String company) {
        this.title = title;
        this.firstName = firstName;
        this.lastName = lastName;
        this.company = company;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddressTwo() {
        return addressTwo;
    }

    public void setAddressTwo(String addressTwo) {
        this.addressTwo = addressTwo;
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

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDateSubmitted() {
        return dateSubmitted;
    }

    public void setDateSubmitted(String dateSubmitted) {
        this.dateSubmitted = dateSubmitted;
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

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Object> getDevices() {
        return devices;
    }

    public void setDevices(List<Object> devices) {
        this.devices = devices;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getFacebookUrl() {
        return facebookUrl;
    }

    public void setFacebookUrl(String facebookUrl) {
        this.facebookUrl = facebookUrl;
    }

    public String getTwitterUrl() {
        return twitterUrl;
    }

    public void setTwitterUrl(String twitterUrl) {
        this.twitterUrl = twitterUrl;
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

}
