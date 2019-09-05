package com.root.wishlist.view;


import com.root.wishlist.pojo.leads.getintroduce.IntroResult;
import com.root.wishlist.pojo.wantto.WnatoResult;

import java.util.List;

public interface IntroduceMutualContactsView {

    void setMutualContact(List<IntroResult> mutualContact);

    void getNotificationMutualContact(List<WnatoResult> mutualContact);

    void getPageNo(String pageNumber);

    void networkError(String connection);
}
