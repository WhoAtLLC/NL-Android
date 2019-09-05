package com.root.wishlist.presentation.registration;

import android.content.Context;

import com.root.wishlist.database.SharedDatabase;
import com.root.wishlist.model.registration.EmailVerificationStatusModel;
import com.root.wishlist.view.EmailverificationView;

public class EmailStatuspresentation implements EmailStatusInterface, EmailVerificationStatusModel.OnCheckedStatus {

    EmailverificationView emailverificationView;
    Context context;
    SharedDatabase sharedDatabase;
    EmailVerificationStatusModel emailVerificationStatusModel;

    public EmailStatuspresentation(EmailverificationView emailverificationView, Context context) {
        this.context = context;
        this.emailverificationView = emailverificationView;
        sharedDatabase = new SharedDatabase(context);
        emailVerificationStatusModel = new EmailVerificationStatusModel();
    }

    @Override
    public void setStatus() {
        emailVerificationStatusModel.getEmailStatus(context,"token " + sharedDatabase.getToken(), this);

    }

    @Override
    public void reSendEmail() {
        emailVerificationStatusModel.reEmail(context,"token " + sharedDatabase.getToken(), this);

    }

    @Override
    public void sendStatus(boolean status) {
        if (emailverificationView != null) {
            emailverificationView.getStatus(status);
        }
    }

    @Override
    public void resendEmail(boolean status) {
        if (emailverificationView != null) {
            emailverificationView.reSendEmail(status);
        }
    }
}
