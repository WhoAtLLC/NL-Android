package com.root.wishlist.presentation.leads;


import android.content.Context;

import com.root.wishlist.database.SharedDatabase;
import com.root.wishlist.model.leads.ResonToDetails;
import com.root.wishlist.view.Reasonview;

import java.util.HashMap;

public class ReasonPresentation implements ReasonInterface, ResonToDetails.OnReasonSend {

    Context context;
    ResonToDetails resonToDetails;
    SharedDatabase sharedDatabase;
    Reasonview reasonview;

    public ReasonPresentation(Context context) {
        this.context = context;
        sharedDatabase = new SharedDatabase(context);
        resonToDetails = new ResonToDetails();
    }

    @Override
    public void sendReason(HashMap<String, Object> stringObjectHashMap) {
        resonToDetails.getReasonMessage(context,"token " + sharedDatabase.getToken(), stringObjectHashMap);

    }

    @Override
    public void returnMessage(String message) {

    }
}
