package com.root.wishlist.view;


public interface operationView {
    void getarchived(boolean archive);

    void getdecline(boolean decline);

    void getStatus(String status);

    void newtworkError(String connection);
}
