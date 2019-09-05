package com.root.wishlist.pojo.search;


import com.google.gson.annotations.SerializedName;

public class SearchResult {

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

    public SearchResult(int id, String title, int level, String icon) {

        this.icon = icon;
        this.id = id;
        this.title = title;
        this.level = level;
    }

    public SearchResult(int id, String title, String icon) {
        this.id = id;
        this.title = title;
        this.icon = icon;
    }

    public Integer getCount() {
        return count;
    }

    public String getIcon() {
        return icon;
    }

    public Integer getLevel() {
        return level;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }


}
