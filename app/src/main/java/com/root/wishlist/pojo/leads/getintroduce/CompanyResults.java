package com.root.wishlist.pojo.leads.getintroduce;

import com.google.gson.annotations.SerializedName;


public class CompanyResults {

    @SerializedName("company")
    private IntroCompanies company;

    public IntroCompanies getCompany() {
        return company;
    }

    public void setCompany(IntroCompanies company) {
        this.company = company;
    }
}
