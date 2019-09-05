package com.root.wishlist.activities.leads;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.root.wishlist.adapters.GetIntroduceToTabAdapter;
import com.root.wishlist.util.globalValues.BottomSheetDialogPage;
import com.root.wishlist.util.globalValues.NoNetworkConnection;
import com.root.wishlist.util.globalValues.StatusBarTransparent;
import com.root.wishlist.presentation.leads.UserDetailsInterface;
import com.root.wishlist.presentation.leads.UserDetaisPresentation;
import com.root.wishlist.R;
import com.root.wishlist.view.UserInformationView;
import com.root.wishlist.interfaces.NetworkInterface;

import java.util.ArrayList;

public class GetInteroduceToActivity extends AppCompatActivity implements View.OnClickListener, UserInformationView,NetworkInterface {

    private android.widget.TextView requesToBtn;
    private android.widget.TextView messagetext;
    private android.widget.TextView interoduceto;
    private LinearLayout viewprofile;
    private android.support.design.widget.TabLayout getinteroducetotab;
    private android.support.v4.view.ViewPager getintroducetoviewpager;
    GetIntroduceToTabAdapter viewpagerAdapter;
    int position = 0;
    String handler;
    Toolbar toolbar;
    private String handle;
    private String shortBio;
    private String bio;
    private String image;
    private String businessDiscussion;
    private String businessAdditional;
    UserDetailsInterface userDetailsInterface;
    Typeface normalFont;
    private View theirInt, companies, mutualContact;
    private TextView tabTitle;

    //
    private BottomSheetBehavior mBottomSheetBehavior;
    private CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_interoduce_to);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        StatusBarTransparent.colorStatusbar(GetInteroduceToActivity.this);
        init();
        handler = getIntent().getStringExtra("handle");
        userDetailsInterface = new UserDetaisPresentation(GetInteroduceToActivity.this, getApplicationContext());
        userDetailsInterface.setUser(handler);
        interoduceto.setText("" + getIntent().getStringExtra("connectionname") + "\nby " + getIntent().getStringExtra("handle"));
    }

    private void init() {


        customHeading();
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.mainlayout);
        viewprofile = (LinearLayout) toolbar.findViewById(R.id.view_profile);
        this.interoduceto = (TextView) toolbar.findViewById(R.id.interoduce_to);
        this.messagetext = (TextView) findViewById(R.id.message_text);
        requesToBtn = (TextView) findViewById(R.id.request_to_btn);
        this.getintroducetoviewpager = (ViewPager) findViewById(R.id.getintroduceto_viewpager);
        this.getinteroducetotab = (TabLayout) findViewById(R.id.getinteroducetotab);
        messagetext.setText("How can you help " + getIntent().getStringExtra("handle") + " in return?");
        getinteroducetotab.addTab(getinteroducetotab.newTab().setCustomView(theirInt));
        getinteroducetotab.addTab(getinteroducetotab.newTab().setCustomView(companies));
        getinteroducetotab.addTab(getinteroducetotab.newTab().setCustomView(mutualContact));

        requesToBtn.setOnClickListener(this);
        viewpagerAdapter = new GetIntroduceToTabAdapter(getSupportFragmentManager(), getinteroducetotab.getTabCount(), getIntent().getStringExtra("handle"));
        getintroducetoviewpager.setOffscreenPageLimit(2);
        getintroducetoviewpager.setAdapter(viewpagerAdapter);
        getintroducetoviewpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(getinteroducetotab));
        getinteroducetotab.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                position = tab.getPosition();
                getintroducetoviewpager.setCurrentItem(tab.getPosition());
                if (position == 0) {
                    messagetext.setText("How can you help " + handler + "in return?");
                }
                if (position == 1)
                    messagetext.setText("These Companies are interesting to " + handler);
                if (position == 2)
                    messagetext.setText("These are your mutual contacts with " + handler);

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        viewprofile.setOnClickListener(this);

        normalFont = Typeface.createFromAsset(getAssets(), "Font/Gotham-Book.otf");
        messagetext.setTypeface(normalFont);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.view_profile) {
            BottomSheetDialogFragment bottomSheetDialogFragment = new BottomSheetDialogPage(this, handle, bio, shortBio, image, businessDiscussion, businessAdditional, 1);
            bottomSheetDialogFragment.show(getSupportFragmentManager(), bottomSheetDialogFragment.getTag());
            //new UserProfileDialog(this, handle, shortBio, bio, image, businessDiscussion, businessAdditional).userDetails();
        }
        if (view.getId() == R.id.request_to_btn) {

            Intent intent = new Intent(getApplicationContext(), MyIntroRequestActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            intent.putExtra("handle", handler);
            intent.putExtra("connectionname", getIntent().getStringExtra("connectionname"));
            startActivity(intent);
        }
    }

    @Override
    public void setUserInformation(String handle, String shortBio, String bio, String image, String businessDiscussion, String businessAdditional) {
        this.handle = handle;
        this.shortBio = shortBio;
        this.bio = bio;
        this.image = image;
        this.shortBio = shortBio;
        this.businessDiscussion = businessDiscussion;
        this.businessAdditional = businessAdditional;

    }

    @Override
    public void getUserProfile(String title, String firstName, String lastName, String handle, String email, String shortBio, String phone, String image, String businessDiscussion, String businessAdditional, String bio) {

    }

    private void customHeading() {

        theirInt = View.inflate(getApplicationContext(), R.layout.tab_incoming, null);
        tabTitle = (TextView) theirInt.findViewById(R.id.companies_title);
        tabTitle.setTextSize(12);
        tabTitle.setText("Their Interest");
        tabTitle.setTypeface(normalFont);


        companies = View.inflate(getApplicationContext(), R.layout.tab_outgoing, null);
        tabTitle = (TextView) companies.findViewById(R.id.companies_title);
        tabTitle.setTextSize(12);
        tabTitle.setText("Companies");
        tabTitle.setTypeface(normalFont);

        mutualContact = View.inflate(getApplicationContext(), R.layout.tab_archive, null);
        tabTitle = (TextView) mutualContact.findViewById(R.id.companies_title);
        tabTitle.setTextSize(12);
        tabTitle.setText("Mutual Contacts");
        tabTitle.setTypeface(normalFont);
    }

    @Override
    public void connectionMessage(String message) {
        try {
            NoNetworkConnection.networkMessage(this, "com.root.wishlist.activities.aeads.GetInteroduceToActivity");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }
}
