package com.root.wishlist.pojo.leads;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ResultLeads implements Serializable{

    @SerializedName("id")
    private Integer id;
    @SerializedName("handle")
    private String handle;
    @SerializedName("short_bio")
    private String shortBio;
    @SerializedName("avatar")
    private String avatar;

    @SerializedName("leads")
    private Integer leads;
    @SerializedName("mutual")
    private Integer mutual;
    @SerializedName("companies")
    private Integer company;

    public ResultLeads(int id, String handle, String short_bio, String avatar, int leads, int mutual,int company) {
        this.id=id;
        this.handle=handle;
        this.shortBio=short_bio;
        this.avatar=avatar;
        this.leads=leads;
        this.mutual=mutual;
        this.company=company;
    }

    public Integer getCompany() {
        return company;
    }

    public void setCompany(Integer company) {
        this.company = company;
    }
    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getHandle() {
        return handle;
    }

    public void setHandle(String handle) {
        this.handle = handle;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLeads() {
        return leads;
    }

    public void setLeads(Integer leads) {
        this.leads = leads;
    }

    public Integer getMutual() {
        return mutual;
    }

    public void setMutual(Integer mutual) {
        this.mutual = mutual;
    }

    public String getShortBio() {
        return shortBio;
    }

    public void setShortBio(String shortBio) {
        this.shortBio = shortBio;
    }


  /*  public CompanyLeads getCompany() {
        return company;
    }

    public void setCompany(CompanyLeads company) {
        this.company = company;
    }
*/

}
