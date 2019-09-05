package com.root.wishlist.pojo.wantto;


import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Recipient {

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

    public Recipient() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Object getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Object lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Boolean getSuperuser() {
        return isSuperuser;
    }

    public void setSuperuser(Boolean superuser) {
        isSuperuser = superuser;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getStaff() {
        return isStaff;
    }

    public void setStaff(Boolean staff) {
        isStaff = staff;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public String getDateJoined() {
        return dateJoined;
    }

    public void setDateJoined(String dateJoined) {
        this.dateJoined = dateJoined;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusChanged() {
        return statusChanged;
    }

    public void setStatusChanged(String statusChanged) {
        this.statusChanged = statusChanged;
    }

    public String getNetworkStatus() {
        return networkStatus;
    }

    public void setNetworkStatus(String networkStatus) {
        this.networkStatus = networkStatus;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Object getFirstName() {
        return firstName;
    }

    public void setFirstName(Object firstName) {
        this.firstName = firstName;
    }

    public Object getLastName() {
        return lastName;
    }

    public void setLastName(Object lastName) {
        this.lastName = lastName;
    }

    public String getBusinessDiscussion() {
        return businessDiscussion;
    }

    public void setBusinessDiscussion(String businessDiscussion) {
        this.businessDiscussion = businessDiscussion;
    }

    public String getBusinessAdditional() {
        return businessAdditional;
    }

    public void setBusinessAdditional(String businessAdditional) {
        this.businessAdditional = businessAdditional;
    }

    public List<Object> getGroups() {
        return groups;
    }

    public void setGroups(List<Object> groups) {
        this.groups = groups;
    }

    public List<Object> getUserPermissions() {
        return userPermissions;
    }

    public void setUserPermissions(List<Object> userPermissions) {
        this.userPermissions = userPermissions;
    }


}
