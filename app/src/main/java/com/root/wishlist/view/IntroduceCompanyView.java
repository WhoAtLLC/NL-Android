package com.root.wishlist.view;


import com.root.wishlist.pojo.leads.getintroduce.IntroCompanies;
import com.root.wishlist.pojo.wantto.CompaniesofInterest;

import java.util.List;

public interface IntroduceCompanyView {

    void setCompanies(List<IntroCompanies> companies);

    void getWantToCompany(List<CompaniesofInterest> companiesofInterests);

    void getPageNumber(int pnumber);
}
