package com.root.wishlist.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.root.wishlist.fragment.notification.ArchiveFragment;
import com.root.wishlist.fragment.notification.IncomingFragment;
import com.root.wishlist.fragment.notification.OutgoingFragment;

public class NotificationViewAdapter extends FragmentStatePagerAdapter {
    int tabCount;

    public NotificationViewAdapter(FragmentManager fm, int tabCount) {
        super(fm);
        this.tabCount = tabCount;
    }

    @Override
    public Fragment getItem(int tabCount) {
        switch (tabCount) {
            case 0:
                IncomingFragment incomingFragment = new IncomingFragment();
                return incomingFragment;
            case 1:
                OutgoingFragment outgoingFragment = new OutgoingFragment();
                return outgoingFragment;

            case 2:
                ArchiveFragment archiveFragment = new ArchiveFragment();
                return archiveFragment;

            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return tabCount;
    }
}