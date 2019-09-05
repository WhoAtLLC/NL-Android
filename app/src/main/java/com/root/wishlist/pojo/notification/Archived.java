package com.root.wishlist.pojo.notification;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Archived {
    @SerializedName("status")
    private String status;
    @SerializedName("category")
    private String category;
    @SerializedName("highlight")
    private Boolean highlight;
    @SerializedName("recipient_read")
    private Boolean recipientRead;
    @SerializedName("recipient_removed")
    private Boolean recipientRemoved;
    @SerializedName("contactowner")
    private String contactowner;
    @SerializedName("contactowner_lastname")
    private String contactownerLastname;
    @SerializedName("contactowner_firstname")
    private String contactownerFirstname;
    @SerializedName("contactusername")
    private String contactusername;
    @SerializedName("contactdesignation")
    private List<String> contactdesignation = null;
    @SerializedName("contactcompanyname")
    private List<String> contactcompanyname = null;
    @SerializedName("contactshortbio")
    private String contactshortbio;
    @SerializedName("prospectname")
    private String prospectname;
    @SerializedName("prospectdesignation")
    private List<String> prospectdesignation = null;
    @SerializedName("prospectcompanyname")
    private List<String> prospectcompanyname = null;
    @SerializedName("companiesofInterest")
    private List<List<String>> companiesofInterest = null;
    @SerializedName("requestID")
    private Integer requestID;
    @SerializedName("date_created")
    private String dateCreated;

    public Archived(Integer requestID, String status, String contactusername, String prospectname, boolean highlight, String contactowner) {
        this.status = status;
        this.contactusername = contactusername;
        this.prospectname = prospectname;
        this.highlight = highlight;
        this.requestID = requestID;
        this.contactowner = contactowner;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public String getStatus() {
        return status;
    }

    public String getCategory() {
        return category;
    }

    public Boolean getHighlight() {
        return highlight;
    }

    public Boolean getRecipientRead() {
        return recipientRead;
    }

    public Boolean getRecipientRemoved() {
        return recipientRemoved;
    }

    public String getContactowner() {
        return contactowner;
    }

    public String getContactownerLastname() {
        return contactownerLastname;
    }

    public String getContactownerFirstname() {
        return contactownerFirstname;
    }

    public String getContactusername() {
        return contactusername;
    }

    public List<String> getContactdesignation() {
        return contactdesignation;
    }

    public List<String> getContactcompanyname() {
        return contactcompanyname;
    }

    public String getContactshortbio() {
        return contactshortbio;
    }

    public String getProspectname() {
        return prospectname;
    }

    public List<String> getProspectdesignation() {
        return prospectdesignation;
    }

    public List<String> getProspectcompanyname() {
        return prospectcompanyname;
    }

    public List<List<String>> getCompaniesofInterest() {
        return companiesofInterest;
    }

    public Integer getRequestID() {
        return requestID;
    }


}