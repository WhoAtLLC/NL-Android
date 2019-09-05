package com.root.wishlist.model.registration;


public interface ForgetPassword {

    interface setOnForgetPasswordListener
    {
        void userEmailError(String useremail);
        void sucess();
    }
    void forgetPassword(String useremail,setOnForgetPasswordListener listener);
}
