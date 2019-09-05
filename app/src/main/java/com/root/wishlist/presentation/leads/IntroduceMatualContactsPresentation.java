package com.root.wishlist.presentation.leads;


import android.content.Context;

import com.root.wishlist.database.SharedDatabase;
import com.root.wishlist.model.leads.GetIntroduceMutualContacts;
import com.root.wishlist.pojo.leads.getintroduce.IntroResult;
import com.root.wishlist.pojo.wantto.WnatoResult;
import com.root.wishlist.view.IntroduceMutualContactsView;

import java.util.List;

public class IntroduceMatualContactsPresentation implements IntroduceMutualContacts, GetIntroduceMutualContacts.OnSetMatualContacts {

    SharedDatabase sharedDatabase;
    Context context;
    GetIntroduceMutualContacts getIntroduceMutualContacts;
    IntroduceMutualContactsView introduceMutualContactsView;

    public IntroduceMatualContactsPresentation(Context context, IntroduceMutualContactsView introduceMutualContactsView) {
        this.context = context;
        this.introduceMutualContactsView = introduceMutualContactsView;
        sharedDatabase = new SharedDatabase(context);
        getIntroduceMutualContacts = new GetIntroduceMutualContacts();
    }

    @Override
    public void getIntroMutualContacts(String name, int pageno) {

        getIntroduceMutualContacts.getMutualContacts(context, name, pageno, "Token " + sharedDatabase.getToken(), this);

    }

    @Override
    public void notificationMutualContacts(int useID, int pageno) {
        getIntroduceMutualContacts.getNotificationMutualContacts(context, useID, pageno, "token " + sharedDatabase.getToken(), this);
    }

    @Override
    public void setMutualContacts(List<IntroResult> companyList) {

        if (introduceMutualContactsView != null) {
            introduceMutualContactsView.setMutualContact(companyList);
        }
    }

    @Override
    public void setNotificationMutualContacts(List<WnatoResult> companyList) {

        if (introduceMutualContactsView != null) {
            introduceMutualContactsView.getNotificationMutualContact(companyList);
        }
    }

    @Override
    public void pagenumber(String pageNumber) {
        if (introduceMutualContactsView != null) {
            introduceMutualContactsView.getPageNo(pageNumber);
        }
    }

    @Override
    public void newtworkError(String connection) {
        if (introduceMutualContactsView != null) {
            introduceMutualContactsView.networkError(connection);
        }
    }
}
