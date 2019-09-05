package com.root.wishlist.model.registration;

/**
 * Created by dal on 19/10/16.
 */
public interface SelectNetworkInt {

    interface selectNetwork {
        void setOnSelectnetwor(String network);
    }

    void selectedNetwork(String network, selectNetwork selectNetwork);

    void getSelectedNetwork(String network, selectNetwork selectNetwork);
}
