package com.root.wishlist.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.root.wishlist.database.SharedDatabase;
import com.root.wishlist.util.globalValues.EmailVerifiacationDiallog;
import com.root.wishlist.presentation.registration.EmailStatusInterface;
import com.root.wishlist.presentation.registration.EmailStatuspresentation;
import com.root.wishlist.R;
import com.root.wishlist.view.EmailverificationView;

import java.util.Timer;
import java.util.TimerTask;

public class VerificationScree extends AppCompatActivity implements EmailverificationView {

    EmailStatusInterface emailStatusInterface;
    SharedDatabase sharedDatabase;
    boolean isStatus = false;
    Timer timer;
    TimerTask task;
    int timeShedule = 25000;
    EmailVerifiacationDiallog emailVerifiacationDiallog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification_scree);
        emailStatusInterface = new EmailStatuspresentation(VerificationScree.this, getApplicationContext());
        sharedDatabase = new SharedDatabase(getApplicationContext());
        emailVerifiacationDiallog = new EmailVerifiacationDiallog(this);

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

    @Override
    public void getStatus(boolean isStatus) {

        this.isStatus = isStatus;
        if (!isStatus) {
            Log.d("status before", String.valueOf(isStatus));
            emailVerifiacationDiallog.validationShowDialog();
        } else {
            if (emailVerifiacationDiallog.siOpen()) {
                emailVerifiacationDiallog.emailDismiss();
                startActivity(new Intent(getApplicationContext(), LeadsActivity.class));
                finish();
            }
            timer.cancel();

            Log.d("status after", String.valueOf(timer));
        }

    }

    @Override
    public void reSendEmail(boolean status) {

    }
}
