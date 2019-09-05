package com.root.wishlist.pojo.notification;


import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Outbound {
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
    private List<String> contactdesignation = new ArrayList<String>();
    @SerializedName("contactcompanyname")
    private List<String> contactcompanyname = new ArrayList<String>();
    @SerializedName("contactshortbio")
    private String contactshortbio;
    @SerializedName("prospectname")
    private String prospectname;
    @SerializedName("prospectdesignation")
    private List<String> prospectdesignation = new ArrayList<String>();
    @SerializedName("prospectcompanyname")
    private List<String> prospectcompanyname = new ArrayList<String>();
    @SerializedName("companiesofInterest")
    private List<List<String>> companiesofInterest = new ArrayList<List<String>>();
    @SerializedName("companiesOffered")
    private List<Object> companiesOffered = new ArrayList<Object>();
    @SerializedName("requestID")
    private Integer requestID;

    public String mProspectdesignation;
    public String mRequestordesignation;
    public String mRequestorcompanyname;
    public String mProspectcompanyname;

    public Outbound(Integer requestID, String status, String requestorname, String prospectname, String prospectdesignation, String requestordesignation, String prospectcompanyname, String requestorcompanyname, boolean recipientRead, String contactshortbio, String contactowner) {
        this.status = status;
        this.contactusername = requestorname;
        this.prospectname = prospectname;
        this.mProspectdesignation = prospectdesignation;
        this.mRequestordesignation = requestordesignation;
        this.mProspectcompanyname = prospectcompanyname;
        this.mRequestorcompanyname = requestorcompanyname;
        this.recipientRead = recipientRead;
        this.requestID = requestID;
        this.contactshortbio = contactshortbio;
        this.contactowner = contactowner;

    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<Object> getCompaniesOffered() {
        return companiesOffered;
    }

    public void setCompaniesOffered(List<Object> companiesOffered) {
        this.companiesOffered = companiesOffered;
    }

    public List<List<String>> getCompaniesofInterest() {
        return companiesofInterest;
    }

    public void setCompaniesofInterest(List<List<String>> companiesofInterest) {
        this.companiesofInterest = companiesofInterest;
    }

    public List<String> getContactcompanyname() {
        return contactcompanyname;
    }

    public void setContactcompanyname(List<String> contactcompanyname) {
        this.contactcompanyname = contactcompanyname;
    }

    public List<String> getContactdesignation() {
        return contactdesignation;
    }

    public void setContactdesignation(List<String> contactdesignation) {
        this.contactdesignation = contactdesignation;
    }

    public String getContactowner() {
        return contactowner;
    }

    public void setContactowner(String contactowner) {
        this.contactowner = contactowner;
    }

    public String getContactownerFirstname() {
        return contactownerFirstname;
    }

    public void setContactownerFirstname(String contactownerFirstname) {
        this.contactownerFirstname = contactownerFirstname;
    }

    public String getContactownerLastname() {
        return contactownerLastname;
    }

    public void setContactownerLastname(String contactownerLastname) {
        this.contactownerLastname = contactownerLastname;
    }

    public String getContactshortbio() {
        return contactshortbio;
    }

    public void setContactshortbio(String contactshortbio) {
        this.contactshortbio = contactshortbio;
    }

    public String getContactusername() {
        return contactusername;
    }

    public void setContactusername(String contactusername) {
        this.contactusername = contactusername;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Boolean getHighlight() {
        return highlight;
    }

    public void setHighlight(Boolean highlight) {
        this.highlight = highlight;
    }

    public List<String> getProspectcompanyname() {
        return prospectcompanyname;
    }

    public void setProspectcompanyname(List<String> prospectcompanyname) {
        this.prospectcompanyname = prospectcompanyname;
    }

    public List<String> getProspectdesignation() {
        return prospectdesignation;
    }

    public void setProspectdesignation(List<String> prospectdesignation) {
        this.prospectdesignation = prospectdesignation;
    }

    public String getProspectname() {
        return prospectname;
    }

    public void setProspectname(String prospectname) {
        this.prospectname = prospectname;
    }

    public Boolean getRecipientRead() {
        return recipientRead;
    }

    public void setRecipientRead(Boolean recipientRead) {
        this.recipientRead = recipientRead;
    }

    public Boolean getRecipientRemoved() {
        return recipientRemoved;
    }

    public void setRecipientRemoved(Boolean recipientRemoved) {
        this.recipientRemoved = recipientRemoved;
    }

    public Integer getRequestID() {
        return requestID;
    }

    public void setRequestID(Integer requestID) {
        this.requestID = requestID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @SerializedName("date_created")
    private String dateCreated;


}
