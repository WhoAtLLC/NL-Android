package com.root.wishlist.presentation.registration;

import android.content.Context;

import com.root.wishlist.model.registration.SelectNetwork;
import com.root.wishlist.model.registration.SelectNetworkInt;
import com.root.wishlist.view.SelectNetworkView;


public class SelectNetworkPresentation implements SelectNetworkPresentationInt, SelectNetworkInt.selectNetwork {

    SelectNetworkInt selectNetworkInt;
    SelectNetworkView selectNetworkView;
    Context context;

    public SelectNetworkPresentation(SelectNetworkView selectNetworkView, Context context) {
        this.selectNetworkView = selectNetworkView;
        selectNetworkInt = new SelectNetwork(context);
        this.context = context;
    }

    @Override
    public void setNetwork(String network) {

        if (selectNetworkView != null) {
        }
        selectNetworkInt.selectedNetwork(network, this);
    }

    @Override
    public void setOnSelectnetwor(String network) {
        if (selectNetworkView != null) {
            selectNetworkView.navigatToHome(network);
        }

    }
}
