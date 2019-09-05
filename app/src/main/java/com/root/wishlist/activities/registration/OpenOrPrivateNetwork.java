package com.root.wishlist.activities.registration;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kyleduo.switchbutton.SwitchButton;
import com.root.wishlist.activities.MoreActivity;
import com.root.wishlist.database.SharedDatabase;
import com.root.wishlist.util.globalValues.GlobalClass;
import com.root.wishlist.presentation.registration.SelectNetworkPresentationInt;
import com.root.wishlist.R;
import com.root.wishlist.view.SelectNetworkView;
import com.root.wishlist.fragment.OpenNetwork;
import com.root.wishlist.fragment.PrivatNetwork;

public class OpenOrPrivateNetwork extends AppCompatActivity implements SelectNetworkView, View.OnClickListener {

    SelectNetworkPresentationInt selectNetworkPresentationInt;
    Fragment fragment;
    FragmentManager fragmentManager;
    private SwitchButton switch1;
    private FrameLayout frame;
    private android.widget.TextView chooseopenorprivate;
    private android.widget.TextView opennetwork;
    private android.widget.TextView privatenetwork;
    private android.widget.ImageView backarrowimage;
    private LinearLayout heghlighticon;
    int flagvalu = 0;
    private String message;
    SharedDatabase sharedDatabase;
    private boolean isSelected = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new GlobalClass().statusBar(OpenOrPrivateNetwork.this);
        setContentView(R.layout.activity_open_or_private_network);
        getSupportActionBar().hide();
        flagvalu = getIntent().getIntExtra("flagvalue", 0);
        initial();
        sharedDatabase = new SharedDatabase(getApplicationContext());

        if (sharedDatabase.getNetwork().equals("private")) {

            fragment = new PrivatNetwork(flagvalu, "private");
            switch1.setChecked(true);
        } else {
            fragment = new OpenNetwork(flagvalu, "open");
        }
       /* selectNetworkPresentationInt = new SelectNetworkPresentation(OpenOrPrivateNetwork.this, getApplicationContext());*/
        message = "open";
        //selectNetworkPresentationInt.setNetwork(message);
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frame, fragment).commit();
        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                selectedValue(b);
            }

        });
    }

    private void selectedValue(boolean b) {

        if (b) {
            fragment = new PrivatNetwork(flagvalu, "private");
            message = "private";
        } else {
            fragment = new OpenNetwork(flagvalu, "open");
            message = "open";
        }
        // sharedDatabase.setNetwork(message);
        //selectNetworkPresentationInt.setNetwork(message);
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frame, fragment).commit();


    }

    private void initial() {
        this.privatenetwork = (TextView) findViewById(R.id.privatenetwork);
        this.opennetwork = (TextView) findViewById(R.id.opennetwork);
        this.chooseopenorprivate = (TextView) findViewById(R.id.choose_open_or_private);
        this.switch1 = (SwitchButton) findViewById(R.id.switch1);
        this.backarrowimage = (ImageView) findViewById(R.id.back_arrow_image);
        this.heghlighticon = (LinearLayout) findViewById(R.id.heighlight_icon);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "Font/Gotham-Book.otf");
        privatenetwork.setTypeface(typeface);
        opennetwork.setTypeface(typeface);
        chooseopenorprivate.setTypeface(typeface);
        backarrowimage.setOnClickListener(this);
        if (flagvalu == 1) {
            backarrowimage.setVisibility(View.INVISIBLE);
            heghlighticon.setVisibility(View.GONE);
        }
    }

    @Override
    public void onBackPressed() {

        if (flagvalu == 1) {
            Intent intent = new Intent(this, MoreActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
            overridePendingTransition(0, 0);
        }
    }

    @Override
    public void navigatToHome(String network) {
        /*if (flagvalu == 1) {
            if (network.equals("private")) {
                fragment = new PrivatNetwork(flagvalu);
                isSelected = true;
                message = "private";
                selectedValue(isSelected);
                fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.frame, fragment).commit();
            } else {
                isSelected = false;
            }
        }*/
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.back_arrow_image) {
            Intent intent = new Intent(this, MoreActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            overridePendingTransition(0, 0);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();


    }
}
