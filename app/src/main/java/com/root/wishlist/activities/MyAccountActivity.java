package com.root.wishlist.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.root.wishlist.activities.myaccount.ProfileEditActivity;
import com.root.wishlist.R;
import com.root.wishlist.adapters.MyAccountViewPagerAdapter;
import com.root.wishlist.database.SharedDatabase;
import com.root.wishlist.interfaces.NetworkInterface;
import com.root.wishlist.presentation.registration.SelectNetworkPresentation;
import com.root.wishlist.presentation.registration.SelectNetworkPresentationInt;
import com.root.wishlist.util.globalValues.InviteFriendMessage;
import com.root.wishlist.util.globalValues.NoNetworkConnection;
import com.root.wishlist.util.globalValues.StatusBarTransparent;
import com.root.wishlist.view.SelectNetworkView;

public class MyAccountActivity extends AppCompatActivity implements View.OnClickListener, NetworkInterface, SelectNetworkView {

    private static final int REQUEST_ACCOUNT = 1;
    private android.widget.LinearLayout myleads;
    private android.widget.LinearLayout mynotifications;
    private android.widget.LinearLayout myaccount;
    private android.widget.LinearLayout more;

    private android.support.design.widget.TabLayout tablayout;
    private android.support.v4.view.ViewPager viewpager;
    MyAccountViewPagerAdapter myAccountViewPagerAdapter;

