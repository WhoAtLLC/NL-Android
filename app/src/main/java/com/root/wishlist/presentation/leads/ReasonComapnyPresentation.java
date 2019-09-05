package com.root.wishlist.presentation.leads;


import android.content.Context;

import com.root.wishlist.database.SharedDatabase;
import com.root.wishlist.model.leads.ReasonCompanyModel;
import com.root.wishlist.view.Reasonview;

public class ReasonComapnyPresentation implements ReasonCompanyInterface, ReasonCompanyModel.OnUpdateReason {

    Reasonview reasonview;
    Context context;
    ReasonCompanyModel reasonCompanyModel;
    SharedDatabase sharedDatabase;

    public ReasonComapnyPresentation(Reasonview reasonview, Context context) {
        this.context = context;
        this.reasonview = reasonview;
        reasonCompanyModel = new ReasonCompanyModel();
        sharedDatabase = new SharedDatabase(context);

    }

    @Override
    public void setReason() {

        reasonCompanyModel.setReason(context, "token " + sharedDatabase.getToken(), this);
    }

    @Override
    public void getReasonUpdate(String handle, String businessDiscussion, String businessAdditional) {

        if (reasonview != null) {
            reasonview.setReasonMessage(handle, businessDiscussion, businessAdditional);
        }
    }

    @Override
    public void networkMessage(String message) {
        reasonview.networkMessage(message);
    }
}
