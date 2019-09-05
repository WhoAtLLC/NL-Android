package com.root.wishlist.presentation.notification;


import android.content.Context;

import com.root.wishlist.R;
import com.root.wishlist.database.SharedDatabase;
import com.root.wishlist.util.globalValues.NetworkConnection;
import com.root.wishlist.model.notification.NotificationIOAModel;
import com.root.wishlist.pojo.notification.Archived;
import com.root.wishlist.pojo.notification.Inbound;
import com.root.wishlist.pojo.notification.Outbound;
import com.root.wishlist.view.NotificationIOAView;

import java.util.List;

public class NotificationIOAPresentation implements NotificationIOAInterface, NotificationIOAModel.OnNotificationIOAOnListener {

    NotificationIOAView mNotificationIOAView;
    NotificationIOAModel notificationIOAModel;
    Context mContext;
    SharedDatabase sharedDatabase;


    public NotificationIOAPresentation(Context context, NotificationIOAView notificationIOAView) {
        this.mNotificationIOAView = notificationIOAView;
        this.mContext = context;
        notificationIOAModel = new NotificationIOAModel();
        sharedDatabase = new SharedDatabase(context);

    }


    @Override
    public void getInBound(List<Inbound> inboundList) {

        mNotificationIOAView.getInBound(inboundList);

    }

    @Override
    public void getOutBound(List<Outbound> outboundList) {
        mNotificationIOAView.getOutBound(outboundList);

    }

    @Override
    public void getArchived(List<Archived> archivedList) {
        mNotificationIOAView.getArchived(archivedList);


    }

    @Override
    public void netWorkmessage(String network) {
        mNotificationIOAView.newtworkError(network);
    }

    @Override
    public void getNotification() {

        try {
            if (NetworkConnection.isNetworkAvailable(mContext)) {
                notificationIOAModel.notificationIOAModel(mContext, "Token " + sharedDatabase.getToken(), this);
            } else {
                mNotificationIOAView.newtworkError(mContext.getString(R.string.networkconnection));
            }
        } catch (Exception e) {
            mNotificationIOAView.newtworkError(mContext.getString(R.string.networkconnection));
        }
    }
}
