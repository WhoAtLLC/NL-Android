package com.root.wishlist.util.globalValues;


import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.view.View;

public class SnackBarMessage {

    public static void displayMessage(View view, String message) {
        Snackbar snackbar;
        snackbar = Snackbar.make(view, message, Snackbar.LENGTH_LONG);
        View snackBarView = snackbar.getView();
        snackBarView.setBackgroundColor(Color.parseColor("#FF4545"));
        snackbar.show();
    }
}
