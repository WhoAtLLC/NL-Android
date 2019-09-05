package com.root.wishlist.presentation.registration;


import okhttp3.MultipartBody;

public interface UserDetailsPresentation {
    void userDetails(MultipartBody.Part imageFileBody,String firstName, String lastName, String screenName);
}


