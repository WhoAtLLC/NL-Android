package com.root.wishlist.pojo.mywishlist;


/*import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;*/


import com.google.gson.annotations.SerializedName;

/*@JsonPropertyOrder({
        "unread"
})*/
public class Notifications {

    @SerializedName("unread")
    private Integer unread;


    public Integer getUnread() {
        return unread;
    }

    public void setUnread(Integer unread) {
        this.unread = unread;
    }
}
