package com.root.wishlist.pojo.mywishlist;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/*@SerializedNameOrder({
        "id",
        "slug",
        "date_created",
        "date_changed",
        "title_raw",
        "first_name",
        "middle_name",
        "last_name",
        "handle",
        "email",
        "short_bio",
        "bio",
        "phone",
        "address",
        "address_two",
        "city",
        "state",
        "postal_code",
        "country",
        "facebook_url",
        "twitter_url",
        "date_submitted",
        "approved",
        "last_import",
        "welcomed",
        "user",
        "titles"
})*/
public class Approved {
    @SerializedName("id")
    private Integer id;
    @SerializedName("slug")
    private String slug;
    @SerializedName("date_created")
    private String dateCreated;
    @SerializedName("date_changed")
    private String dateChanged;
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
    @SerializedName("user")
    private Integer user;
    @SerializedName("titles")
    private List<Object> titles = new ArrayList<Object>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getDateChanged() {
        return dateChanged;
    }

    public void setDateChanged(String dateChanged) {
        this.dateChanged = dateChanged;
    }

    public String getTitleRaw() {
        return titleRaw;
    }

    public void setTitleRaw(String titleRaw) {
        this.titleRaw = titleRaw;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getHandle() {
        return handle;
    }

    public void setHandle(String handle) {
        this.handle = handle;
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

    public String getDateSubmitted() {
        return dateSubmitted;
    }

    public void setDateSubmitted(String dateSubmitted) {
        this.dateSubmitted = dateSubmitted;
    }

    public Integer getApproved() {
        return approved;
    }

    public void setApproved(Integer approved) {
        this.approved = approved;
    }

    public Integer getLastImport() {
        return lastImport;
    }

    public void setLastImport(Integer lastImport) {
        this.lastImport = lastImport;
    }

    public Object getWelcomed() {
        return welcomed;
    }

    public void setWelcomed(Object welcomed) {
        this.welcomed = welcomed;
    }

    public Integer getUser() {
        return user;
    }

    public void setUser(Integer user) {
        this.user = user;
    }

    public List<Object> getTitles() {
        return titles;
    }

    public void setTitles(List<Object> titles) {
        this.titles = titles;
    }


}
