package com.root.wishlist.adapters;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.root.wishlist.fragment.getinteroduceto.MyIntroMutualContact;
import com.root.wishlist.fragment.getinteroduceto.MyintroCompanies;
import com.root.wishlist.fragment.getinteroduceto.ReasonFragment;

import static android.content.Context.INPUT_METHOD_SERVICE;

public class MyIntroRequestAdapter extends FragmentStatePagerAdapter {

    int tabCount;
    String handle;
    Bundle bundle;
    boolean fill;
    String connectionName;

    public MyIntroRequestAdapter(FragmentManager fm, int tabCount, String handle, String connectionname, boolean fill) {
        super(fm);
        this.tabCount = tabCount;
        this.handle = handle;
        bundle = new Bundle();
        bundle.putString("handle", handle);
        this.fill = fill;
        this.connectionName = connectionname;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                ReasonFragment fragment2 = new ReasonFragment(fill, handle,connectionName);
                return fragment2;
            case 1:
                MyintroCompanies fragment1 = new MyintroCompanies(handle);



                return fragment1;


            case 2:
                MyIntroMutualContact fragment3 = new MyIntroMutualContact();
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


