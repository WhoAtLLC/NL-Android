package com.root.wishlist.activities;


import android.animation.Animator;
import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.root.wishlist.R;
import com.root.wishlist.activities.leads.LeadsYourConnectionAtActivity;
import com.root.wishlist.adapters.DashBoardViewPagerAdapter;
import com.root.wishlist.adapters.HotSearcAdapter;
import com.root.wishlist.adapters.SearchAdapter;
import com.root.wishlist.database.CompanyListDatabase;
import com.root.wishlist.database.SharedDatabase;
import com.root.wishlist.fragment.MembersFragment;
import com.root.wishlist.util.globalValues.NoNetworkConnection;
import com.root.wishlist.util.globalValues.SnackBarMessage;
import com.root.wishlist.util.globalValues.StatusBarTransparent;
import com.root.wishlist.util.globalValues.WishlistProgressDialog;
import com.root.wishlist.interfaces.NetworkInterface;
import com.root.wishlist.pojo.search.SearchResult;
import com.root.wishlist.presentation.registration.EmailStatusInterface;
import com.root.wishlist.presentation.registration.SelectNetworkPresentation;
import com.root.wishlist.presentation.registration.SelectNetworkPresentationInt;
import com.root.wishlist.presentation.search.SearchInterface;
import com.root.wishlist.presentation.search.SearchPresentaion;
import com.root.wishlist.view.SearchView;
import com.root.wishlist.view.SelectNetworkView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

public class LeadsActivity extends AppCompatActivity implements View.OnClickListener, SearchView, NetworkInterface, SelectNetworkView {


    private static final int PICK_SMS_REQUEST = 112;
    private android.widget.LinearLayout myleads;
    private android.widget.LinearLayout mynotifications;
    private android.widget.LinearLayout myaccount;
    private android.widget.LinearLayout more;
    private TabLayout tablayout;
    private android.support.v4.view.ViewPager viewpager;
    CompanyListDatabase hotSearchDatabase;
    DashBoardViewPagerAdapter viewpagerAdapter;
    boolean status = false;
    int position = 0;
    Bundle bundle;
    MembersFragment membersFragment;
    public static EmailStatusInterface emailStatusInterface;
    FloatingActionButton floatingActionButton;
    private LinearLayout backLayout;
    private LinearLayout layoutButtons;
    private LinearLayout linearView;
    Animation alphaAnimation;
    float pixelDensity;
    boolean flag = true;
    //for search
    SearchInterface searchInterface;

