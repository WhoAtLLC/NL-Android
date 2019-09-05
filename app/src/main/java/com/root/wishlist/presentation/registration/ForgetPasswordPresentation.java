package com.root.wishlist.presentation.registration;

import android.content.Context;

import com.root.wishlist.model.registration.ForgetPassword;
import com.root.wishlist.model.registration.ForgetPasswordModel;
import com.root.wishlist.view.ForgetPasswordView;
import com.root.wishlist.model.registration.ForgetPasswordProcess;



public class ForgetPasswordPresentation implements Forgetpasswordint, ForgetPassword.setOnForgetPasswordListener {

    Context context;
    ForgetPasswordView forgetPasswordView;
    ForgetPasswordModel forgetPasswordModel;
    ForgetPasswordProcess forgetPasswordProcess;

    String email;

    public ForgetPasswordPresentation(ForgetPasswordView forgetPasswordView, Context context) {
        this.context = context;
        this.forgetPasswordView = forgetPasswordView;
        forgetPasswordProcess = new ForgetPasswordProcess(context);
        forgetPasswordModel=new ForgetPasswordModel();
    }


    @Override
    public void userEmailError(String useremail) {

        if (forgetPasswordView != null) {
            forgetPasswordView.setEmailError(useremail);
        }
    }

    @Override
    public void sucess() {
        if (forgetPasswordView != null) {
            forgetPasswordProcess.getForgetPassword(email);
        }

    }

    @Override
    public void validateEmail(String email) {

        if(forgetPasswordView!=null)
        {

        }
        this.email=email;
        forgetPasswordModel.forgetPassword(email,this);
    }
}
