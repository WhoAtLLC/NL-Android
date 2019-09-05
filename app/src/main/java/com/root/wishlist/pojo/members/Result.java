package com.root.wishlist.pojo.members;

import com.google.gson.annotations.SerializedName;


public class Result {
    @SerializedName("id")
    private Integer id;
    @SerializedName("title")
    private String title;
    @SerializedName("leads")
    private Integer leads;
    @SerializedName("mutual")
    private Integer mutual;

    public Result(String title, int id, int leads, int mutual){
        this.title = title;
        this.id = id;
        this.leads=leads;
        this.mutual=mutual;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


}
