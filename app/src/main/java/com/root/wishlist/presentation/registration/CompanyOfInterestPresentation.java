package com.root.wishlist.presentation.registration;

import android.content.Context;

import com.root.wishlist.database.SharedDatabase;
import com.root.wishlist.util.globalValues.NetworkConnection;
import com.root.wishlist.pojo.mywishlist.Result;
import com.root.wishlist.view.CompanyofInterestView;

import java.util.List;


public class CompanyOfInterestPresentation implements CompanyOfInterestInterface, CompanyOfInterestClass.OnLoadCompanyList {

    Context context;
    CompanyofInterestView companyofInterestView;
    SharedDatabase sharedDatabase;
    CompanyOfInterestClass companyOfInterestClass;

    public CompanyOfInterestPresentation(CompanyofInterestView companyofInterestView, Context context) {
        this.context = context;
        this.companyofInterestView = companyofInterestView;
        sharedDatabase = new SharedDatabase(context);
        companyOfInterestClass = new CompanyOfInterestClass();
    }

    @Override
    public void loadComapnyList(Integer integer) {
        try {
            if (NetworkConnection.isNetworkAvailable(context)) {
                companyOfInterestClass.companyList(context,integer, "Token " + sharedDatabase.getToken(), this);
            } else {
                companyofInterestView.newtWorkconnection("No internet connection");
            }
        } catch (Exception e) {
            try {
                companyofInterestView.newtWorkconnection("No internet connection");
            } catch (IllegalAccessException e1) {
                e1.printStackTrace();
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            } catch (InstantiationException e1) {
                e1.printStackTrace();
            }
        }
    }

    @Override
    public void getCompanyList(List<Result> companyLists, int next) {
        if (companyofInterestView != null) {
            companyofInterestView.getCompanyList(companyLists);
            companyofInterestView.getnext(next);

        }
    }
}
