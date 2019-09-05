package com.root.wishlist.activities.registration;

import android.animation.Animator;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.root.wishlist.R;
import com.root.wishlist.activities.LeadsActivity;
import com.root.wishlist.activities.MoreActivity;
import com.root.wishlist.adapters.SearchAdapter;
import com.root.wishlist.adapters.YourBussinessViewPagerAdapter;
import com.root.wishlist.database.CompanyListDatabase;
import com.root.wishlist.database.SharedDatabase;
import com.root.wishlist.fragment.CompaniesInterest;
import com.root.wishlist.fragment.MyBusiness;
import com.root.wishlist.fragment.TotalSelectedCompanies;
import com.root.wishlist.util.globalValues.AlertDialogBox;
import com.root.wishlist.util.globalValues.GlobalClass;
import com.root.wishlist.util.globalValues.NoNetworkConnection;
import com.root.wishlist.util.globalValues.StatusBarTransparent;
import com.root.wishlist.util.globalValues.coachmark.YourConnectionCoachmark;
import com.root.wishlist.interfaces.UpdateNumberOfItems;
import com.root.wishlist.pojo.mywishlist.Result;
import com.root.wishlist.pojo.search.SearchResult;
import com.root.wishlist.presentation.registration.CompaniesOfInterestInterface;
import com.root.wishlist.presentation.registration.MyCompanyOfInterestPresentation;
import com.root.wishlist.presentation.registration.MyWishListPresentationint;
import com.root.wishlist.presentation.registration.MyWishListpresentation;
import com.root.wishlist.presentation.search.SearchInterface;
import com.root.wishlist.presentation.search.SearchPresentaion;
import com.root.wishlist.view.MyWishListView;
import com.root.wishlist.view.SearchView;
import com.squareup.picasso.Downloader;

