package com.root.wishlist.presentation.leads;

import android.content.Context;

import com.root.wishlist.database.SharedDatabase;
import com.root.wishlist.model.leads.LeadsMeambersModel;
import com.root.wishlist.pojo.leads.ResultLeads;
import com.root.wishlist.view.LeadsMembersView;

import java.util.List;

public class LeadsMembersPresentation implements LeadsMemberPresentarInt, LeadsMeambersModel.OnLoadmembersList {

    Context context;
    LeadsMembersView leadsMembersView;
    LeadsMeambersModel leadsMeambersModel;
    SharedDatabase sharedDatabase;

    public LeadsMembersPresentation(Context context, LeadsMembersView leadsMembersView) {
        this.context = context;
        this.leadsMembersView = leadsMembersView;
        leadsMeambersModel = new LeadsMeambersModel();
        sharedDatabase = new SharedDatabase(context);
    }

    @Override
    public void loadMembersList(Integer integer) {
        if (leadsMembersView != null) {

        }

        leadsMeambersModel.companyList(context,integer, "Token " + sharedDatabase.getToken(), this);

    }

    @Override
    public void getCompanyList(List<ResultLeads> leadsCompanyLists) {
        if (leadsMembersView != null) {
            leadsMembersView.getMembersList(leadsCompanyLists);
        }

    }
}
