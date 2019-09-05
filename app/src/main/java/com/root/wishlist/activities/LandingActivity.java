package com.root.wishlist.activities;

import android.app.ActivityManager;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.root.wishlist.R;
import com.root.wishlist.activities.registration.RegistrationActivity;
import com.root.wishlist.util.globalValues.GlobalClass;

public class LandingActivity extends AppCompatActivity {


    private TextView splashmessage1txt;
    private TextView splashmessage2txt;
    private TextView splashmessage3txt;
    private TextView newusertxt;
    private TextView logintxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
        new GlobalClass().statusBar(LandingActivity.this);
        getSupportActionBar().hide();
        initial();
    }

    private void initial() {

        this.logintxt = (TextView) findViewById(R.id.login_txt);
        this.newusertxt = (TextView) findViewById(R.id.new_user_txt);
        this.splashmessage3txt = (TextView) findViewById(R.id.splash_message3_txt);
        this.splashmessage2txt = (TextView) findViewById(R.id.splash_message2_txt);
        this.splashmessage1txt = (TextView) findViewById(R.id.splash_message1_txt);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "Font/Gotham-Book.otf");
        Typeface boldFont = Typeface.createFromAsset(getAssets(), "Font/Gotham-Medium.otf");

        logintxt.setTypeface(boldFont);
        newusertxt.setTypeface(boldFont);
        splashmessage3txt.setTypeface(boldFont);
        splashmessage2txt.setTypeface(boldFont);
        splashmessage1txt.setTypeface(boldFont);

    }

    public void newUser(View view) {
        Intent intent = new Intent(this, RegistrationActivity.class);
        startActivity(intent);
        finish();
        overridePendingTransition(0, 0);
    }

    public void loginUser(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
        overridePendingTransition(0, 0);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        ActivityManager am = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        am.killBackgroundProcesses("com.root.wishlist");
        System.runFinalizersOnExit(true);
        System.exit(0);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
