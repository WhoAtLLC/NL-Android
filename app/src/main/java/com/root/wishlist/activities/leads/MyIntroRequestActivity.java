package com.root.wishlist.activities.leads;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.root.wishlist.activities.NotificationActivity;
import com.root.wishlist.adapters.MyIntroRequestAdapter;
import com.root.wishlist.adapters.MyintroCompaniesAdapter;
import com.root.wishlist.database.SharedDatabase;
import com.root.wishlist.util.globalValues.AlertDialogBox;
import com.root.wishlist.util.globalValues.NoNetworkConnection;
import com.root.wishlist.util.globalValues.StatusBarTransparent;
import com.root.wishlist.presentation.leads.ReasonInterface;
import com.root.wishlist.presentation.leads.ReasonPresentation;
import com.root.wishlist.R;
import com.root.wishlist.fragment.getinteroduceto.MyIntroMutualContact;
import com.root.wishlist.fragment.getinteroduceto.ReasonFragment;
import com.root.wishlist.interfaces.NetworkInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MyIntroRequestActivity extends AppCompatActivity implements MyintroCompaniesAdapter.SelectedCompany, ReasonFragment.ReasonMessage, NetworkInterface {


    private TextView requestto;
    public static TextView requesttobtn;
    private android.support.design.widget.TabLayout requesttotab;
    private android.support.v4.view.ViewPager requesttotabviewpager;
    MyIntroRequestAdapter viewpagerAdapter;
    int position = 0;
    private Toolbar toolbar;
    SharedDatabase sharedDatabase;
    String resonMessage = "", mybusiness = "";
    boolean fill = false;
    List<Integer> companyselected = new ArrayList<>();
    ReasonInterface reasonInterface;
    List<String> excludedmutualcontacts = new ArrayList<>();
    String contactId = "";
    String json = "";
    HashMap<String, Object> resonObjectHashMap = new HashMap<>();
    View resonTitle, companiesTitle, mutualTitle;
    TextView tabTitle;
    Typeface fontFace;
    private CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_intro_request);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.mainlayout);
        fontFace = Typeface.createFromAsset(getAssets(), "Font/Gotham-Book.otf");
        this.requestto = (TextView) findViewById(R.id.request_to);
        requesttobtn = (TextView) findViewById(R.id.request_to_btn);
        requesttobtn.setTypeface(fontFace);
        requestto.setTypeface(fontFace);
        this.requesttotabviewpager = (ViewPager) findViewById(R.id.requesttotab_viewpager);
        this.requesttotab = (TabLayout) findViewById(R.id.requesttotab);
        StatusBarTransparent.colorStatusbar(MyIntroRequestActivity.this);
        sharedDatabase = new SharedDatabase(getApplicationContext());
        /*if (!sharedDatabase.getMyintro()) {
            new YourConnectionCoachmark(this).validationdialog("myinterorequest", true);
        }*/
        customHeading();
        requesttobtn.setText("Send Request");
        requestto.setText("to " + getIntent().getStringExtra("connectionname"));

        //for presentation
        reasonInterface = new ReasonPresentation(getApplicationContext());
        requesttotab.addTab(requesttotab.newTab().setCustomView(resonTitle));
        requesttotab.addTab(requesttotab.newTab().setCustomView(companiesTitle));
        requesttotab.addTab(requesttotab.newTab().setCustomView(mutualTitle));
        viewpagerAdapter = new MyIntroRequestAdapter(getSupportFragmentManager(), requesttotab.getTabCount(), getIntent().getStringExtra("handle"), getIntent().getStringExtra("connectionname"), fill);
        requesttotabviewpager.setOffscreenPageLimit(2);
        requesttotabviewpager.setAdapter(viewpagerAdapter);
        requesttotabviewpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(requesttotab));
        requesttotab.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                position = tab.getPosition();


                requesttotabviewpager.setCurrentItem(position);
                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                requesttobtn.setVisibility(View.VISIBLE);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        requesttobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                requesttobtn.setVisibility(View.VISIBLE);
                if (ReasonFragment.reasonmessage.getText().toString().length() == 0) {

                    ReasonFragment.reasonmessage.setBackgroundResource(R.drawable.edittext_validation_border);
                    ReasonFragment.reasonmessage.requestFocus();
                    new AlertDialogBox(MyIntroRequestActivity.this).networkMessage(" Enter how you can help in return.");
                    // viewpagerAdapter = new MyIntroRequestAdapter(getSupportFragmentManager(), requesttotab.getTabCount(), getIntent().getStringExtra("handle"), fill);
                    requesttotabviewpager.setCurrentItem(0);
                    viewpagerAdapter.notifyDataSetChanged();

                } else if (companyselected.size() == 0) {
                    new AlertDialogBox(MyIntroRequestActivity.this).networkMessage(" Please select at least 1 company to offer help in return.");
                    requesttotabviewpager.setCurrentItem(1);
                } else {
                    resonObjectHashMap.put("companiesofInterest", companyselected);
                    resonObjectHashMap.put("contact", "10539");
                    resonObjectHashMap.put("excludedmutualcontacts", MyIntroMutualContact.hideContact);
                    resonObjectHashMap. put("howIntroReason", mybusiness);
                    resonObjectHashMap.put("recipient", getIntent().getStringExtra("handle"));
                    resonObjectHashMap.put("whyIntroReason", resonMessage);
                    Log.d("dada", String.valueOf(resonObjectHashMap));
                   /* ObjectMapper mapper = new ObjectMapper();
                    try {
                        json = mapper.writeValueAsString(resonObjectHashMap);
                        Log.d("json", json);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }*/
                    reasonInterface.sendReason(resonObjectHashMap);
                    startActivity(new Intent(getApplicationContext(), NotificationActivity.class).putExtra("flag", 0));
                }
            }
        });

    }

    @Override
    public void selectCompany(ArrayList<Integer> companyId) {
        companyselected.addAll(companyId);
        Log.d("dadadadad", String.valueOf(companyselected.size()));
    }

    @Override
    public void resonMessage(String reason, String mybusiness) {
        resonMessage = reason;
        this.mybusiness = mybusiness;
    }

    private void customHeading() {

        companiesTitle = View.inflate(getApplicationContext(), R.layout.tab_custom_companies, null);
        tabTitle = (TextView) companiesTitle.findViewById(R.id.companies_title);
        tabTitle.setTextSize(11);
        tabTitle.setTypeface(fontFace);
        mutualTitle = View.inflate(getApplicationContext(), R.layout.tab_custom_mutual, null);
        tabTitle = (TextView) mutualTitle.findViewById(R.id.companies_title);
        tabTitle.setTextSize(11);
        tabTitle.setTypeface(fontFace);
        resonTitle = View.inflate(getApplicationContext(), R.layout.tab_custom_reason, null);
        tabTitle = (TextView) resonTitle.findViewById(R.id.companies_title);
        tabTitle.setTextSize(11);
        tabTitle.setTypeface(fontFace);
    }


    @Override
    public void connectionMessage(String message) {
        // SnackBarMessage.displayMessage(coordinatorLayout, message);
        try {
            NoNetworkConnection.networkMessage(this, "com.root.wishlist.activities.leads.MyIntroRequestActivity");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }



}
