package com.root.wishlist.pojo.leads;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class YourConnectionAtResult {

    @SerializedName("wishlistmember")
    private Boolean wishlistmember;
    @SerializedName("connectioncount")
    private Integer connectioncount;
    @SerializedName("title")
    private String title;
    @SerializedName("connectionpossibleto")
    private List<YourConnectionpossibleto> connectionpossibleto = new ArrayList<YourConnectionpossibleto>();
    @SerializedName("connectionname")
    private String connectionname;
    @SerializedName("highlight")
    private Boolean highlight;
    @SerializedName("id")
    private Integer id;


    public YourConnectionAtResult(String connectionname, String title, int connectioncount, List<YourConnectionpossibleto> connectionpossibleto) {
        this.connectionname = connectionname;
        this.title = title;
        this.connectioncount = connectioncount;
        this.connectionpossibleto = connectionpossibleto;
    }

    public YourConnectionAtResult(String connectionname, String title, int connectioncount) {
        this.connectionname = connectionname;
        this.title = title;
        this.connectioncount = connectioncount;
    }

    public Boolean getWishlistmember() {
        return wishlistmember;
    }

    public void setWishlistmember(Boolean wishlistmember) {
        this.wishlistmember = wishlistmember;
    }

    public Integer getConnectioncount() {
        return connectioncount;
    }

    public void setConnectioncount(Integer connectioncount) {
        this.connectioncount = connectioncount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<YourConnectionpossibleto> getConnectionpossibleto() {
        return connectionpossibleto;
    }

    public void setConnectionpossibleto(List<YourConnectionpossibleto> connectionpossibleto) {
        this.connectionpossibleto = connectionpossibleto;
    }

    public String getConnectionname() {
        return connectionname;
    }

    public void setConnectionname(String connectionname) {
        this.connectionname = connectionname;
    }

    public Boolean getHighlight() {
        return highlight;
    }

    public void setHighlight(Boolean highlight) {
        this.highlight = highlight;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


}
