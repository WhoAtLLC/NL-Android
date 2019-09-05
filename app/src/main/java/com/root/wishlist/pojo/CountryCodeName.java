package com.root.wishlist.pojo;

import java.io.Serializable;

public class CountryCodeName implements Serializable {


    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    String countryName;
    String countryCode;

    public CountryCodeName(String countryName, String countryCode) {
        this.countryCode = countryCode;
        this.countryName = countryName;
    }


}
