package com.root.wishlist.pojo;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UploadContactBean {


    @SerializedName("company")
    private String company;
    @SerializedName("creationDate")
    private String creationDate;
    @SerializedName("emails")
    private List emails;
    @SerializedName("firstName")
    private String firstName;
    @SerializedName("jobtitle")
    private String jobtitle;
    @SerializedName("key")
    private int key;
    @SerializedName("label")
    private String label;
    @SerializedName("lastName")
    private String lastName;
    @SerializedName("middleName")
    private String middleName;
    @SerializedName("modificationDate")
    private String modificationDate;
    @SerializedName("nickname")
    private String nickname;
    @SerializedName("phones")
    private List phones;

    public UploadContactBean(String company, String creationDate, List emails, String jobtitle, String firstName, int key, String label, String lastName, String middleName, String modificationDate, String nickname, List phones) {
        this.company = company;
        this.creationDate = creationDate;
        this.emails = emails;
        this.jobtitle = jobtitle;
        this.firstName = firstName;
        this.key = key;
        this.label = label;
        this.lastName = lastName;
        this.middleName = middleName;
        this.modificationDate = modificationDate;
        this.nickname = nickname;
        this.phones = phones;

    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public List getEmails() {
        return emails;
    }

    public void setEmails(List emails) {
        this.emails = emails;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getJobtitle() {
        return jobtitle;
    }

    public void setJobtitle(String jobtitle) {
        this.jobtitle = jobtitle;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(String modificationDate) {
        this.modificationDate = modificationDate;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public List getPhones() {
        return phones;
    }

    public void setPhones(List phones) {
        this.phones = phones;
    }

}
