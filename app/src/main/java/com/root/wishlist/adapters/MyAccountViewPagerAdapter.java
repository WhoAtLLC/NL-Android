package com.root.wishlist.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.root.wishlist.fragment.PrivateProfileEditFragment;
import com.root.wishlist.fragment.PrivateProfileFragment;
import com.root.wishlist.fragment.PublicProfileEditFragment;
import com.root.wishlist.fragment.PublicProfileFragment;


public class MyAccountViewPagerAdapter extends FragmentStatePagerAdapter {

    int tabCount;
    String whoseCalling;
    private Fragment fragment;

    public MyAccountViewPagerAdapter(FragmentManager fm, int tabCount, String whoseCalling) {
        super(fm);
        this.tabCount = tabCount;
        this.whoseCalling = whoseCalling;
    }

    @Override
    public Fragment getItem(int position) {

        if(whoseCalling.equals("profileEdit"))
        {
            switch (position) {
                case 0:
                    fragment = new PublicProfileEditFragment();
                    //return fragment1;
                    break;
                case 1:
                   fragment = new PrivateProfileEditFragment();
                   // return fragment2;
                    break;

                default:
                    return null;
            }
        }
        else if(whoseCalling.equals("viewProfile"))
        {
            switch (position) {
                case 0:
                    fragment = new PublicProfileFragment();
                   // return fragment1;
                    break;
                case 1:
                    fragment = new PrivateProfileFragment();
                   // return fragment2;
                    break;

                default:
                    return null;
            }
        }

        return fragment;

    }


    @Override
    public int getCount() {
        return tabCount;
    }
}


