package com.root.wishlist.view;


public interface UserInformationView {
    void setUserInformation(String handle, String shortBio, String bio, String image, String businessDiscussion, String businessAdditional);

    void getUserProfile(String title, String firstName, String lastName, String handle,
                        String email, String shortBio, String phone, String image, String businessDiscussion, String businessAdditional, String bio);
}
