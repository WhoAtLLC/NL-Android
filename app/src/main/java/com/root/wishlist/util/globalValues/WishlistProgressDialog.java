package com.root.wishlist.util.globalValues;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Window;
import android.widget.ProgressBar;

import com.root.wishlist.R;

public class WishlistProgressDialog {

    Context context;
    Dialog dialog;
    private android.widget.ProgressBar progressbar;

    public WishlistProgressDialog(Context context) {
        this.context = context;

    }

    public void dialogShow() {
        dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.loader);
        this.progressbar = (ProgressBar) dialog.findViewById(R.id.progressbar);

        //loadingview.getIndeterminateDrawable().setColorFilter(Color.parseColor("#5B9EEC"), PorterDuff.Mode.MULTIPLY);
        dialog.show();
    }

    public void dismissDialog() {
        if (dialog != null && dialog.isShowing())
            dialog.dismiss();

    }

}