//import org.codehaus.jackson.map.ObjectMapper;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class YourBusinessInterest extends AppCompatActivity implements View.OnClickListener, UpdateNumberOfItems, MyWishListView, SearchView {

    public static android.support.v4.view.ViewPager viewpager;
    //for arrow
    public static LinearLayout mybusinessArrow;
    public static LinearLayout companyArrow;
    // private LinearLayout totalArrow;
    public static List<Integer> totalselectedCompany=new ArrayList<>();
    ImageView backarrowimage;
    LinearLayout heighlightIcon;
    CompanyListDatabase companyListDatabase;
    int flagvalu=0;
    int counter=0;
    YourBussinessViewPagerAdapter yourBussinessViewPagerAdapter;
    View v;
    TextView textView;
    //for anim
    Animation alphaAnimation;
    float pixelDensity;
    boolean flag=true;
    FloatingActionButton floatingActionButton;
    ImageView right_arrow, company_right_arrow, company_left_arrow, toalleft_arrowleft_arrow;
    TextView done;
    SharedDatabase sharedDatabase;
    HashMap<String, Object> wishListCompany=new HashMap<>();
    MyWishListPresentationint mywishListinterface;
    ArrayList<Result> companyDetails=new ArrayList<>();
    SearchAdapter leadsCompanyAdapter;
    List<SearchResult> searchResults1=new ArrayList<>();
    SearchInterface searchInterface;
    CompaniesOfInterestInterface companiesOfInterestInterface;
    private TabLayout tablayout;
    private LinearLayout backlayout;
    private android.support.v7.widget.SearchView searchCompany;
    private android.widget.ListView searchnewlist;
    private LinearLayout backLayout;
    private LinearLayout layoutButtons;
    private LinearLayout linearView;
    private LinearLayout /*companyArrow,*/ totalArrow;
    private ProgressBar searchProgressBar;
    private TextView updateTxt;
    private String searchCompanyName="";
    private boolean isClickable=true;

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_your_business_interest);

        Toolbar toolbar=(Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        StatusBarTransparent.colorStatusbar(YourBusinessInterest.this);
        // setContentView(R.layout.activity_your_business_interest);
        sharedDatabase=new SharedDatabase(getApplicationContext());



        initial();

        companiesOfInterestInterface=new MyCompanyOfInterestPresentation(getApplicationContext(), YourBusinessInterest.this);
        searchInterface=new SearchPresentaion(getApplicationContext(), YourBusinessInterest.this);

        mywishListinterface=new MyWishListpresentation(YourBusinessInterest.this, getApplicationContext());
        pixelDensity=getResources().getDisplayMetrics().density;
        alphaAnimation=AnimationUtils.loadAnimation(this, R.anim.alpha_anim);

        flagvalu=getIntent().getIntExtra("flagvalue", 0);
        v=View.inflate(getApplicationContext(), R.layout.tab_custom_view, null);
        textView=(TextView) v.findViewById(R.id.title);
        companyListDatabase=new CompanyListDatabase(YourBusinessInterest.this);
        if (flagvalu == 1 || flagvalu == 2) {
            companyListDatabase.deletetable();
            heighlightIcon.setVisibility(View.GONE);


            companyArrow.setVisibility(View.VISIBLE);

            mywishListinterface.companiesOfInterest();
        }
        counter=companyListDatabase.totalCounter();
        textView.setText("" + counter);

        tablayout.addTab(tablayout.newTab().setText("My Business"));
        tablayout.addTab(tablayout.newTab().setText("Companies of interest"));
        tablayout.addTab(tablayout.newTab().setCustomView(v));


        // yourBussinessViewPagerAdapter = new YourBussinessViewPagerAdapter(getSupportFragmentManager(), tablayout.getTabCount(), flagvalu);
        yourBussinessViewPagerAdapter=new YourBussinessViewPagerAdapter(getSupportFragmentManager(), tablayout.getTabCount());
        viewpager.setOffscreenPageLimit(0);
        tablayout.setEnabled(false);
        viewpager.setAdapter(yourBussinessViewPagerAdapter);
        viewpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tablayout));
        tablayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                System.out.println("dadadada" + tab.getPosition());
                showSearchFab(tab.getPosition());
                viewpager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        viewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                final InputMethodManager imm=(InputMethodManager) getSystemService(
                        Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(viewpager.getWindowToken(), 0);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        LinearLayout tabStrip=((LinearLayout) tablayout.getChildAt(0));
        for (int i=0; i < tabStrip.getChildCount(); i++) {
            tabStrip.getChildAt(i).setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    return true;
                }
            });
        }

        //
        //for search

        View closeButton=searchCompany.findViewById(android.support.v7.appcompat.R.id.search_close_btn);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchCompany.setQuery("", false);
                searchCompany.requestFocus();
                searchResults1.clear();
                searchProgressBar.setVisibility(View.GONE);
                searchnewlist.setVisibility(View.GONE);
                searchProgressBar.setIndeterminate(false);
                leadsCompanyAdapter=new SearchAdapter(getApplicationContext(), searchResults1);
                searchnewlist.setAdapter(leadsCompanyAdapter);
            }
        });

        searchCompany.setOnQueryTextListener(new android.support.v7.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                floatingActionButton.setVisibility(View.VISIBLE);
                if (query.length() <= 1) {
                    if (searchResults1.size() != 0) {
                        searchResults1.clear();
                        searchCompanyName="";
                        leadsCompanyAdapter.notifyDataSetChanged();

                    }

                } else {

                    searchnewlist.setVisibility(View.VISIBLE);
                    searchCompanyName=query.toString();
                    searchProgressBar.setVisibility(View.VISIBLE);
                    searchProgressBar.setIndeterminate(true);
                    searchProgressBar.getProgressDrawable().setColorFilter(
                            Color.parseColor("#FF9700"), android.graphics.PorterDuff.Mode.SRC_IN);
                    searchInterface.findData(query.toString().trim());

                }
                return false;
            }


            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }


        });

