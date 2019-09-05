package com.root.wishlist.model.registration;

public interface Login {

    interface OnLoginFinishedListener
    {
        void userNameError(String username);
        void userPasswordError(String userpassword);
        void sucess();
    }
    void userlogin(String userName,String userPassword,OnLoginFinishedListener listener);

}
