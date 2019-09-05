package com.root.wishlist.presentation.leads;


import android.content.Context;

import com.root.wishlist.database.SharedDatabase;
import com.root.wishlist.model.leads.LeadsYourConnectionAtModel;
import com.root.wishlist.pojo.leads.YourConnectionAtResult;
import com.root.wishlist.view.LeadsYourConnectionView;

import java.util.List;

public class LeasdYourConnectionAtPresentation implements LeadsYourConnectionAtInterface, LeadsYourConnectionAtModel.OnYourConnectionListener {

    Context context;
    LeadsYourConnectionView leadsYourConnectionView;
    LeadsYourConnectionAtModel leadsYourConnectionAtModel;
    SharedDatabase sharedDatabase;

    public LeasdYourConnectionAtPresentation(Context context, LeadsYourConnectionView leadsYourConnectionView) {
        this.context = context;
        this.leadsYourConnectionView = leadsYourConnectionView;
        leadsYourConnectionAtModel = new LeadsYourConnectionAtModel();
        sharedDatabase = new SharedDatabase(context);
    }

    @Override
    public void getLeadsYourConnectionAt(int userId, int pageNumber) {

        leadsYourConnectionAtModel.yourConnectionAt(context, pageNumber, userId, "token " + sharedDatabase.getToken(), this);
    }

    @Override
    public void getConnectionAt(List<YourConnectionAtResult> leadsyourConnectionAt,int totalCount) {

        if (leadsYourConnectionView != null) {
            leadsYourConnectionView.getYourConnection(leadsyourConnectionAt,totalCount);
        }

    }

    @Override
    public void networkMessage() {
        if (leadsYourConnectionView != null)
            leadsYourConnectionView.newtworkConnection("No internet connection");

    }
}
