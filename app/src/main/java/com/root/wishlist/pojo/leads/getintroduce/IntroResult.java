package com.root.wishlist.pojo.leads.getintroduce;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;


public class IntroResult {

    @SerializedName("id")
    private Integer id;
    @SerializedName("user")
    private Object user;
    @SerializedName("first_name")
    private String firstName;
    @SerializedName("last_name")
    private String lastName;
    @SerializedName("email")
    private Object email;
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
    private Object handle;
    @SerializedName("title")
    private String title;
    @SerializedName("company")
    private Object company;
    @SerializedName("short_bio")
    private Object shortBio;
    @SerializedName("bio")
    private Object bio;
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
    private Object businessDiscussion;
    @SerializedName("business_additional")
    private Object businessAdditional;

    public IntroResult(int id, String firstName, String lastName, String title) {
        this.title = title;
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Object getUser() {
        return user;
    }

    public void setUser(Object user) {
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

    public Object getEmail() {
        return email;
    }

    public void setEmail(Object email) {
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

    public Object getHandle() {
        return handle;
    }

    public void setHandle(Object handle) {
        this.handle = handle;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Object getCompany() {
        return company;
    }

    public void setCompany(Object company) {
        this.company = company;
    }

    public Object getShortBio() {
        return shortBio;
    }

    public void setShortBio(Object shortBio) {
        this.shortBio = shortBio;
    }

    public Object getBio() {
        return bio;
    }

    public void setBio(Object bio) {
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

    public Object getBusinessDiscussion() {
        return businessDiscussion;
    }

    public void setBusinessDiscussion(Object businessDiscussion) {
        this.businessDiscussion = businessDiscussion;
    }

    public Object getBusinessAdditional() {
        return businessAdditional;
    }

    public void setBusinessAdditional(Object businessAdditional) {
        this.businessAdditional = businessAdditional;
    }


}
