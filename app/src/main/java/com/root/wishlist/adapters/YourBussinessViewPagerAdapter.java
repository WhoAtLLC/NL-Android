package com.root.wishlist.adapters;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.root.wishlist.fragment.CompaniesInterest;
import com.root.wishlist.fragment.MyBusiness;
import com.root.wishlist.fragment.TotalSelectedCompanies;

public class YourBussinessViewPagerAdapter extends FragmentStatePagerAdapter {

    int tabCount;


    public YourBussinessViewPagerAdapter(FragmentManager fm, int tabCount) {
        super(fm);
        this.tabCount = tabCount;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                MyBusiness myBusiness = new MyBusiness();
                return myBusiness;
            case 1:
                CompaniesInterest companiesInterest = new CompaniesInterest();
                return companiesInterest;
            case 2:
                TotalSelectedCompanies totalSelectedCompanies = new TotalSelectedCompanies();
                return totalSelectedCompanies;

            default:
                return null;
        }

    }


    @Override
    public int getCount() {
        return tabCount;
    }


}
