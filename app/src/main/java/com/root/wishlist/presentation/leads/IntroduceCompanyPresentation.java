package com.root.wishlist.presentation.leads;


import android.content.Context;

import com.root.wishlist.database.SharedDatabase;
import com.root.wishlist.model.leads.GetIntroCompanys;
import com.root.wishlist.pojo.leads.getintroduce.IntroCompanies;
import com.root.wishlist.pojo.wantto.CompaniesofInterest;
import com.root.wishlist.view.IntroduceCompanyView;

import java.util.List;

public class IntroduceCompanyPresentation implements IntroduceCompanyList, GetIntroCompanys.OnIntroCompanyList {

    IntroduceCompanyView introduceCompanyView;
    GetIntroCompanys getIntroCompanys;
    Context context;
    SharedDatabase sharedDatabase;

    public IntroduceCompanyPresentation(Context context, IntroduceCompanyView introduceCompanyView) {
        this.context = context;
        this.introduceCompanyView = introduceCompanyView;
        getIntroCompanys = new GetIntroCompanys();
        sharedDatabase = new SharedDatabase(context);
    }

    @Override
    public void getIntroCompanies(String name, int pageno) {
        getIntroCompanys.getCompanyDetails(context,"Token " + sharedDatabase.getToken(), name, pageno, this);
    }

    @Override
    public void wantToCompany(int userID, int pageno) {
        getIntroCompanys.getWantToCompanyList(context,"Token " + sharedDatabase.getToken(), userID, pageno, this);
    }

    @Override
    public void getAllIntroComapny(List<IntroCompanies> companyResultses) {
        if (introduceCompanyView != null) {
            introduceCompanyView.setCompanies(companyResultses);
        }

    }

    @Override
    public void getWantToCompany(List<CompaniesofInterest> companyResultses) {

        if (introduceCompanyView != null) {
            introduceCompanyView.getWantToCompany(companyResultses);
        }
    }

    @Override
    public void pageNumber(int pNumber) {
        if (introduceCompanyView != null) {
            introduceCompanyView.getPageNumber(pNumber);
        }
    }
}
