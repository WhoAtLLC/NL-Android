package com.root.wishlist.activities;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.root.wishlist.database.SharedDatabase;
import com.root.wishlist.util.globalValues.NoNetworkConnection;
import com.root.wishlist.util.globalValues.StatusBarTransparent;
import com.root.wishlist.R;
import com.root.wishlist.fragment.notification.ArchiveFragment;
import com.root.wishlist.fragment.notification.IncomingFragment;
import com.root.wishlist.fragment.notification.OutgoingFragment;
import com.root.wishlist.interfaces.NetworkInterface;

public class NotificationActivity extends AppCompatActivity implements View.OnClickListener, NetworkInterface {


    private android.widget.LinearLayout myleads;
    private android.widget.LinearLayout mynotifications;
    private android.widget.LinearLayout myaccount;
    private android.widget.LinearLayout more;
    private ScrollView scrollView;
    private int position = 0;
    SharedDatabase sharedDatabase;
    public static ViewpagerAdapter viewpagerAdapter;
    private android.support.design.widget.TabLayout tablayout;
    private android.support.v4.view.ViewPager viewpager;

    private View incomingTitle, outGoingTitle, archiveTitle;
    private TextView tabTitle;
    private Typeface fontFace;
    private TextView leadstxt;
    private TextView notificationtxt;
    private TextView mycontacttct;
    private TextView moretxt;
    CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().hide();
        this.moretxt = (TextView) findViewById(R.id.more_txt);
        this.mycontacttct = (TextView) findViewById(R.id.mycontact_tct);
        this.notificationtxt = (TextView) findViewById(R.id.notification_txt);
        this.leadstxt = (TextView) findViewById(R.id.leads_txt);
        this.viewpager = (ViewPager) findViewById(R.id.viewpager);
        this.tablayout = (TabLayout) findViewById(R.id.tablayout);
        this.more = (LinearLayout) findViewById(R.id.more);
        this.myaccount = (LinearLayout) findViewById(R.id.my_account);
        this.mynotifications = (LinearLayout) findViewById(R.id.my_notifications);
        this.myleads = (LinearLayout) findViewById(R.id.my_leads);
        scrollView = (ScrollView) findViewById(R.id.scrollView);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinate);
        getSupportActionBar().hide();
        StatusBarTransparent.colorStatusbar(NotificationActivity.this);
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
        customHeading();
        mynotifications.setBackgroundColor(getResources().getColor(R.color.headermenu));
        myleads.setBackgroundColor(getResources().getColor(R.color.appColor));
        myaccount.setBackgroundColor(getResources().getColor(R.color.appColor));
        more.setBackgroundColor(getResources().getColor(R.color.appColor));
        myaccount.setOnClickListener(this);
        mynotifications.setOnClickListener(this);
        myleads.setOnClickListener(this);
        more.setOnClickListener(this);
        tablayout.addTab(tablayout.newTab().setCustomView(incomingTitle));
        tablayout.addTab(tablayout.newTab().setCustomView(outGoingTitle));
        tablayout.addTab(tablayout.newTab().setCustomView(archiveTitle));
        viewpagerAdapter = new ViewpagerAdapter(getSupportFragmentManager(), tablayout.getTabCount());
        viewpager.setAdapter(viewpagerAdapter);
        viewpagerAdapter.notifyDataSetChanged();
        viewpager.setOffscreenPageLimit(2);

        viewpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tablayout));
        tablayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                position = tab.getPosition();
                viewpager.setCurrentItem(tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {


            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        viewpager.setCurrentItem(getIntent().getIntExtra("flag", 0));

    }

    @Override
    public void connectionMessage(String message) {
        //SnackBarMessage.displayMessage(coordinatorLayout, message);
        try {
            NoNetworkConnection.networkMessage(this, "com.root.wishlist.activities.NotificationActivity");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    public class ViewpagerAdapter extends FragmentStatePagerAdapter {
        int tabCount;

        public ViewpagerAdapter(FragmentManager fm, int tabCount) {
            super(fm);
            this.tabCount = tabCount;
        }

        @Override
        public Fragment getItem(int tabCount) {
            switch (tabCount) {
                case 0:
                    IncomingFragment incomingFragment = new IncomingFragment();
                    return incomingFragment;
                case 1:
                    OutgoingFragment outgoingFragment = new OutgoingFragment();
                    return outgoingFragment;

                case 2:
                    ArchiveFragment archiveFragment = new ArchiveFragment();
                    return archiveFragment;

                default:
                    return null;
            }

        }

        @Override
        public int getCount() {
            return tabCount;
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
                break;
            case R.id.my_account:
                myaccount.setBackgroundColor(getResources().getColor(R.color.headermenu));
                mynotifications.setBackgroundColor(getResources().getColor(R.color.appColor));
                myleads.setBackgroundColor(getResources().getColor(R.color.appColor));
                more.setBackgroundColor(getResources().getColor(R.color.appColor));
                startActivity(new Intent(getApplicationContext(), MyAccountActivity.class).setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
                finish();
                overridePendingTransition(0, 0);
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
    private void customHeading() {

        incomingTitle = View.inflate(getApplicationContext(), R.layout.tab_incoming, null);
        tabTitle = (TextView) incomingTitle.findViewById(R.id.companies_title);
        tabTitle.setTextSize(14);
        tabTitle.setText("Incoming");
        tabTitle.setTypeface(fontFace);

        outGoingTitle = View.inflate(getApplicationContext(), R.layout.tab_outgoing, null);
        tabTitle = (TextView) outGoingTitle.findViewById(R.id.companies_title);
        tabTitle.setTextSize(14);
        tabTitle.setText("Outgoing");
        tabTitle.setTypeface(fontFace);
        archiveTitle = View.inflate(getApplicationContext(), R.layout.tab_archive, null);
        tabTitle = (TextView) archiveTitle.findViewById(R.id.companies_title);
        tabTitle.setTextSize(14);
        tabTitle.setText("Archive");
        tabTitle.setTypeface(fontFace);
    }

    @Override
    protected void onResume() {
        super.onResume();
       /* IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(receiver, filter);*/
        mynotifications.setBackgroundColor(getResources().getColor(R.color.headermenu));
        myleads.setBackgroundColor(getResources().getColor(R.color.appColor));
        myaccount.setBackgroundColor(getResources().getColor(R.color.appColor));
        more.setBackgroundColor(getResources().getColor(R.color.appColor));
    }

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getExtras() != null) {
                NetworkInfo ni = (NetworkInfo) intent.getExtras().get(ConnectivityManager.EXTRA_NETWORK_INFO);
                if (ni != null && ni.getState() == NetworkInfo.State.CONNECTED) {
                    Log.d("connectedd", "connected");
                   /* viewpagerAdapter = new ViewpagerAdapter(getSupportFragmentManager(), tablayout.getTabCount());
                    viewpager.setAdapter(viewpagerAdapter);*/
                    viewpager.setCurrentItem(getIntent().getIntExtra("flag", 0));
                }

            } else {
                Log.d("connectedd", "dadad-----connected");
            }
        }
    };

    @Override
    protected void onPause() {
        super.onPause();
        //unregisterReceiver(receiver);
    }
}