/*        searchCompany.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() <= 1) {
                    if (searchResults1.size() != 0) {
                        searchResults1.clear();
                        searchCompanyName = "";
                        leadsCompanyAdapter.notifyDataSetChanged();
                    }

                } else {
                    // wishlistProgressDialog.dialogShow();
                    searchProgressBar.setVisibility(View.VISIBLE);
                    searchProgressBar.setIndeterminate(true);
                    searchCompanyName = s.toString();
                    searchInterface.findData(s.toString());


                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        }); searchCompany.setKeyImeChangeListener(new MybusinessEdittext.KeyImeChange() {
            @Override
            public void onKeyIme(int keyCode, KeyEvent event) {
                if (KeyEvent.KEYCODE_BACK == event.getKeyCode()) {
                    linearView.setVisibility(View.GONE);
                    floatingActionButton.setImageResource(R.drawable.search);
                }
            }
        });*/

        searchnewlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                companyListDatabase.insertCounterCompanyTable(searchResults1.get(position).getId(), searchResults1.get(position).getTitle(), searchResults1.get(position).getIcon());
             /*   Intent intent = new Intent(getApplicationContext(),YourBusinessInterest.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);*/
                searchResults1.remove(position);
                counter=companyListDatabase.totalCounter();
                textView.setText("" + counter);
                leadsCompanyAdapter.notifyDataSetChanged();

               /* String transitionName = getString(R.string.transition);
                ActivityOptionsCompat options=ActivityOptionsCompat.makeSceneTransitionAnimation(YourBusinessInterest.this, view, transitionName   // Starting view
                        // The String
                );

                ActivityCompat.startActivity(YourBusinessInterest.this, intent, options.toBundle());*/

            }
        });

    }

    private void showSearchFab(int position) {

        switch (position) {
            case 0:
                floatingActionButton.setVisibility(View.GONE);
                totalArrow.setVisibility(View.GONE);
              /*  companyArrow.setVisibility(View.GONE);
                mybusinessArrow.setVisibility(View.VISIBLE);*/
                company_left_arrow.setVisibility(View.VISIBLE);
                company_right_arrow.setVisibility(View.VISIBLE);
                break;
            case 1:

                if (!sharedDatabase.getCompanylistCoach())
                    new YourConnectionCoachmark(this).validationdialog("companylist", true);
                mybusinessArrow.setVisibility(View.GONE);
                floatingActionButton.setVisibility(View.VISIBLE);
                totalArrow.setVisibility(View.GONE);
                companyArrow.setVisibility(View.VISIBLE);
                CompaniesInterest comP1=(CompaniesInterest) viewpager.getAdapter().instantiateItem(viewpager, viewpager.getCurrentItem());
                comP1.refreshpage();
                break;
            case 2:

                if (!sharedDatabase.gettotalCompanylist())
                    new YourConnectionCoachmark(this).validationdialog("totalCompanylist", true);

                new TotalSelectedCompanies().more_value=1;
                floatingActionButton.setVisibility(View.GONE);
                totalArrow.setVisibility(View.VISIBLE);
                companyArrow.setVisibility(View.GONE);
                mybusinessArrow.setVisibility(View.GONE);
                TotalSelectedCompanies toTal=(TotalSelectedCompanies) viewpager.getAdapter().instantiateItem(viewpager, viewpager.getCurrentItem());
                toTal.refreshpage();
                //viewpager.setCurrentItem(2);
                //yourBussinessViewPagerAdapter.notifyDataSetChanged();

        }
    }

    private void initial() {

        this.searchProgressBar=(ProgressBar) findViewById(R.id.searchProgressBar);
        backarrowimage=(ImageView) findViewById(R.id.back_arrow_image);
        searchCompany=(android.support.v7.widget.SearchView) findViewById(R.id.search_Company);
        heighlightIcon=(LinearLayout) findViewById(R.id.heighlight_icon);
        viewpager=(ViewPager) findViewById(R.id.viewpager);
        this.tablayout=(TabLayout) findViewById(R.id.tablayout);
        backarrowimage.setOnClickListener(this);

        this.searchnewlist=(ListView) findViewById(R.id.search_new_list);
        this.linearView=(LinearLayout) findViewById(R.id.linearView);
        this.layoutButtons=(LinearLayout) findViewById(R.id.layoutButtons);
        this.backLayout=(LinearLayout) findViewById(R.id.back_layout);

        this.totalArrow=(LinearLayout) findViewById(R.id.total_arrow);
        this.companyArrow=(LinearLayout) findViewById(R.id.company_arrow);
        mybusinessArrow=(LinearLayout) findViewById(R.id.mybusiness_arrow);

        floatingActionButton=(FloatingActionButton) findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fabAnimation(v);
            }
        });

        right_arrow=(ImageView) findViewById(R.id.right_arrow);
        company_right_arrow=(ImageView) findViewById(R.id.company_right_arrow);
        company_left_arrow=(ImageView) findViewById(R.id.company_left_arrow);
        toalleft_arrowleft_arrow=(ImageView) findViewById(R.id.toalleft_arrowleft_arrow);
        done=(TextView) findViewById(R.id.done);

        done.setOnClickListener(this);
        right_arrow.setOnClickListener(this);
        company_right_arrow.setOnClickListener(this);
        company_left_arrow.setOnClickListener(this);
        toalleft_arrowleft_arrow.setOnClickListener(this);

        totalArrow.setOnClickListener(this);
        companyArrow.setOnClickListener(this);
        mybusinessArrow.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {


        switch (view.getId()) {
            case R.id.back_arrow_image:
                finish();
                break;
            case R.id.right_arrow:
                viewpager.setCurrentItem(1);
                break;
            case R.id.company_right_arrow:
                if (tablayout.getSelectedTabPosition() == 1)
                    viewpager.setCurrentItem(0);
                else if (flagvalu == 1 || flagvalu == 2) {
                    if (isClickable) {
                        isClickable=false;
                        checkMybusiness();
                    }
                } else {
                    checkMybusiness();
                }
                break;
            case R.id.company_left_arrow:
                if (tablayout.getSelectedTabPosition() == 1)
                    viewpager.setCurrentItem(2);
                else
                    viewpager.setCurrentItem(1);
                break;
            case R.id.toalleft_arrowleft_arrow:
                viewpager.setCurrentItem(1);
                break;
            case R.id.mybusiness_arrow:
                viewpager.setVisibility(View.GONE);
                break;
            case R.id.done:
                if (flagvalu == 1 || flagvalu == 2) {
                    if (isClickable) {
                        isClickable=false;
                        checkMybusiness();

                    }
                } else {
                    checkMybusiness();
                }
                break;

        }

    }

    @Override
    public void updateCounter() {
        counter=companyListDatabase.totalCounter();
        textView.setText("" + counter);
        viewpager.getAdapter().notifyDataSetChanged();
    }


    @Override
    protected void onResume() {
        super.onResume();

    }
    //

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void fabAnimation(View view) {
        searchCompany.setIconified(false);
        searchCompany.requestFocus();
        int search_close_btnId=android.support.v7.appcompat.R.id.search_close_btn;
        ImageView search_close_btn=(ImageView) searchCompany.findViewById(search_close_btnId);
        search_close_btn.setImageResource(R.drawable.ic_cancel_black_24dp);
        int x=backLayout.getRight();
        int y=backLayout.getBottom();
        x-=((28 * pixelDensity) + (16 * pixelDensity));

        int hypotenuse=(int) Math.hypot(backLayout.getWidth(), backLayout.getHeight());

        if (flag) {
            floatingActionButton.setImageResource(R.drawable.image_cancel);
            FrameLayout.LayoutParams parameters=(FrameLayout.LayoutParams)
                    linearView.getLayoutParams();
            parameters.height=backLayout.getHeight();
            linearView.setLayoutParams(parameters);
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {


                Animator anim=ViewAnimationUtils.createCircularReveal(linearView, x, y, 0, hypotenuse);
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

            flag=false;
        } else {
            final InputMethodManager imm=(InputMethodManager) getSystemService(
                    Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            floatingActionButton.setImageResource(R.drawable.search);
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
                Animator anim=ViewAnimationUtils.createCircularReveal(linearView, x, y, hypotenuse, 0);
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
            flag=true;
        }
    }

    //

    private void checkMybusiness() {
        String mybusinessdata=sharedDatabase.getMyBusinessMessage();
        if (mybusinessdata == null || mybusinessdata.length() == 0) {
            viewpager.setCurrentItem(0);
            MyBusiness.message1ext.setBackgroundResource(R.drawable.edittext_validation_border);
        } else if (counter == 0) {
            viewpager.setCurrentItem(1);
            new AlertDialogBox(this).networkMessage(" Select at least 1 company you'd like an intro to.");

        } else {
            uploadInterestcompany();
        }
    }

    //for device id
    public String getDeviceUDID() {
        String android_id=Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);
        return android_id;
    }

    public String getDevicename() {
        String devicename=android.os.Build.MODEL;
        return devicename;
    }

    @Override
    public void companiesofInterest(List<Result> companiesofInterest, String myBusinessDiscussion, String myBusinessOtherInfo) {

        new MyBusiness().message1ext.setText(myBusinessDiscussion);
        new MyBusiness().message2edt.setText(myBusinessOtherInfo);
        for (int i=0; i < companiesofInterest.size(); i++) {
            companyListDatabase.insertCounterCompanyTable(companiesofInterest.get(i).getId(), companiesofInterest.get(i).getTitle(), companiesofInterest.get(i).getProfileImageUrl());
        }
        CompaniesInterest.totalSelectedComapny=companyListDatabase.getCounterCompany();
        counter=companyListDatabase.totalCounter();
        textView.setText("" + counter);


    }

    @Override
    public void navigateToNext(String next) {
        if (next.equals("Created")) {
            sharedDatabase.addStep("OpenOrPrivateNetwork");
            Intent intent=new Intent(getApplicationContext(), OpenOrPrivateNetwork.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);
            finish();
            overridePendingTransition(0, 0);
        }
        if (next.equals("Partial Content")) {
            callBack();
        }

    }

    @Override
    public void networkError(String connection) {
        //new AlertDialogBox(this).networkMessage(connection);
        try {
            NoNetworkConnection.networkMessage(this, "com.root.wishlist.activities.registration.YourBusinessInterest");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onBackPressed() {
        callBack();
    }

    private void callBack() {
        if (flagvalu == 1) {
            Intent intent=new Intent(new Intent(getApplicationContext(), MoreActivity.class));
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);
            finish();
            overridePendingTransition(0, 0);
        }
        if (flagvalu == 2) {
            sharedDatabase.addFlag(true);
            Intent intent=new Intent(new Intent(getApplicationContext(), LeadsActivity.class));
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);
            finish();
            overridePendingTransition(0, 0);
        }
    }

    @Override
    public void getSearchData(List<SearchResult> searchResults) {
        searchProgressBar.setVisibility(View.GONE);
        searchProgressBar.setIndeterminate(false);
        searchResults1=new ArrayList<>();
        //searchResults1 = searchResults;
        for (int i=0; i < searchResults.size(); i++) {
            String name=searchResults.get(i).getTitle().toLowerCase();
            if (name.startsWith(searchCompanyName.toLowerCase())) {
                Log.d("name", name);

                searchResults1.add(new SearchResult(searchResults.get(i).getId(), searchResults.get(i).getTitle(),
                        searchResults.get(i).getLevel(), searchResults.get(i).getIcon()));
            }
        }
        leadsCompanyAdapter=new SearchAdapter(getApplicationContext(), searchResults1);
        searchnewlist.setAdapter(leadsCompanyAdapter);

    }

    @Override
    public void getmessage(String message) {
        //wishlistProgressDialog.dismissDialog();
    }

    void uploadInterestcompany() {
        companyListDatabase=new CompanyListDatabase(getApplicationContext());
        companyDetails=companyListDatabase.getCounterCompany();
        for (int i=0; i < companyDetails.size(); i++) {
            totalselectedCompany.add(companyDetails.get(i).getId());
        }

        List<HashMap<String, String>> hashMaps=new ArrayList<>();
        HashMap<String, String> stringStringHashMap=new HashMap<>();
        stringStringHashMap.put("device_type", getDeviceUDID());
        stringStringHashMap.put("udid", getDevicename());
        hashMaps.add(stringStringHashMap);
        wishListCompany.put("companiesofInterest", totalselectedCompany);
        wishListCompany.put("devices", stringStringHashMap);
        wishListCompany.put("myBusinessDiscussion", MyBusiness.message1ext.getText().toString());
        wishListCompany.put("myBusinessOtherInfo", MyBusiness.message2edt.getText().toString());
        //for api
        if (flagvalu == 1 || flagvalu == 2) {
            mywishListinterface.myWishlistCompany(wishListCompany);
        } else {
           /* ObjectMapper mapper = new ObjectMapper();
            try {
                String json = mapper.writeValueAsString(wishListCompany);
                Log.d("json", json);
            } catch (Exception e) {
                e.printStackTrace();
            }*/
            companiesOfInterestInterface.myWishListInterest(wishListCompany);
            //mywishListinterface.myWishListInterest(wishListCompany);


        }
    }


}