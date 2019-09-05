package com.root.wishlist.adapters;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.inputmethod.InputMethodManager;

import com.root.wishlist.fragment.getinteroduceto.GetCompanies;
import com.root.wishlist.fragment.getinteroduceto.GetMatualContacts;
import com.root.wishlist.fragment.getinteroduceto.TheirInterest;

import static android.content.Context.INPUT_METHOD_SERVICE;

public class GetIntroduceToTabAdapter extends FragmentStatePagerAdapter {

    int tabCount;
    String handle;
    Bundle bundle;

    public GetIntroduceToTabAdapter(FragmentManager fm, int tabCount, String handle) {
        super(fm);
        this.tabCount = tabCount;
        this.handle = handle;
        bundle = new Bundle();
        bundle.putString("handle", handle);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:

                TheirInterest fragment2 = new TheirInterest();
                fragment2.setArguments(bundle);
                return fragment2;

            case 1:
                GetCompanies fragment1 = new GetCompanies();
                fragment1.setArguments(bundle);

                return fragment1;

            case 2:

                GetMatualContacts fragment3 = new GetMatualContacts(0);
                fragment3.setArguments(bundle);
                return fragment3;


            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
