package com.root.wishlist.interfaces;


import android.content.Context;

import com.root.wishlist.pojo.notification.Archived;
import com.root.wishlist.pojo.notification.Inbound;
import com.root.wishlist.pojo.notification.Outbound;

import java.util.List;

public interface IOAModel {
    interface OnNotificationIOAOnListener {

        void getInBound(List<Inbound> inboundList);

        void getOutBound(List<Outbound> outboundList);

        void getArchived(List<Archived> archivedList);

        void netWorkmessage(String network);
    }

    void notificationIOAModel(Context context, String token, OnNotificationIOAOnListener onNotificationIOAOnListener);
}
