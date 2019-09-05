package com.root.wishlist.presentation.notification;


import android.content.Context;

import com.root.wishlist.database.SharedDatabase;
import com.root.wishlist.model.notification.OperationPerformed;
import com.root.wishlist.view.operationView;

import java.util.HashMap;

public class OperationPresentation implements OperationInterface, OperationPerformed.OnPerformedOperation {
    OperationPerformed operationPerformed;
    SharedDatabase sharedDatabase;
    Context context;
    operationView notificationIOAView;

    public OperationPresentation(Context context, operationView notificationIOAView) {
        this.context = context;
        this.notificationIOAView = notificationIOAView;
        sharedDatabase = new SharedDatabase(context);
        operationPerformed = new OperationPerformed();
    }

    @Override
    public void getArchiveId(int userId, HashMap<String, Object> archive) {

        operationPerformed.onArchive(context,"token " + sharedDatabase.getToken(), userId, archive, this);
    }

    @Override
    public void getDeleteId(int userId, HashMap<String, Object> delete) {
        operationPerformed.onDelete(context,"token " + sharedDatabase.getToken(), userId, delete, this);
    }

    @Override
    public void getAcceptID(int userId, HashMap<String, Object> accept) {
        operationPerformed.onAccept(context,"token " + sharedDatabase.getToken(), userId, accept, this);
    }


    @Override
    public void getarchived(boolean archive) {
        if (notificationIOAView != null) {
            notificationIOAView.getarchived(archive);
        }
    }

    @Override
    public void getdecline(boolean decline) {
        if (notificationIOAView != null) {
            notificationIOAView.getdecline(decline);
        }
    }
    @Override
    public void getStatus(String status) {
        if (notificationIOAView != null) {
            notificationIOAView.getStatus(status);
        }
    }
}
