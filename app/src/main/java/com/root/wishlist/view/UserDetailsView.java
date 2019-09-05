package com.root.wishlist.view;

/**
 * Created by dal on 3/10/16.
 */
public interface UserDetailsView {
    void setFirstnameError(String fname);

    void setLastnameError(String lname);

    void setNicknameError(String sname);

    void checkNetworkConnection(String connection);

}
