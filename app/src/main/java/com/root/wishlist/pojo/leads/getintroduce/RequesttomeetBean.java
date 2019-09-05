package com.root.wishlist.pojo.leads.getintroduce;


import com.google.gson.annotations.SerializedName;
import com.root.wishlist.pojo.members.Notifications;

import java.util.ArrayList;
import java.util.List;

public class RequesttomeetBean {

    @SerializedName("requestID")
    private Integer requestID;
    @SerializedName("status")
    private String status;
    @SerializedName("category")
    private String category;
    @SerializedName("author")
    private Integer author;
    @SerializedName("whyIntroReason")
    private String whyIntroReason;
    @SerializedName("howIntroReason")
    private String howIntroReason;
    @SerializedName("companiesofInterest")
    private List<Integer> companiesofInterest = new ArrayList<Integer>();
    @SerializedName("companiesOffered")
    private List<Object> companiesOffered = new ArrayList<Object>();
    @SerializedName("contact")
    private Integer contact;
    @SerializedName("recipient")
    private Integer recipient;
    @SerializedName("comments")
    private List<Object> comments = new ArrayList<Object>();
    @SerializedName("notifications")
    private Notifications notifications;

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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getAuthor() {
        return author;
    }

    public void setAuthor(Integer author) {
        this.author = author;
    }

    public String getWhyIntroReason() {
        return whyIntroReason;
    }

    public void setWhyIntroReason(String whyIntroReason) {
        this.whyIntroReason = whyIntroReason;
    }

    public String getHowIntroReason() {
        return howIntroReason;
    }

    public void setHowIntroReason(String howIntroReason) {
        this.howIntroReason = howIntroReason;
    }

    public List<Integer> getCompaniesofInterest() {
        return companiesofInterest;
    }

    public void setCompaniesofInterest(List<Integer> companiesofInterest) {
        this.companiesofInterest = companiesofInterest;
    }

    public List<Object> getCompaniesOffered() {
        return companiesOffered;
    }

    public void setCompaniesOffered(List<Object> companiesOffered) {
        this.companiesOffered = companiesOffered;
    }

    public Integer getContact() {
        return contact;
    }

    public void setContact(Integer contact) {
        this.contact = contact;
    }

    public Integer getRecipient() {
        return recipient;
    }

    public void setRecipient(Integer recipient) {
        this.recipient = recipient;
    }

    public List<Object> getComments() {
        return comments;
    }

    public void setComments(List<Object> comments) {
        this.comments = comments;
    }

    public Notifications getNotifications() {
        return notifications;
    }

    public void setNotifications(Notifications notifications) {
        this.notifications = notifications;
    }


}
