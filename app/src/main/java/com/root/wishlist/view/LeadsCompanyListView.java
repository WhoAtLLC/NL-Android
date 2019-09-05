package com.root.wishlist.view;

import com.root.wishlist.pojo.leads.MemberResult;

import java.util.List;


public interface LeadsCompanyListView {

    void getCompanyList(List<MemberResult> resultLeadses);

    void getnext(int next);

    void networkMessage(String connection);
}