    int position = 0;
    private com.github.clans.fab.FloatingActionButton editfloat;
    /*private com.github.clans.fab.FloatingActionButton contactusfloat;
    private com.github.clans.fab.FloatingActionButton privacypolicyfloat;
    private com.github.clans.fab.FloatingActionButton termsservicefloat;
    private com.github.clans.fab.FloatingActionButton signoutfloat;*/
    private com.github.clans.fab.FloatingActionMenu materialdesignandroidfloatingactionmenu;
    private FloatingActionButton pvteditfloat;
   /* private FloatingActionButton pvtcontactusfloat;
    private FloatingActionButton pvtprivacypolicyfloat;
    private FloatingActionButton pvttermsservicefloat;
    private FloatingActionButton pvtsignoutfloat;*/
    private FloatingActionMenu pvtmaterialdesignandroidfloatingactionmenu;
    SharedDatabase sharedDatabase;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    public static final String SHAREDDATABASE = "wishlist";
    Intent intent;
    private android.widget.TextView leadstxt;
    private android.widget.TextView notificationtxt;
    private android.widget.TextView mycontacttct;
    private android.widget.TextView moretxt;
    Typeface fontFace;
    CoordinatorLayout coordinatorLayout;
    SelectNetworkPresentationInt selectNetworkPresentationInt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinate);
        this.moretxt = (TextView) findViewById(R.id.more_txt);
        this.mycontacttct = (TextView) findViewById(R.id.mycontact_tct);
        this.notificationtxt = (TextView) findViewById(R.id.notification_txt);
        this.leadstxt = (TextView) findViewById(R.id.leads_txt);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().hide();
        StatusBarTransparent.colorStatusbar(MyAccountActivity.this);
        selectNetworkPresentationInt = new SelectNetworkPresentation(MyAccountActivity.this, getApplicationContext());
        sharedDatabase = new SharedDatabase(getApplicationContext());
        fontFace = Typeface.createFromAsset(getAssets(), "Font/Gotham-Book.otf");
        moretxt.setTypeface(fontFace);
        mycontacttct.setTypeface(fontFace);
        notificationtxt.setTypeface(fontFace);
        leadstxt.setTypeface(fontFace);
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.KITKAT) {
            moretxt.setTextSize(8);
            mycontacttct.setTextSize(8);
            notificationtxt.setTextSize(8);
            leadstxt.setTextSize(8);
        } else {
            moretxt.setTextSize(8);
            mycontacttct.setTextSize(8);
            notificationtxt.setTextSize(8);
            leadstxt.setTextSize(8);
        }
        this.pvtmaterialdesignandroidfloatingactionmenu = (FloatingActionMenu) findViewById(R.id.pvt_material_design_android_floating_action_menu);
       /* this.pvtsignoutfloat = (FloatingActionButton) findViewById(R.id.pvt_signout_float);
        this.pvttermsservicefloat = (FloatingActionButton) findViewById(R.id.pvt_terms_service_float);
        this.pvtprivacypolicyfloat = (FloatingActionButton) findViewById(R.id.pvt_privacy_policy_float);
        this.pvtcontactusfloat = (FloatingActionButton) findViewById(R.id.pvt_contactus_float);*/
        this.pvteditfloat = (FloatingActionButton) findViewById(R.id.pvt_edit_float);


        this.materialdesignandroidfloatingactionmenu = (FloatingActionMenu) findViewById(R.id.material_design_android_floating_action_menu);
        /*this.signoutfloat = (FloatingActionButton) findViewById(R.id.signout_float);
        this.termsservicefloat = (FloatingActionButton) findViewById(R.id.terms_service_float);
        this.privacypolicyfloat = (FloatingActionButton) findViewById(R.id.privacy_policy_float);
        this.contactusfloat = (FloatingActionButton) findViewById(R.id.contactus_float);*/
        this.editfloat = (FloatingActionButton) findViewById(R.id.edit_float);


        this.viewpager = (ViewPager) findViewById(R.id.viewpager);
        this.tablayout = (TabLayout) findViewById(R.id.tablayout);

        this.more = (LinearLayout) findViewById(R.id.more);
        this.myaccount = (LinearLayout) findViewById(R.id.my_account);
        this.mynotifications = (LinearLayout) findViewById(R.id.my_notifications);
        this.myleads = (LinearLayout) findViewById(R.id.my_leads);

        //onclick
        editfloat.setOnClickListener(this);
       /* signoutfloat.setOnClickListener(this);
        contactusfloat.setOnClickListener(this);
        privacypolicyfloat.setOnClickListener(this);
        termsservicefloat.setOnClickListener(this);

        pvtsignoutfloat.setOnClickListener(this);
        pvttermsservicefloat.setOnClickListener(this);
        pvtprivacypolicyfloat.setOnClickListener(this);
        pvtcontactusfloat.setOnClickListener(this);*/
        pvteditfloat.setOnClickListener(this);

        //
        myaccount.setBackgroundColor(getResources().getColor(R.color.headermenu));
        mynotifications.setBackgroundColor(getResources().getColor(R.color.appColor));
        myleads.setBackgroundColor(getResources().getColor(R.color.appColor));
        more.setBackgroundColor(getResources().getColor(R.color.appColor));
        myaccount.setOnClickListener(this);
        mynotifications.setOnClickListener(this);
        myleads.setOnClickListener(this);
        more.setOnClickListener(this);

        pvtmaterialdesignandroidfloatingactionmenu.setVisibility(View.GONE);
        materialdesignandroidfloatingactionmenu.setVisibility(View.VISIBLE);
        //FOR TAB

        position = getIntent().getIntExtra("position", 0);
        tablayout.addTab(tablayout.newTab().setText("Public"));
        tablayout.addTab(tablayout.newTab().setText("Private"));
        myAccountViewPagerAdapter = new MyAccountViewPagerAdapter(getSupportFragmentManager(), tablayout.getTabCount(),"viewProfile");
        viewpager.setOffscreenPageLimit(2);
        viewpager.setAdapter(myAccountViewPagerAdapter);

        viewpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tablayout));
        tablayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                floatMenu(tab.getPosition());
                viewpager.setCurrentItem(tab.getPosition());
                //Toast.makeText(MyAccountActivity.this, "select", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                // Toast.makeText(MyAccountActivity.this, "unselect", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                //Toast.makeText(MyAccountActivity.this, "release", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void floatMenu(int position) {

        switch (position) {
            case 0:
                pvtmaterialdesignandroidfloatingactionmenu.setVisibility(View.GONE);
                materialdesignandroidfloatingactionmenu.setVisibility(View.VISIBLE);
                break;
            case 1:
                pvtmaterialdesignandroidfloatingactionmenu.setVisibility(View.VISIBLE);
                materialdesignandroidfloatingactionmenu.setVisibility(View.GONE);
                break;
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.my_leads:
                myleads.setBackgroundColor(getResources().getColor(R.color.headermenu));
                mynotifications.setBackgroundColor(getResources().getColor(R.color.appColor));
                myaccount.setBackgroundColor(getResources().getColor(R.color.appColor));
                more.setBackgroundColor(getResources().getColor(R.color.appColor));
                startActivity(new Intent(getApplicationContext(), LeadsActivity.class).setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
                finish();
                overridePendingTransition(0, 0);
                break;
            case R.id.my_notifications:
                mynotifications.setBackgroundColor(getResources().getColor(R.color.headermenu));
                myleads.setBackgroundColor(getResources().getColor(R.color.appColor));
                myaccount.setBackgroundColor(getResources().getColor(R.color.appColor));
                more.setBackgroundColor(getResources().getColor(R.color.appColor));
                startActivity(new Intent(getApplicationContext(), NotificationActivity.class).setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
                finish();
                overridePendingTransition(0, 0);
                break;
            case R.id.my_account:
                myaccount.setBackgroundColor(getResources().getColor(R.color.headermenu));
                mynotifications.setBackgroundColor(getResources().getColor(R.color.appColor));
                myleads.setBackgroundColor(getResources().getColor(R.color.appColor));
                more.setBackgroundColor(getResources().getColor(R.color.appColor));
                break;
            case R.id.more:
                more.setBackgroundColor(getResources().getColor(R.color.headermenu));
                mynotifications.setBackgroundColor(getResources().getColor(R.color.appColor));
                myaccount.setBackgroundColor(getResources().getColor(R.color.appColor));
                myleads.setBackgroundColor(getResources().getColor(R.color.appColor));
                startActivity(new Intent(getApplicationContext(), MoreActivity.class).setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
                finish();
                overridePendingTransition(0, 0);
                break;
            //for menu
            case R.id.edit_float:
                //intent = new Intent(getApplicationContext(), PublicProfileEditActivity.class);
                intent = new Intent(getApplicationContext(), ProfileEditActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_ANIMATION);
                materialdesignandroidfloatingactionmenu.close(true);
                startActivity(intent);
                break;
         /*   case R.id.contactus_float:
                materialdesignandroidfloatingactionmenu.close(true);
                InviteFriendMessage.supportMail(this);
                position = 0;
                break;
            case R.id.privacy_policy_float:
                intent = new Intent(getApplicationContext(), PrivacyPolicyActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_ANIMATION);
                materialdesignandroidfloatingactionmenu.close(true);
                startActivity(intent);
                overridePendingTransition(0, 0);
                position = 0;
                break;
            case R.id.terms_service_float:
                intent = new Intent(getApplicationContext(), TermAndConditionActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_ANIMATION);
                materialdesignandroidfloatingactionmenu.close(true);
                startActivity(intent);
                overridePendingTransition(0, 0);
                position = 0;
                break;
            case R.id.signout_float:
                logOut();
                break;*/
////for private
            case R.id.pvt_edit_float:
               // Intent intent = new Intent(getApplicationContext(), PrivateprofileEditActivity.class);
                Intent intent = new Intent(getApplicationContext(), ProfileEditActivity.class);
                materialdesignandroidfloatingactionmenu.close(true);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
                finish();
                overridePendingTransition(0, 0);
                break;
          /*  case R.id.pvt_contactus_float:
                materialdesignandroidfloatingactionmenu.close(true);
                InviteFriendMessage.supportMail(this);
                position = 1;
                break;
            case R.id.pvt_privacy_policy_float:
                intent = new Intent(getApplicationContext(), PrivacyPolicyActivity.class);
                materialdesignandroidfloatingactionmenu.close(true);
                startActivity(intent);
                overridePendingTransition(0, 0);
                position = 1;
                break;
            case R.id.pvt_terms_service_float:
                intent = new Intent(getApplicationContext(), TermAndConditionActivity.class);
                materialdesignandroidfloatingactionmenu.close(true);
                startActivity(intent);
                overridePendingTransition(0, 0);
                position = 1;
                break;
            case R.id.pvt_signout_float:
                logOut();
                break;*/
        }
    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), LeadsActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
        overridePendingTransition(0, 0);
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        myaccount.setBackgroundColor(getResources().getColor(R.color.headermenu));
        mynotifications.setBackgroundColor(getResources().getColor(R.color.appColor));
        myleads.setBackgroundColor(getResources().getColor(R.color.appColor));
        more.setBackgroundColor(getResources().getColor(R.color.appColor));

        viewpager.setCurrentItem(position);
        myAccountViewPagerAdapter.notifyDataSetChanged();
    }

    @Override
    public void connectionMessage(String message) {
        //SnackBarMessage.displayMessage(coordinatorLayout, message);
        try {
            NoNetworkConnection.networkMessage(this, "com.root.wishlist.activities.MyAccountActivity");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void navigatToHome(String network) {

    }

   /* private void logOut() {
        sharedDatabase.saveLeadsCompany(null);
        sharedDatabase.addStep("");
        sharedDatabase.userToken("");
        selectNetworkPresentationInt.setNetwork("open");
        sharedDatabase.setNetwork("open");
        intent = new Intent(getApplicationContext(), LandingActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
        overridePendingTransition(0, 0);
    }*/
}
