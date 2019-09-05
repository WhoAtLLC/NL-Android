package com.root.wishlist.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.root.wishlist.fragment.LeadsCompaniesFragment;
import com.root.wishlist.fragment.MembersFragment;


public class DashBoardViewPagerAdapter extends FragmentStatePagerAdapter {
    int tabCount;

    public DashBoardViewPagerAdapter(FragmentManager fm, int tabCount) {
        super(fm);
        this.tabCount = tabCount;
    }

    @Override
    public Fragment getItem(int tabCount) {
        switch (tabCount) {
            case 0:
                LeadsCompaniesFragment fragment2 = new LeadsCompaniesFragment();
                return fragment2;
            case 1:
                MembersFragment fragment1 = new MembersFragment();
                return fragment1;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}