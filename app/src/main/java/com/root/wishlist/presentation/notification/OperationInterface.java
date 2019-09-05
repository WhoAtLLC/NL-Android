package com.root.wishlist.presentation.notification;


import java.util.HashMap;

public interface OperationInterface {
    void getArchiveId(int userId, HashMap<String, Object> archive);

    void getDeleteId(int userId, HashMap<String, Object> delete);

    void getAcceptID(int userId, HashMap<String, Object> accept);
}
