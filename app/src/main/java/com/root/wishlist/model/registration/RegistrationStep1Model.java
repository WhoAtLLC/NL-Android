package com.root.wishlist.model.registration;

public interface RegistrationStep1Model {

    interface OnRegistrationFinishedListener
    {
        void userNameError(String username);
        void userPasswordError(String userpassword);
        void sucess();
    }
    void userlogin(String userName,String userPassword,OnRegistrationFinishedListener listener);

}
