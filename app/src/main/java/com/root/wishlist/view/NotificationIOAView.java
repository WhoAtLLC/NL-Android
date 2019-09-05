package com.root.wishlist.view;


import com.root.wishlist.pojo.notification.Archived;
import com.root.wishlist.pojo.notification.Inbound;
import com.root.wishlist.pojo.notification.Outbound;

import java.util.List;

public interface NotificationIOAView {
    void  getInBound(List<Inbound> inboundList);
    void  getOutBound(List<Outbound> outboundList);
    void  getArchived(List<Archived> archivedList);
    void newtworkError(String connection);
}
