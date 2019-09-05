package com.root.wishlist.util.globalValues;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.root.wishlist.R;

/**
 * Created by dal on 26/9/16.
 */
public class SignoutDialogbox {

    Context context;
    private android.widget.TextView contactustxt;
    private android.widget.TextView privacypolicytxt;
    private android.widget.TextView termsservicetxt;
    private android.widget.TextView signouttxt;
    private android.widget.TextView cancletxt;

    public SignoutDialogbox(Context context) {

        this.context = context;
    }

    public void signout() {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.signout_dialog_box);
        dialog.getWindow().setBackgroundDrawable(context.getResources().getDrawable(R.drawable.main_layout_corner));
        this.cancletxt = (TextView) dialog.findViewById(R.id.cancle_txt);
        this.signouttxt = (TextView) dialog.findViewById(R.id.signout_txt);
        this.termsservicetxt = (TextView) dialog.findViewById(R.id.terms_service_txt);
        this.privacypolicytxt = (TextView) dialog.findViewById(R.id.privacy_policy_txt);
        this.contactustxt = (TextView) dialog.findViewById(R.id.contactus_txt);
        signouttxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}
