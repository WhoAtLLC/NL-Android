package com.root.wishlist.pojo.leads;

import android.os.Environment;

import com.google.gson.annotations.SerializedName;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class MemberResult implements Serializable {
    private static final long serialVersionUID = 1L;
    @SerializedName("id")
    private Integer id;
    @SerializedName("title")
    private String title;
    @SerializedName("level")
    private Integer level;
    @SerializedName("icon")
    private String icon;
    @SerializedName("count")
    private Integer count;

    public MemberResult(Integer id, String title, Integer level, String icon, Integer count) {

        this.count = count;
        this.title = title;
        this.level = level;
        this.icon = icon;
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }



}
