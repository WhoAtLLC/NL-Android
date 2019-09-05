package com.root.wishlist.view;

import com.root.wishlist.pojo.leads.YourConnectionAtResult;

import java.util.List;

public interface LeadsYourConnectionView {
    void getYourConnection(List<YourConnectionAtResult> resultLeadses, int totalCount);

    void newtworkConnection(String message);
}
