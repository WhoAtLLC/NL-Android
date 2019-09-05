package com.root.wishlist.pojo.notification;


import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Inbound {

    @SerializedName("status")
    private String status;
    @SerializedName("category")
    private String category;
    @SerializedName("highlight")
    private Boolean highlight;
    @SerializedName("requestorname")
    private String requestorname;
    @SerializedName("requestor_firstname")
    private String requestorFirstname;
    @SerializedName("requestor_lastname")
    private String requestorLastname;
    @SerializedName("requestorusername")
    private String requestorusername;
    @SerializedName("requestorimage")
    private String requestorimage;
    @SerializedName("requestordesignation")
    private List<String> requestordesignation = new ArrayList<String>();
    @SerializedName("requestorcompanyname")
    private List<String> requestorcompanyname = new ArrayList<String>();
    @SerializedName("requestor_removed")
    private Boolean requestorRemoved;
    @SerializedName("prospectname")
    private String prospectname;
    @SerializedName("prospectdesignation")
    private List<String> prospectdesignation = new ArrayList<String>();
    @SerializedName("companiesofInterest")
    private List<List<String>> companiesofInterest = new ArrayList<List<String>>();
/*    @SerializedName("companiesOffered")
    private List<String> companiesOffered = new ArrayList<String>();*/
    @SerializedName("prospectcompanyname")
    private String prospectcompanyname;
    @SerializedName("requestID")
    private Integer requestID;
    @SerializedName("date_created")
    private String dateCreated;
    public String mProspectdesignation;
    public String mRequestordesignation;
    public String mRequestorcompanyname;

    public Inbound(String requestorusername, Integer requestID, String status, String requestorname, String prospectname, String prospectdesignation, String requestordesignation, String prospectcompanyname, String requestorcompanyname, String requestorimage, boolean highlight) {
        this.status = status;
        this.requestorname = requestorname;
        this.prospectname = prospectname;
        this.mProspectdesignation = prospectdesignation;
        this.mRequestordesignation = requestordesignation;
        this.prospectcompanyname = prospectcompanyname;
        this.mRequestorcompanyname = requestorcompanyname;
        this.requestorimage = requestorimage;
        this.highlight = highlight;
        this.requestID = requestID;
        this.requestorusername = requestorusername;
    }


    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }


    public List<List<String>> getCompaniesofInterest() {
        return companiesofInterest;
    }

    public void setCompaniesofInterest(List<List<String>> companiesofInterest) {
        this.companiesofInterest = companiesofInterest;
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

    public String getProspectcompanyname() {
        return prospectcompanyname;
    }

    public void setProspectcompanyname(String prospectcompanyname) {
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

    public Integer getRequestID() {
        return requestID;
    }

    public void setRequestID(Integer requestID) {
        this.requestID = requestID;
    }

    public List<String> getRequestorcompanyname() {
        return requestorcompanyname;
    }

    public void setRequestorcompanyname(List<String> requestorcompanyname) {
        this.requestorcompanyname = requestorcompanyname;
    }

    public List<String> getRequestordesignation() {
        return requestordesignation;
    }

    public void setRequestordesignation(List<String> requestordesignation) {
        this.requestordesignation = requestordesignation;
    }

    public String getRequestorFirstname() {
        return requestorFirstname;
    }

    public void setRequestorFirstname(String requestorFirstname) {
        this.requestorFirstname = requestorFirstname;
    }

    public String getRequestorimage() {
        return requestorimage;
    }

    public void setRequestorimage(String requestorimage) {
        this.requestorimage = requestorimage;
    }

    public String getRequestorLastname() {
        return requestorLastname;
    }

    public void setRequestorLastname(String requestorLastname) {
        this.requestorLastname = requestorLastname;
    }

    public String getRequestorname() {
        return requestorname;
    }

    public void setRequestorname(String requestorname) {
        this.requestorname = requestorname;
    }

    public Boolean getRequestorRemoved() {
        return requestorRemoved;
    }

    public void setRequestorRemoved(Boolean requestorRemoved) {
        this.requestorRemoved = requestorRemoved;
    }

    public String getRequestorusername() {
        return requestorusername;
    }

    public void setRequestorusername(String requestorusername) {
        this.requestorusername = requestorusername;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}
