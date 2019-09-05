package com.root.wishlist.pojo.leads;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CompanyLeads implements Serializable {
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
    @SerializedName("title")
    private String title;
    @SerializedName("alias")
    private Object alias;
    @SerializedName("profile_image_url")
    private String profileImageUrl;
    @SerializedName("type")
    private String type;
    @SerializedName("primary_role")
    private String primaryRole;
    @SerializedName("unique_thirdparty_ref")
    private String uniqueThirdpartyRef;
    @SerializedName("short_description")
    private String shortDescription;
    @SerializedName("funding_round_name")
    private Object fundingRoundName;
    @SerializedName("crunchbase_url")
    private String crunchbaseUrl;
    @SerializedName("homepage_url")
    private String homepageUrl;
    @SerializedName("facebook_url")
    private String facebookUrl;
    @SerializedName("twitter_url")
    private Object twitterUrl;
    @SerializedName("linkedin_url")
    private String linkedinUrl;
    @SerializedName("stock_symbol")
    private String stockSymbol;
    @SerializedName("location_city")
    private String locationCity;
    @SerializedName("location_region")
    private String locationRegion;
    @SerializedName("location_country_code")
    private String locationCountryCode;
    @SerializedName("approval_date")
    private String approvalDate;
    @SerializedName("status")
    private String status;
    @SerializedName("approved")
    private Integer approved;
    @SerializedName("reviewed")
    private Object reviewed;

    public CompanyLeads(int id,String profileImageUrl, String title) {
        this.title = title;
        this.profileImageUrl = profileImageUrl;
        this.id=id;

    }

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

    public Object getGraphId() {
        return graphId;
    }

    public void setGraphId(Object graphId) {
        this.graphId = graphId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Object getAlias() {
        return alias;
    }

    public void setAlias(Object alias) {
        this.alias = alias;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPrimaryRole() {
        return primaryRole;
    }

    public void setPrimaryRole(String primaryRole) {
        this.primaryRole = primaryRole;
    }

    public String getUniqueThirdpartyRef() {
        return uniqueThirdpartyRef;
    }

    public void setUniqueThirdpartyRef(String uniqueThirdpartyRef) {
        this.uniqueThirdpartyRef = uniqueThirdpartyRef;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public Object getFundingRoundName() {
        return fundingRoundName;
    }

    public void setFundingRoundName(Object fundingRoundName) {
        this.fundingRoundName = fundingRoundName;
    }

    public String getCrunchbaseUrl() {
        return crunchbaseUrl;
    }

    public void setCrunchbaseUrl(String crunchbaseUrl) {
        this.crunchbaseUrl = crunchbaseUrl;
    }

    public String getHomepageUrl() {
        return homepageUrl;
    }

    public void setHomepageUrl(String homepageUrl) {
        this.homepageUrl = homepageUrl;
    }

    public String getFacebookUrl() {
        return facebookUrl;
    }

    public void setFacebookUrl(String facebookUrl) {
        this.facebookUrl = facebookUrl;
    }

    public Object getTwitterUrl() {
        return twitterUrl;
    }

    public void setTwitterUrl(Object twitterUrl) {
        this.twitterUrl = twitterUrl;
    }

    public String getLinkedinUrl() {
        return linkedinUrl;
    }

    public void setLinkedinUrl(String linkedinUrl) {
        this.linkedinUrl = linkedinUrl;
    }

    public String getStockSymbol() {
        return stockSymbol;
    }

    public void setStockSymbol(String stockSymbol) {
        this.stockSymbol = stockSymbol;
    }

    public String getLocationCity() {
        return locationCity;
    }

    public void setLocationCity(String locationCity) {
        this.locationCity = locationCity;
    }

    public String getLocationRegion() {
        return locationRegion;
    }

    public void setLocationRegion(String locationRegion) {
        this.locationRegion = locationRegion;
    }

    public String getLocationCountryCode() {
        return locationCountryCode;
    }

    public void setLocationCountryCode(String locationCountryCode) {
        this.locationCountryCode = locationCountryCode;
    }

    public String getApprovalDate() {
        return approvalDate;
    }

    public void setApprovalDate(String approvalDate) {
        this.approvalDate = approvalDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getApproved() {
        return approved;
    }

    public void setApproved(Integer approved) {
        this.approved = approved;
    }

    public Object getReviewed() {
        return reviewed;
    }

    public void setReviewed(Object reviewed) {
        this.reviewed = reviewed;
    }

}
