package com.root.wishlist.activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.root.wishlist.database.SharedDatabase;
import com.root.wishlist.util.globalValues.AlertDialogBox;
import com.root.wishlist.util.globalValues.InviteFriendMessage;
import com.root.wishlist.util.globalValues.StatusBarTransparent;
import com.root.wishlist.util.globalValues.WishlistProgressDialog;
import com.root.wishlist.presentation.registration.EmailStatusInterface;
import com.root.wishlist.presentation.registration.EmailStatuspresentation;
import com.root.wishlist.R;
import com.root.wishlist.view.EmailverificationView;

import java.util.Timer;
import java.util.TimerTask;

public class EmailVerificationActivity extends AppCompatActivity implements View.OnClickListener, EmailverificationView {

    private android.widget.TextView headingtitle;
    private android.widget.TextView youwegettxt;
    private android.widget.TextView perhapstxt;
    private android.widget.TextView verifiedtxt;
    private android.widget.TextView useremailverification;
    private android.widget.TextView resendemail;
    private android.widget.TextView policytxt;
    private android.widget.TextView policy2txt;
    private android.widget.TextView supportemail;
    EmailStatusInterface emailStatusInterface;
    WishlistProgressDialog wishlistProgressDialog;
    SharedDatabase sharedDatabase;
    TimerTask task;
    Timer timer;
    boolean isStatus = false;
    int timeShedule = 25000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_verification);
        getSupportActionBar().hide();
        StatusBarTransparent.colorStatusbar(EmailVerificationActivity.this);
        emailStatusInterface = new EmailStatuspresentation(EmailVerificationActivity.this, getApplicationContext());
        sharedDatabase = new SharedDatabase(getApplicationContext());
        wishlistProgressDialog=new WishlistProgressDialog(this);
        initial();
        timer = new Timer();
        timer = new Timer();
        task = new TimerTask() {
            @Override
            public void run() {

                if (!isStatus) {
                    Log.d("isstatus", String.valueOf(isStatus));
                    emailStatusInterface.setStatus();
                }
            }
        };
        timer.scheduleAtFixedRate(task, 5000, timeShedule);
    }

    void initial() {
        this.supportemail = (TextView) findViewById(R.id.support_email);
        this.policy2txt = (TextView) findViewById(R.id.policy2_txt);
        this.policytxt = (TextView) findViewById(R.id.policy_txt);
        this.resendemail = (TextView) findViewById(R.id.re_send_email);
        this.useremailverification = (TextView) findViewById(R.id.useremail_verification);
        this.verifiedtxt = (TextView) findViewById(R.id.verified_txt);
        this.perhapstxt = (TextView) findViewById(R.id.perhaps_txt);
        this.youwegettxt = (TextView) findViewById(R.id.you_we_get_txt);
        this.headingtitle = (TextView) findViewById(R.id.heading_title);
        //onclick
        supportemail.setOnClickListener(this);
        resendemail.setOnClickListener(this);

        Typeface boldFont = Typeface.createFromAsset(getAssets(), "Font/Gotham-Medium.otf");
        youwegettxt.setTypeface(boldFont);
        supportemail.setTypeface(boldFont);
        Typeface normalFont = Typeface.createFromAsset(getAssets(), "Font/Gotham-Book.otf");
        policy2txt.setTypeface(normalFont);
        headingtitle.setTypeface(normalFont);
        perhapstxt.setTypeface(normalFont);
        verifiedtxt.setTypeface(normalFont);
        useremailverification.setTypeface(normalFont);
        resendemail.setTypeface(normalFont);
        policytxt.setTypeface(normalFont);
        String email = sharedDatabase.getEmail();
        useremailverification.setText(email);
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.support_email) {
            InviteFriendMessage.supportMail(this);
        }
        if (v.getId() == R.id.re_send_email) {
            wishlistProgressDialog.dialogShow();
            emailStatusInterface.reSendEmail();
        }
    }

    @Override
    public void onBackPressed() {
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }

    @Override
    public void getStatus(boolean isStatus) {
        this.isStatus = isStatus;
        if (!isStatus) {
            Log.d("status before", String.valueOf(isStatus));

        } else {
            sharedDatabase.addStep("completed");
            startActivity(new Intent(getApplicationContext(), LeadsActivity.class));
            finish();
            overridePendingTransition(0,0);
            timer.cancel();
            Log.d("status after", String.valueOf(timer));
        }

    }

    @Override
    public void reSendEmail(boolean status) {
        wishlistProgressDialog.dismissDialog();
        if (status) {
            new AlertDialogBox(this).forgetPassword("Email sent successfully please check email", 2);
        }
        //Toast.makeText(EmailVerificationActivity.this, "" + status, Toast.LENGTH_SHORT).show();

    }
}
