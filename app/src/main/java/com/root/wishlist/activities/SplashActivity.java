package com.root.wishlist.activities;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;

import com.crashlytics.android.Crashlytics;
import com.root.wishlist.R;
import com.root.wishlist.activities.registration.OpenOrPrivateNetwork;
import com.root.wishlist.activities.registration.RegisterationUploadContact;
import com.root.wishlist.activities.registration.RegistrationUserDetailsActivity;
import com.root.wishlist.activities.registration.YourBusinessInterest;
import com.root.wishlist.database.SharedDatabase;
import com.root.wishlist.searvices.ContactFetchSearvice;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;



public class SplashActivity extends AppCompatActivity {

    private android.widget.TextView splashmessage1txt;
    private android.widget.TextView splashmessage2txt;
    private android.widget.TextView splashmessage3txt;
    SharedDatabase sharedDatabase;
    String steps = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

      /*  requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);*/
        setContentView(R.layout.activity_splash);

       // new ContactFetchSearvice(getApplicationContext()).execute();
        // hashkey();
        // new ContactFetchSearvice(SplashActivity.this).execute();
        sharedDatabase = new SharedDatabase(getApplicationContext());
        steps = sharedDatabase.getStep();
        Log.d("step", steps);
        String version = "";
        PackageManager manager = this.getPackageManager();
        PackageInfo info = null;
        try {
            info = manager.getPackageInfo(this.getPackageName(), 0);
            version = info.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        sharedDatabase.setVersion(version);
        Thread thread = new Thread() {
            public void run() {
                try {
                    Thread.sleep(5000);
                    if (steps.equals("")) {
                        Intent intent = new Intent(SplashActivity.this, LandingActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent);
                        overridePendingTransition(0, 0);
                        finish();
                    }
                    if (steps.equals("step2")) {
                        Intent intent = new Intent(SplashActivity.this, RegistrationUserDetailsActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent);
                        finish();
                    }
                    if (steps.equals("contacts")) {
                        Intent intent = new Intent(SplashActivity.this, RegisterationUploadContact.class);
                        intent.putExtra("whoseCalling","syncContacts");
                        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent);
                        overridePendingTransition(0, 0);
                        finish();
                    }
                    if (steps.equals("companies")) {
                        Intent intent = new Intent(SplashActivity.this, YourBusinessInterest.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent);
                        overridePendingTransition(0, 0);
                        finish();
                    }
                    if (steps.equals("completed") && !sharedDatabase.getToken().equals("")) {
                        Intent intent = new Intent(SplashActivity.this, LeadsActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent);
                        overridePendingTransition(0, 0);
                        finish();
                    }
                    if (steps.equals("OpenOrPrivateNetwork")) {
                        Intent intent = new Intent(SplashActivity.this, OpenOrPrivateNetwork.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent);
                        overridePendingTransition(0, 0);
                        finish();
                    }
                    if (steps.equals("VerificationScree")) {
                        Intent intent = new Intent(SplashActivity.this, EmailVerificationActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent);
                        overridePendingTransition(0, 0);
                        finish();
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
    }

    public void hashkey() {
        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    getPackageName(),
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        String action = intent.getAction();
        Uri data = intent.getData();
        Log.d("uri",String.valueOf(data));
    }
}
