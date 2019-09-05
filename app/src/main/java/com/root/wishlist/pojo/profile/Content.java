package com.root.wishlist.pojo.profile;

import com.google.gson.annotations.SerializedName;


public class Content {
    @SerializedName("id")
    private Integer id;
    @SerializedName("slug")
    private String slug;
    @SerializedName("date_created")
    private String dateCreated;
    @SerializedName("date_changed")
    private String dateChanged;
    @SerializedName("graph_id")
    private Object graphId;
    @SerializedName("label")
    private String label;
    @SerializedName("content")
    private String content;
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDateChanged() {
        return dateChanged;
    }

    public void setDateChanged(String dateChanged) {
        this.dateChanged = dateChanged;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Object getGraphId() {
        return graphId;
    }

    public void setGraphId(Object graphId) {
        this.graphId = graphId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }



}
