package com.root.wishlist.pojo.wantto;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Author {
    @SerializedName("id")
    private Integer id;
    @SerializedName("password")
    private String password;
    @SerializedName("last_login")
    private Object lastLogin;
    @SerializedName("is_superuser")
    private Boolean isSuperuser;
    @SerializedName("email")
    private String email;
    @SerializedName("is_staff")
    private Boolean isStaff;
    @SerializedName("is_active")
    private Boolean isActive;
    @SerializedName("date_joined")
    private String dateJoined;
    @SerializedName("status")
    private String status;
    @SerializedName("status_changed")
    private String statusChanged;
    @SerializedName("network_status")
    private String networkStatus;
    @SerializedName("username")
    private String username;
    @SerializedName("first_name")
    private Object firstName;
    @SerializedName("last_name")
    private Object lastName;
    @SerializedName("business_discussion")
    private String businessDiscussion;
    @SerializedName("business_additional")
    private String businessAdditional;
    @SerializedName("groups")
    private List<Object> groups = new ArrayList<Object>();
    @SerializedName("user_permissions")
    private List<Object> userPermissions = new ArrayList<Object>();

    public Integer getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public Object getLastLogin() {
        return lastLogin;
    }

    public Boolean getSuperuser() {
        return isSuperuser;
    }

    public String getEmail() {
        return email;
    }

    public Boolean getStaff() {
        return isStaff;
    }

    public Boolean getActive() {
        return isActive;
    }

    public String getDateJoined() {
        return dateJoined;
    }

    public String getStatus() {
        return status;
    }

    public String getStatusChanged() {
        return statusChanged;
    }

    public String getNetworkStatus() {
        return networkStatus;
    }

    public String getUsername() {
        return username;
    }

    public Object getFirstName() {
        return firstName;
    }

    public Object getLastName() {
        return lastName;
    }

    public String getBusinessDiscussion() {
        return businessDiscussion;
    }

    public String getBusinessAdditional() {
        return businessAdditional;
    }

    public List<Object> getGroups() {
        return groups;
    }

    public List<Object> getUserPermissions() {
        return userPermissions;
    }


}
