package com.root.wishlist.pojo.wantto;


import com.google.gson.annotations.SerializedName;

public class CompaniesofInterest {

    @SerializedName("id")
    private Integer id;
    @SerializedName("slug")
    private String slug;
    @SerializedName("profile_image_url")
    private String profileImageUrl;
    @SerializedName("date_created")
    private String dateCreated;
    @SerializedName("date_changed")
    private String dateChanged;
    @SerializedName("graph_id")
    private String graphId;
    @SerializedName("title")
    private String title;
    @SerializedName("alias")
    private String alias;
    @SerializedName("logo")
    private String logo;
    @SerializedName("type")
    private String type;
    @SerializedName("primary_role")
    private String primaryRole;
    @SerializedName("unique_thirdparty_ref")
    private String uniqueThirdpartyRef;
    @SerializedName("short_description")
    private String shortDescription;
    @SerializedName("funding_round_name")
    private String fundingRoundName;
    @SerializedName("crunchbase_url")
    private String crunchbaseUrl;
    @SerializedName("homepage_url")
    private String homepageUrl;
    @SerializedName("facebook_url")
    private String facebookUrl;
    @SerializedName("twitter_url")
    private String twitterUrl;
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
    private Approved approved;
    @SerializedName("reviewed[]")
    private String reviewed;

    public CompaniesofInterest(String slug, String profileImageUrl) {
        this.slug = slug;
        this.profileImageUrl = profileImageUrl;
    }

    public CompaniesofInterest(String title, String profileImageUrl, int id) {
        this.title = title;
        this.profileImageUrl = profileImageUrl;
        this.id = id;

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

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
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

    public String getGraphId() {
        return graphId;
    }

    public void setGraphId(String graphId) {
        this.graphId = graphId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
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

    public String getFundingRoundName() {
        return fundingRoundName;
    }

    public void setFundingRoundName(String fundingRoundName) {
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

    public String getTwitterUrl() {
        return twitterUrl;
    }

    public void setTwitterUrl(String twitterUrl) {
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

    public Approved getApproved() {
        return approved;
    }

    public void setApproved(Approved approved) {
        this.approved = approved;
    }

    public String getReviewed() {
        return reviewed;
    }

    public void setReviewed(String reviewed) {
        this.reviewed = reviewed;
    }


}
