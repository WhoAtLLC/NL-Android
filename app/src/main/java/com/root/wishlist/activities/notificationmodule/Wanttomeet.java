package com.root.wishlist.activities.notificationmodule;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.root.wishlist.adapters.WnatToMeetAdapter;
import com.root.wishlist.util.globalValues.BottomSheetDialogPage;
import com.root.wishlist.util.globalValues.NoNetworkConnection;
import com.root.wishlist.util.globalValues.NotificationDialog;
import com.root.wishlist.util.globalValues.StatusBarTransparent;
import com.root.wishlist.interfaces.NetworkInterface;
import com.root.wishlist.presentation.leads.UserDetailsInterface;
import com.root.wishlist.presentation.leads.UserDetaisPresentation;
import com.root.wishlist.R;
import com.root.wishlist.view.UserInformationView;
import com.root.wishlist.interfaces.Refreshpage;

public class Wanttomeet extends AppCompatActivity implements UserInformationView, View.OnClickListener, Refreshpage, NetworkInterface {

    private android.widget.TextView handlername;
    private android.widget.TextView meetto;
    private android.widget.TextView profileTxt;
    private android.widget.ImageView viewprofile;
    private Toolbar toolbar;
    private TextView messagetext;
    private android.support.v4.view.ViewPager meetviewpager;
    TabLayout meetTab;
    LinearLayout accept, decline, linerLyout;
    WnatToMeetAdapter wnatToMeetAdapter;
    int position = 0;
    String handler, requestname;
    private String handle;
    private String shortBio;
    private String phone;
    private String bio;
    private String title;
    private String image;
    private String businessDiscussion;
    private String businessAdditional;
    private String email;
    UserDetailsInterface userDetailsInterface;
    int requestCode;
    //
    private BottomSheetBehavior mBottomSheetBehavior;
    private View bottomsheet;
    View resonTitle, companiesTitle, mutualTitle;
    TextView tabTitle;
    String status = "";
    Typeface typeface;
    String requesterName = "";
    private ImageView leftBackWantToMeet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wnattomeet);
        this.toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        StatusBarTransparent.colorStatusbar(Wanttomeet.this);
        status = getIntent().getStringExtra("status");
        requestCode = getIntent().getIntExtra("requestID", 0);
        requestname = getIntent().getStringExtra("requestname");
        handler = getIntent().getStringExtra("handler");
        if(requestname!=null)
        {
            if (requestname.contains(" "))
                requesterName = requestname.substring(0, requestname.lastIndexOf(" "));
            else
                requesterName = requestname;
        }

        initial();
        //
        this.bottomsheet = findViewById(R.id.bottom_sheet);

        mBottomSheetBehavior = BottomSheetBehavior.from(bottomsheet);
        if (status.equals("accepted") && (getIntent().getStringExtra("code").equals("Ar") || getIntent().getStringExtra("code").equals("Ot"))) {
            viewprofile.setImageResource(R.drawable.view_profile);
            profileTxt.setText("Contact");
        }
        userDetailsInterface = new UserDetaisPresentation(Wanttomeet.this, getApplicationContext());
        handlername.setText(requestname);
        userDetailsInterface.notificationUser(getIntent().getStringExtra("handler"));

    }

    private void initial() {
        accept = (LinearLayout) findViewById(R.id.accept);
        decline = (LinearLayout) findViewById(R.id.decline);
        linerLyout = (LinearLayout) findViewById(R.id.accpt_lyt);
        this.meetviewpager = (ViewPager) findViewById(R.id.meet_viewpager);
        this.messagetext = (TextView) findViewById(R.id.message_text);
        this.viewprofile = (ImageView) findViewById(R.id.view_profile);
        this.meetto = (TextView) findViewById(R.id.meet_to);
        this.profileTxt = (TextView) findViewById(R.id.profile_txt);
        this.handlername = (TextView) findViewById(R.id.handler_name);
        this.leftBackWantToMeet = findViewById(R.id.leftbackwanttomeet);
        viewprofile.setOnClickListener(this);
        accept.setOnClickListener(this);
        decline.setOnClickListener(this);
        leftBackWantToMeet.setOnClickListener(this);
        customHeading();
        typeface = Typeface.createFromAsset(getAssets(), "Font/Gotham-Book.otf");
        meetto.setTypeface(typeface);
        messagetext.setTypeface(typeface);
        handlername.setTypeface(typeface);
        profileTxt.setTypeface(typeface);

        meetTab = (TabLayout) findViewById(R.id.meetto_tab);
        if (getIntent().getStringExtra("code").equals("In")) {
            profileTxt.setText("Contact");
            viewprofile.setImageResource(R.drawable.view_profile);
            messagetext.setText("How " + requesterName + " can help you in return");
        } else
            messagetext.setText("How you offered to help " + requesterName);
        meetTab.addTab(meetTab.newTab().setCustomView(resonTitle));
        meetTab.addTab(meetTab.newTab().setCustomView(companiesTitle));
        meetTab.addTab(meetTab.newTab().setCustomView(mutualTitle));
        wnatToMeetAdapter = new WnatToMeetAdapter(getSupportFragmentManager(), meetTab.getTabCount(), requestCode, getIntent().getStringExtra("requestname"), getIntent().getStringExtra("prospectname"), getIntent().getStringExtra("code"));
        meetviewpager.setAdapter(wnatToMeetAdapter);
        meetviewpager.setOffscreenPageLimit(3);
        meetviewpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(meetTab));
        meetTab.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                position = tab.getPosition();

                switch (getIntent().getStringExtra("code")) {
                    case "In":
                        setHeading(position);
                        break;
                    case "Ot":
                        messagetext.setText("How you offered to help " + requesterName);
                        break;
                    case "Ar":
                        messagetext.setText("How you offered to help " + requesterName);
                        break;
                }
                meetviewpager.setCurrentItem(tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        if (status != null) {
            if (status.equals("pending") && (getIntent().getStringExtra("code").equals("In")))
                linerLyout.setVisibility(View.VISIBLE);
            else linerLyout.setVisibility(View.GONE);
        }

        switch (getIntent().getStringExtra("code")) {
            case "In":
                inComingStatus();
                break;
            case "Ot":
                outGoingStatus();
                break;
            case "Ar":
                outGoingStatus();
                break;
        }


    }

    private void setHeading(int position) {

        switch (position) {
            case 0:
                messagetext.setText("How " + requesterName + " can help you in return");
                break;
            case 1:
                messagetext.setText("Companies " + requesterName + " can help you with");
                break;
            case 2:
                messagetext.setText("Your mutual contacts with " + requesterName);
                break;
        }

    }

    private void outGoingStatus() {
        if (status.equals("accepted")) {
            meetto.setText("Accepted your request to meet \n" + getIntent().getStringExtra("prospectname"));
        } else if (status.equals("pending")) {
            meetto.setText("will introduce you to \n" + getIntent().getStringExtra("prospectname"));
        } else if (status.equals("declined")) {
            meetto.setText("Declined your request to meet \n" + getIntent().getStringExtra("prospectname"));
        }
    }

    private void inComingStatus() {
        meetto.setText("wants to meet \n" + getIntent().getStringExtra("prospectname"));
    }


    @Override
    public void onClick(View view) {
        Log.d("handler", getIntent().getStringExtra("handler"));
        Log.d("status", getIntent().getStringExtra("status"));
        switch (view.getId()) {
            case R.id.view_profile:
                if (getIntent().getStringExtra("code").equals("In")) {
                    BottomSheetDialogFragment bottomSheetDialogFragment = new BottomSheetDialogPage(this, handle, title, bio, image, businessDiscussion, businessAdditional, phone, email);
                    bottomSheetDialogFragment.show(getSupportFragmentManager(), bottomSheetDialogFragment.getTag());

                } else if (status.equals("accepted")) {
                    BottomSheetDialogFragment bottomSheetDialogFragment = new BottomSheetDialogPage(this, handle, shortBio, bio, image, businessDiscussion, businessAdditional, phone, email);
                    bottomSheetDialogFragment.show(getSupportFragmentManager(), bottomSheetDialogFragment.getTag());

                } else {

                    BottomSheetDialogFragment bottomSheetDialogFragment = new BottomSheetDialogPage(this, handle, bio, shortBio, image, businessDiscussion, businessAdditional, 2);
                    bottomSheetDialogFragment.show(getSupportFragmentManager(), bottomSheetDialogFragment.getTag());
                }
                break;
            case R.id.accept:
                new NotificationDialog(this).setOnAcceptClick(requestCode);
                break;
            case R.id.decline:
                new NotificationDialog(this).setOnDeclineClick(requestCode);
                break;
            case R.id.leftbackwanttomeet:
                finish();
                break;

        }

    }

    @Override
    public void setUserInformation(String handle, String shortBio, String bio, String image, String businessDiscussion, String businessAdditional) {


    }

    @Override
    public void getUserProfile(String title, String firstName, String lastName, String handle, String email, String shortBio, String phone, String image, String businessDiscussion, String businessAdditional, String bio) {

        if (firstName == null && lastName == null) {
            this.handle = handle;
        } else {
            this.handle = firstName + " " + lastName;
        }
        this.shortBio = shortBio;
        this.phone = phone;
        this.image = image;
        this.shortBio = shortBio;
        this.businessDiscussion = businessDiscussion;
        this.businessAdditional = businessAdditional;
        this.email = email;
        this.title = title;
        this.bio = bio;

    }

    private void customHeading() {

        companiesTitle = View.inflate(getApplicationContext(), R.layout.tab_custom_companies, null);
        tabTitle = (TextView) companiesTitle.findViewById(R.id.companies_title);
        tabTitle.setTextSize(12);
        tabTitle.setTypeface(typeface);

        mutualTitle = View.inflate(getApplicationContext(), R.layout.tab_custom_mutual, null);
        tabTitle = (TextView) mutualTitle.findViewById(R.id.companies_title);
        tabTitle.setTextSize(12);
        tabTitle.setTypeface(typeface);

        resonTitle = View.inflate(getApplicationContext(), R.layout.tab_custom_reason, null);
        tabTitle = (TextView) resonTitle.findViewById(R.id.companies_title);
        tabTitle.setText("Business");
        tabTitle.setTextSize(12);
        tabTitle.setTypeface(typeface);

    }

    @Override
    public void refreshpage() {
        userDetailsInterface.notificationUser(requestname);
        wnatToMeetAdapter = new WnatToMeetAdapter(getSupportFragmentManager(), meetTab.getTabCount(), requestCode, getIntent().getStringExtra("requestname"), getIntent().getStringExtra("prospectname"), getIntent().getStringExtra("code"));
        meetviewpager.setAdapter(wnatToMeetAdapter);
        meetviewpager.setOffscreenPageLimit(3);
    }

    @Override
    public void onBackPressed() {
        // startActivity(new Intent(getApplicationContext(), NotificationActivity.class).putExtra("flag", getIntent().getIntExtra("flag", 0)).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_ANIMATION));
        super.onBackPressed();
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(receiver, filter);
    }

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getExtras() != null) {
                NetworkInfo ni = (NetworkInfo) intent.getExtras().get(ConnectivityManager.EXTRA_NETWORK_INFO);
                if (ni != null && ni.getState() == NetworkInfo.State.CONNECTED) {
                    Log.d("connectedd", "connected");
                    wnatToMeetAdapter = new WnatToMeetAdapter(getSupportFragmentManager(), meetTab.getTabCount(), requestCode, getIntent().getStringExtra("requestname"), getIntent().getStringExtra("prospectname"), getIntent().getStringExtra("code"));
                    meetviewpager.setAdapter(wnatToMeetAdapter);
                }

            } else {
                Log.d("connectedd", "dadad-----connected");
            }
        }
    };

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(receiver);
    }

    @Override
    public void connectionMessage(String message) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        try {
            NoNetworkConnection.networkMessage(this, "com.root.wishlist.activities.notificationmodule.Wanttomeet");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }
}
