package com.root.wishlist.searvices.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

public class NetworkReceiver extends BroadcastReceiver {

    public NetworkReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getExtras() != null) {
            NetworkInfo ni = (NetworkInfo) intent.getExtras().get(ConnectivityManager.EXTRA_NETWORK_INFO);
            if (ni != null && ni.getState() == NetworkInfo.State.CONNECTED) {
                Log.d("connectedd", "connected");
                /*NetworkUpdate networkUpdate = (NetworkUpdate) context;
                networkUpdate.reloadPage();*/
            }

        } else {
            Log.d("connectedd", "dadad-----connected");
        }

    }


}
