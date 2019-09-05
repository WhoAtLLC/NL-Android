package com.root.wishlist.pojo.registration;


import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Loginbean {

    @SerializedName("email")
    private String email;
    @SerializedName("password")
    private String password;
    @SerializedName("token")
    private String token;
    @SerializedName("step")
    private String step;
    @SerializedName("non_field_errors")
    private List<String> nonFieldErrors = new ArrayList<String>();

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<String> getNonFieldErrors() {
        return nonFieldErrors;
    }

    public void setNonFieldErrors(List<String> nonFieldErrors) {
        this.nonFieldErrors = nonFieldErrors;
    }

    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