    private android.widget.ListView searchnewlist;
    private ListView hotSearchListview;
    SearchAdapter leadsCompanyAdapter;
    WishlistProgressDialog wishlistProgressDialog;
    List<SearchResult> searchResults1 = new ArrayList<>();
    List<SearchResult> hotSearchList = new ArrayList<>();
    View companiesHeading, memberHeading;
    TextView companiesTitle;
    Timer timer = new Timer();
    static int count = 0;
    ProgressBar searchProgressBar;
    SharedDatabase sharedDatabase;
    Typeface fontFace;
    private TextView leadstxt;
    private TextView notificationtxt;
    private TextView mycontacttct;
    private TextView moretxt;
    //private me.wangyuwei.loadingview.LoadingView searchProgressBar;
    private FrameLayout frameLayout;
    private LinearLayout mainlayout;
    // private SearchEditText searchCompany;
    private String searchCompanyName = "";
    HotSearcAdapter hotSearcAdapter;
    LinearLayout hot_search_layout;
    private TextView mostRecent;
    List<SearchResult> searchResults;
    CoordinatorLayout coordinatorLayout;
    int lengthText = 0;
    private TextView mostrecent;
    private ListView hotsearchlist;
    private LinearLayout hotsearchlayout, boomerScreen;
    private ImageView publicNetwork;
    //
    SelectNetworkPresentationInt selectNetworkPresentationInt;
    private LinearLayout bummerscreen;
    private android.support.v7.widget.SearchView searchCompany;
    private boolean isClearText = false;
    private int x, y, hypotenuse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_tab);
        //new NotificationServices(this).execute();

        this.searchCompany = (android.support.v7.widget.SearchView) findViewById(R.id.search_Company);

        // searchCompany = (SearchEditText) findViewById(R.id.search_Company);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().hide();
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinate);
        hotSearchDatabase = new CompanyListDatabase(getApplicationContext());
        this.frameLayout = (FrameLayout) findViewById(R.id.frameLayout);
        publicNetwork = (ImageView) findViewById(R.id.publicnetwork);
        boomerScreen = (LinearLayout) findViewById(R.id.bummer_screen);
        this.backLayout = (LinearLayout) findViewById(R.id.back_layout);
        this.searchProgressBar = (ProgressBar) findViewById(R.id.searchProgressBar);
        this.moretxt = (TextView) findViewById(R.id.more_txt);
        mostRecent = (TextView) findViewById(R.id.most_recent);
        this.mycontacttct = (TextView) findViewById(R.id.mycontact_tct);
        this.notificationtxt = (TextView) findViewById(R.id.notification_txt);
        this.leadstxt = (TextView) findViewById(R.id.leads_txt);
        wishlistProgressDialog = new WishlistProgressDialog(this);
        fontFace = Typeface.createFromAsset(getAssets(), "Font/Gotham-Book.otf");
        this.searchnewlist = (ListView) findViewById(R.id.search_new_list);
        hotSearchListview = (ListView) findViewById(R.id.hot_search_list);
        this.linearView = (LinearLayout) findViewById(R.id.linearView);
        this.layoutButtons = (LinearLayout) findViewById(R.id.layoutButtons);
        hot_search_layout = (LinearLayout) findViewById(R.id.hot_search_layout);
        /*searchProgressBar = (ProgressBar) findViewById(R.id.searchProgressBar);
       */
        searchProgressBar.getIndeterminateDrawable().setColorFilter(Color.parseColor("#5B9EEC"), PorterDuff.Mode.MULTIPLY);
        getHotSearchCompanyList();
        // searchCompany.setTypeface(fontFace);
        moretxt.setTypeface(fontFace);
        mycontacttct.setTypeface(fontFace);
        notificationtxt.setTypeface(fontFace);
        mostRecent.setTypeface(fontFace);
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
        selectNetworkPresentationInt = new SelectNetworkPresentation(LeadsActivity.this, getApplicationContext());
        sharedDatabase = new SharedDatabase(getApplicationContext());
        searchInterface = new SearchPresentaion(getApplicationContext(), LeadsActivity.this);
        floatingActionButton = (FloatingActionButton) findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchCompanies(v);
            }
        });
        StatusBarTransparent.colorStatusbar(LeadsActivity.this);
        pixelDensity = getResources().getDisplayMetrics().density;
        alphaAnimation = AnimationUtils.loadAnimation(this, R.anim.alpha_anim);

        this.viewpager = (ViewPager) findViewById(R.id.viewpager);
        this.tablayout = (TabLayout) findViewById(R.id.tablayout);
        this.more = (LinearLayout) findViewById(R.id.more);
        this.myaccount = (LinearLayout) findViewById(R.id.my_account);
        this.mynotifications = (LinearLayout) findViewById(R.id.my_notifications);
        this.myleads = (LinearLayout) findViewById(R.id.my_leads);
        publicNetwork.setOnClickListener(this);
        myleads.setOnClickListener(this);
        mynotifications.setOnClickListener(this);
        myaccount.setOnClickListener(this);
        more.setOnClickListener(this);
        myleads.setBackgroundColor(getResources().getColor(R.color.headermenu));
        mynotifications.setBackgroundColor(getResources().getColor(R.color.appColor));
        myaccount.setBackgroundColor(getResources().getColor(R.color.appColor));
        more.setBackgroundColor(getResources().getColor(R.color.appColor));
        //
        String net = sharedDatabase.getNetwork();
        if (sharedDatabase.getNetwork().equals("private")) {
            boomerScreen.setVisibility(View.VISIBLE);
            frameLayout.setVisibility(View.GONE);
        } else {
            boomerScreen.setVisibility(View.GONE);
            frameLayout.setVisibility(View.VISIBLE);
        }

        //
        tablayout.addTab(tablayout.newTab().setCustomView(companiesHeading));
        tablayout.addTab(tablayout.newTab().setCustomView(memberHeading));
        viewpagerAdapter = new DashBoardViewPagerAdapter(getSupportFragmentManager(), tablayout.getTabCount());
        viewpager.setAdapter(viewpagerAdapter);

        viewpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tablayout));
        tablayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                position = tab.getPosition();
                viewpager.setCurrentItem(position);
                fabSearchShow(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                //Toast.makeText(LeadsActivity.this, "onTabUnselected", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                //Toast.makeText(LeadsActivity.this, "onTabReselected", Toast.LENGTH_SHORT).show();
            }
        });

        //for search
        View closeButton = searchCompany.findViewById(android.support.v7.appcompat.R.id.search_close_btn);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isClearText = true;

                searchCompany.setQuery("", false);
                searchCompany.requestFocus();
                searchResults1.clear();
                searchProgressBar.setVisibility(View.GONE);
                searchProgressBar.setIndeterminate(false);
                hotSearchCompany();
            }
        });

        searchCompany.setOnQueryTextListener(new android.support.v7.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                floatingActionButton.setVisibility(View.VISIBLE);
                lengthText = query.length();
                if (query.length() <= 1) {
                    if (searchResults1.size() != 0) {
                        searchResults1.clear();
                        searchCompanyName = "";
                        leadsCompanyAdapter.notifyDataSetChanged();
                        getHotSearchCompanyList();
                        hot_search_layout.setVisibility(View.VISIBLE);
                        mostRecent.setText("Most Recent Searches");
                        isClearText = true;
                    }

                } else {
                    //wishlistProgressDialog.dialogShow();
                    isClearText = false;
                    searchnewlist.setVisibility(View.VISIBLE);
                    searchCompanyName = query.toString();
                    if (!isClearText) {
                        searchProgressBar.setVisibility(View.VISIBLE);
                       /* searchProgressBar.setIndeterminate(true);
                        searchProgressBar.getProgressDrawable().setColorFilter(
                                Color.parseColor("#FF0000"), PorterDuff.Mode.MULTIPLY);*/
                        searchInterface.findData(query.toString().trim());

                    }

                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {


                return false;
            }
        });

        searchnewlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                hotSearchDatabase.insertHotSearch(searchResults1.get(position).getId(), searchResults1.get(position).getTitle(), searchResults1.get(position).getIcon());
                Intent intent = new Intent(getApplicationContext(), LeadsYourConnectionAtActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                intent.putExtra("userid", searchResults1.get(position).getId());
                intent.putExtra("title", searchResults1.get(position).getTitle());
                String transitionName = getString(R.string.transition);
                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(LeadsActivity.this, view,   // Starting view
                        transitionName    // The String
                );
                ActivityCompat.startActivity(LeadsActivity.this, intent, options.toBundle());
            }
        });

        hotSearchListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), LeadsYourConnectionAtActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                intent.putExtra("userid", hotSearchList.get(position).getId());
                intent.putExtra("title", hotSearchList.get(position).getTitle());
                String transitionName = getString(R.string.transition);
                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(LeadsActivity.this, view,   // Starting view
                        transitionName    // The String
                );
                ActivityCompat.startActivity(LeadsActivity.this, intent, options.toBundle());
            }
        });
    }

    private void hotSearchCompany() {
        searchnewlist.setVisibility(View.VISIBLE);
        hot_search_layout.setVisibility(View.VISIBLE);
        leadsCompanyAdapter = new SearchAdapter(getApplicationContext(), searchResults1);
        searchnewlist.setAdapter(leadsCompanyAdapter);
    }

    //for search
    @Override
    public void getSearchData(List<SearchResult> searchResults) {
        //wishlistProgressDialog.dismissDialog();

        if (isClearText) {

        } else {
            isClearText = false;
            hot_search_layout.setVisibility(View.GONE);
            searchProgressBar.setVisibility(View.GONE);
            searchResults1 = new ArrayList<>();
            searchResults1 = new ArrayList<>();
            this.searchResults = searchResults;
            if (searchResults.size() == 0) {
                hot_search_layout.setVisibility(View.VISIBLE);
                hotSearchListview.setVisibility(View.INVISIBLE);
                mostRecent.setText("Search result not found. Please try a\n different search.");
            } else {
                for (int i = 0; i < searchResults.size(); i++) {
                    String name = searchResults.get(i).getTitle().toLowerCase();
                    if (name.startsWith(searchCompanyName.toLowerCase())) {
                        Log.d("name", name);

                        searchResults1.add(new SearchResult(searchResults.get(i).getId(), searchResults.get(i).getTitle(),
                                searchResults.get(i).getLevel(), searchResults.get(i).getIcon()));
                    } /*else {

                hot_search_layout.setVisibility(View.VISIBLE);
                hotSearchListview.setVisibility(View.INVISIBLE);
                mostRecent.setText("Search result not found. Please try a\n different search.");

            }*/
                }
            }

            Log.d("hhdjajdhja", String.valueOf(lengthText));
            if (lengthText == 0) {
                searchCompany.setQuery("", false);
                searchCompany.clearFocus();
                searchProgressBar.setVisibility(View.GONE);
                searchResults1.clear();


            }
            leadsCompanyAdapter = new SearchAdapter(getApplicationContext(), searchResults1);
            searchnewlist.setAdapter(leadsCompanyAdapter);


        }
    }

    private void customHeading() {

        companiesHeading = View.inflate(getApplicationContext(), R.layout.tab_custom_companies, null);
        companiesTitle = (TextView) companiesHeading.findViewById(R.id.companies_title);
        companiesTitle.setTypeface(fontFace);
        memberHeading = View.inflate(getApplicationContext(), R.layout.tab_custom_member, null);
        companiesTitle = (TextView) memberHeading.findViewById(R.id.companies_title);
        companiesTitle.setTypeface(fontFace);
    }

    private void fabSearchShow(int position) {

        switch (position) {
            case 0:
                floatingActionButton.setVisibility(View.VISIBLE);
                break;
            case 1:
                floatingActionButton.setVisibility(View.GONE);
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
                break;
            case R.id.my_notifications:
                mynotifications.setBackgroundColor(getResources().getColor(R.color.headermenu));
                myleads.setBackgroundColor(getResources().getColor(R.color.appColor));
                myaccount.setBackgroundColor(getResources().getColor(R.color.appColor));
                more.setBackgroundColor(getResources().getColor(R.color.appColor));
                startActivity(new Intent(getApplicationContext(), NotificationActivity.class).setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION | Intent.FLAG_ACTIVITY_CLEAR_TASK));
                finish();
                overridePendingTransition(0, 0);
                break;
            case R.id.my_account:
                myaccount.setBackgroundColor(getResources().getColor(R.color.headermenu));
                mynotifications.setBackgroundColor(getResources().getColor(R.color.appColor));
                myleads.setBackgroundColor(getResources().getColor(R.color.appColor));
                more.setBackgroundColor(getResources().getColor(R.color.appColor));
                startActivity(new Intent(getApplicationContext(), MyAccountActivity.class).setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION | Intent.FLAG_ACTIVITY_CLEAR_TASK));
                finish();
                overridePendingTransition(0, 0);
                break;
            case R.id.more:
                timer.cancel();
                more.setBackgroundColor(getResources().getColor(R.color.headermenu));
                mynotifications.setBackgroundColor(getResources().getColor(R.color.appColor));
                myaccount.setBackgroundColor(getResources().getColor(R.color.appColor));
                myleads.setBackgroundColor(getResources().getColor(R.color.appColor));
                startActivity(new Intent(getApplicationContext(), MoreActivity.class).setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION | Intent.FLAG_ACTIVITY_CLEAR_TASK));
                finish();
                overridePendingTransition(0, 0);
                break;
            case R.id.publicnetwork:
                selectNetworkPresentationInt.setNetwork("open");
                sharedDatabase.setNetwork("open");
                startActivity(new Intent(getApplicationContext(), LeadsActivity.class).setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
                finish();
                break;
        }
    }


    @Override
    public void getmessage(String message) {
        searchProgressBar.setVisibility(View.GONE);
        SnackBarMessage.displayMessage(coordinatorLayout, message);
    }

    boolean isCloseApplication = false;

    @Override
    public void onBackPressed() {
        if (isCloseApplication) {
            super.onBackPressed();
            return;
        }
        closeSearchDialog();
        isCloseApplication = true;
        Toast.makeText(LeadsActivity.this, "Please pressed back again to exit", Toast.LENGTH_SHORT).show();


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                isCloseApplication = false;
            }
        }, 2000);
       /* Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        startActivity(intent);
        overridePendingTransition(0, 0);*/

    }

    //for searching
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void searchCompanies(View view) {
        searchCompany.setIconified(false);
        searchCompany.requestFocus();

        int search_close_btnId = android.support.v7.appcompat.R.id.search_close_btn;
        ImageView search_close_btn = (ImageView) searchCompany.findViewById(search_close_btnId);
        search_close_btn.setImageResource(R.drawable.ic_cancel_black_24dp);
        x = backLayout.getRight();
        y = backLayout.getBottom();
        x -= ((28 * pixelDensity) + (16 * pixelDensity));

        hypotenuse = (int) Math.hypot(backLayout.getWidth(), backLayout.getHeight());

        if (flag) {
            hot_search_layout.setVisibility(View.GONE);
            floatingActionButton.setImageResource(R.drawable.image_cancel);
            FrameLayout.LayoutParams parameters = (FrameLayout.LayoutParams)
                    linearView.getLayoutParams();
            parameters.height = backLayout.getHeight();
            linearView.setLayoutParams(parameters);

            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
                Animator anim = ViewAnimationUtils.createCircularReveal(linearView, x, y, 0, hypotenuse);
                anim.setDuration(700);
                anim.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animator) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animator) {
                        layoutButtons.setVisibility(View.VISIBLE);
                        layoutButtons.startAnimation(alphaAnimation);
                        layoutButtons.setBackgroundColor(Color.parseColor("#FFFFFF"));
                        hotSearchCompany();
                    }

                    @Override
                    public void onAnimationCancel(Animator animator) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animator) {

                    }
                });

                linearView.setVisibility(View.VISIBLE);
                anim.start();
            } else {
                layoutButtons.setVisibility(View.VISIBLE);
                //layoutButtons.startAnimation(alphaAnimation);
                layoutButtons.setBackgroundColor(Color.parseColor("#FFFFFF"));
                linearView.setVisibility(View.VISIBLE);
            }
            flag = false;
        } else {
            closeSearchDialog();
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void closeSearchDialog() {
        searchCompany.setIconified(true);
        floatingActionButton.setImageResource(R.drawable.search);
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
            Animator anim = ViewAnimationUtils.createCircularReveal(linearView, x, y, hypotenuse, 0);
            anim.setDuration(400);

            anim.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animator) {

                }

                @Override
                public void onAnimationEnd(Animator animator) {
                    linearView.setVisibility(View.GONE);
                    layoutButtons.setVisibility(View.GONE);
                }

                @Override
                public void onAnimationCancel(Animator animator) {

                }

                @Override
                public void onAnimationRepeat(Animator animator) {

                }
            });
            anim.start();
        } else {
            linearView.setVisibility(View.GONE);
            layoutButtons.setVisibility(View.GONE);
        }
        flag = true;

    }

    @Override
    protected void onResume() {
        super.onResume();
       /* IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(receiver, filter);*/
        myleads.setBackgroundColor(getResources().getColor(R.color.headermenu));
        mynotifications.setBackgroundColor(getResources().getColor(R.color.appColor));
        myaccount.setBackgroundColor(getResources().getColor(R.color.appColor));
        more.setBackgroundColor(getResources().getColor(R.color.appColor));

        if (sharedDatabase.getIsFlag()) {
            sharedDatabase.addFlag(false);
            viewpager.setCurrentItem(2);
        }

    }


    @Override
    protected void onPause() {
        super.onPause();
        //unregisterReceiver(receiver);
    }

    private void getHotSearchCompanyList() {
        hotSearchList = hotSearchDatabase.getHotSearch();
        Log.d("size", String.valueOf(hotSearchList.size()));
        hotSearcAdapter = new HotSearcAdapter(this, hotSearchList);
        hotSearchListview.setAdapter(hotSearcAdapter);
    }

    @Override
    public void connectionMessage(String message) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        //SnackBarMessage.displayMessage(coordinatorLayout, message);

        NoNetworkConnection.networkMessage(this, "com.root.wishlist.activities.LeadsActivity");

    }

    @Override
    public void navigatToHome(String network) {

    }


}
