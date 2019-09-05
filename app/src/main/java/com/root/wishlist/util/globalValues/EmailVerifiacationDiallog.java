package com.root.wishlist.util.globalValues;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.root.wishlist.activities.LeadsActivity;
import com.root.wishlist.database.SharedDatabase;
import com.root.wishlist.R;

public class EmailVerifiacationDiallog {

    SharedDatabase sharedDatabase;
    Context context;
    private TextView headingtitle;
    private TextView youwegettxt;
    private TextView perhapstxt;
    private TextView verifiedtxt;
    private TextView useremailverification;
    private TextView resendemail;
    private TextView policytxt;
    private TextView policy2txt;
    private TextView supportemail;
    Dialog dialog;

    public EmailVerifiacationDiallog(Context context) {
        super();
        this.context = context;
        sharedDatabase = new SharedDatabase(context);
    }

    public void validationShowDialog() {
        dialog = new Dialog(context, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.activity_email_verification);
        dialog.setCancelable(false);
        this.supportemail = (TextView) dialog.findViewById(R.id.support_email);
        this.policy2txt = (TextView) dialog.findViewById(R.id.policy2_txt);
        this.policytxt = (TextView) dialog.findViewById(R.id.policy_txt);
        this.resendemail = (TextView) dialog.findViewById(R.id.re_send_email);
        this.useremailverification = (TextView) dialog.findViewById(R.id.useremail_verification);
        this.verifiedtxt = (TextView) dialog.findViewById(R.id.verified_txt);
        this.perhapstxt = (TextView) dialog.findViewById(R.id.perhaps_txt);
        this.youwegettxt = (TextView) dialog.findViewById(R.id.you_we_get_txt);
        this.headingtitle = (TextView) dialog.findViewById(R.id.heading_title);
        Typeface boldFont = Typeface.createFromAsset(context.getAssets(), "Font/Gotham-Medium.otf");
        youwegettxt.setTypeface(boldFont);
        supportemail.setTypeface(boldFont);
        Typeface normalFont = Typeface.createFromAsset(context.getAssets(), "Font/Gotham-Book.otf");
        policy2txt.setTypeface(normalFont);
        headingtitle.setTypeface(normalFont);
        perhapstxt.setTypeface(normalFont);
        verifiedtxt.setTypeface(normalFont);
        useremailverification.setTypeface(normalFont);
        resendemail.setTypeface(normalFont);
        policytxt.setTypeface(normalFont);
        String email = sharedDatabase.getEmail();
        useremailverification.setText(email);

        resendemail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LeadsActivity.emailStatusInterface.reSendEmail();
            }
        });
        supportemail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InviteFriendMessage.supportMail(context);
            }
        });
        dialog.show();
    }

    public void emailDismiss() {
        dialog.dismiss();

    }

    public boolean siOpen() {
        boolean isshow = false;
        if (dialog != null) {
            if (dialog.isShowing())
                isshow = true;

        }
        return isshow;
    }


}
