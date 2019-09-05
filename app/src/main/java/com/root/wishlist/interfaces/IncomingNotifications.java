package com.root.wishlist.interfaces;

import com.root.wishlist.pojo.notification.Inbound;

import java.util.List;


public interface IncomingNotifications {
    void getIncomingNotification(List<Inbound> inbounds);

}
