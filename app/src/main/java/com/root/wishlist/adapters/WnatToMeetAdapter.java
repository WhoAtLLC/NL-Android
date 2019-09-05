package com.root.wishlist.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.root.wishlist.fragment.notification.NotificationBusiness;
import com.root.wishlist.fragment.notification.NotificationCompanies;
import com.root.wishlist.fragment.notification.NotificationMutualContact;

public class WnatToMeetAdapter extends FragmentStatePagerAdapter {

    int tabCount;
    int requestCode;
    private String requestname, prospectname,code;
    /*public WnatToMeetAdapter(FragmentManager fm, int tabCount, int requestCode) {
        super(fm);


    }*/

    public WnatToMeetAdapter(FragmentManager supportFragmentManager, int tabCount, int requestCode, String requestname, String prospectname,String code) {
        super(supportFragmentManager);
        this.tabCount = tabCount;
        this.requestCode = requestCode;
        this.requestname = requestname;
        this.prospectname = prospectname;
        this.code=code;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:

                NotificationBusiness fragment2 = new NotificationBusiness(requestCode,requestname,prospectname,code);
                return fragment2;

            case 1:
                NotificationCompanies fragment1 = new NotificationCompanies(requestCode);
                return fragment1;


            case 2:

                NotificationMutualContact fragment3 = new NotificationMutualContact(requestCode);
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
