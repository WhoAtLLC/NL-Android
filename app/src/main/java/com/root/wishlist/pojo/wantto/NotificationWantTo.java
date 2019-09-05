package com.root.wishlist.pojo.wantto;


import com.google.gson.annotations.SerializedName;
import com.root.wishlist.pojo.mywishlist.Notifications;

import java.util.ArrayList;
import java.util.List;

public class NotificationWantTo {

    @SerializedName("status")
    private String status;
    @SerializedName("category")
    private String category;
    @SerializedName("author")
    private Author author;
    @SerializedName("archived")
    private Boolean archived;
    @SerializedName("recipient")
    private Recipient recipient;
    @SerializedName("id")
    private Integer id;
    @SerializedName("deleted_recipient")
    private Boolean deletedRecipient;
    @SerializedName("contact")
    private String contact;
    @SerializedName("whyIntroReason")
    private String whyIntroReason;
    @SerializedName("howIntroReason")
    private String howIntroReason;
    @SerializedName("companiesofInterest")
    private List<CompaniesofInterest> companiesofInterest = new ArrayList<CompaniesofInterest>();
    @SerializedName("companiesOffered")
    private List<Object> companiesOffered = new ArrayList<Object>();
    @SerializedName("comments")
    private List<Comment> comments = new ArrayList<Comment>();
    @SerializedName("notifications")
    private Notifications notifications;

    public NotificationWantTo(String status, String whyIntroReason, String howIntroReason, List<Comment> comments) {
        this.howIntroReason = howIntroReason;
        this.whyIntroReason = whyIntroReason;
        this.comments = comments;
        this.status = status;
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

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Boolean getArchived() {
        return archived;
    }

    public void setArchived(Boolean archived) {
        this.archived = archived;
    }

    public Recipient getRecipient() {
        return recipient;
    }

    public void setRecipient(Recipient recipient) {
        this.recipient = recipient;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getDeletedRecipient() {
        return deletedRecipient;
    }

    public void setDeletedRecipient(Boolean deletedRecipient) {
        this.deletedRecipient = deletedRecipient;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
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

    public List<CompaniesofInterest> getCompaniesofInterest() {
        return companiesofInterest;
    }

    public void setCompaniesofInterest(List<CompaniesofInterest> companiesofInterest) {
        this.companiesofInterest = companiesofInterest;
    }

    public List<Object> getCompaniesOffered() {
        return companiesOffered;
    }

    public void setCompaniesOffered(List<Object> companiesOffered) {
        this.companiesOffered = companiesOffered;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public Notifications getNotifications() {
        return notifications;
    }

    public void setNotifications(Notifications notifications) {
        this.notifications = notifications;
    }


}
