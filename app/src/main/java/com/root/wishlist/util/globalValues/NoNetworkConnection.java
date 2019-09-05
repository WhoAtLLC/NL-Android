package com.root.wishlist.util.globalValues;


import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;

import com.root.wishlist.R;

public class NoNetworkConnection {

    private static android.widget.LinearLayout tryagain;
    public static Dialog dialog;

    public static void networkMessage(final Context context, String activity) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.nointernetconnection);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        tryagain = (LinearLayout) dialog.findViewById(R.id.try_again);
        dialog.setCancelable(false);
        final Class<?> aClass = Class.forName(activity);
        Activity activity1 = (Activity) aClass.newInstance();
        tryagain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Intent intent = new Intent(context, aClass);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                context.startActivity(intent);

            }
        });
        dialog.show();

    }

    public static void dismissNetworkDialog(Context context) {
        if (dialog.isShowing()) {
            dialog.dismiss();
        }
    }
}