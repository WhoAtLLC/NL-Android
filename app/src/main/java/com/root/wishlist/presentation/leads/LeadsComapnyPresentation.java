package com.root.wishlist.presentation.leads;

import android.content.Context;

import com.root.wishlist.database.SharedDatabase;
import com.root.wishlist.model.leads.LeadsCompanyIListModel;
import com.root.wishlist.pojo.leads.MemberResult;
import com.root.wishlist.view.LeadsCompanyListView;

import java.util.List;


public class LeadsComapnyPresentation implements LeadsCompanyListPresentationInt, LeadsCompanyIListModel.OnLoadCompanyList {

    LeadsCompanyListView leadsCompanyListView;
    LeadsCompanyIListModel leadsCompanyIListInterface;
    Context context;
    SharedDatabase sharedDatabase;


    public LeadsComapnyPresentation(LeadsCompanyListView leadsCompanyListView, Context context) {
        this.context = context;
        this.leadsCompanyListView = leadsCompanyListView;
        leadsCompanyIListInterface = new LeadsCompanyIListModel();
        sharedDatabase = new SharedDatabase(context);

    }

    @Override
    public void loadComapnyList(Integer integer) {

        leadsCompanyIListInterface.companyList(integer, "token " + sharedDatabase.getToken(), this, context);

    }

    @Override
    public void getCompanyList(List<MemberResult> leadsCompanyLists, int next) {
        if (leadsCompanyLists != null) {
            leadsCompanyListView.getCompanyList(leadsCompanyLists);
            leadsCompanyListView.getnext(next);

        }
    }

    @Override
    public void newtWorkMessage(String message) {
        leadsCompanyListView.networkMessage(message);
    }

}
