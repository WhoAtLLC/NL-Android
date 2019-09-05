package com.root.wishlist.view;

import com.root.wishlist.pojo.mywishlist.Result;

import java.util.List;

public interface CompanyofInterestView {
    void getCompanyList(List<Result> resultLeadses);
    void getnext(int next);
    void newtWorkconnection(String connection) throws IllegalAccessException, ClassNotFoundException, InstantiationException;
}
