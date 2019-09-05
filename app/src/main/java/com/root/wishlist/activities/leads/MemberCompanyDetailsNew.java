package com.root.wishlist.activities.leads;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialogFragment;
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
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.root.wishlist.adapters.MutualContactAdopter;
import com.root.wishlist.pojo.members.MutualContactResult;
import com.root.wishlist.util.globalValues.BottomSheetDialogPage;
import com.root.wishlist.util.globalValues.Constants;
import com.root.wishlist.util.globalValues.NoNetworkConnection;
import com.root.wishlist.util.globalValues.SnackBarMessage;
import com.root.wishlist.util.globalValues.StatusBarTransparent;
import com.root.wishlist.presentation.leads.UserDetailsInterface;
import com.root.wishlist.presentation.leads.UserDetaisPresentation;
import com.root.wishlist.presentation.profile.PrivateProfileInterface;
import com.root.wishlist.presentation.profile.PrivateProfilePresentation;
import com.root.wishlist.R;
import com.root.wishlist.view.PrivateProfileView;
import com.root.wishlist.view.UserInformationView;
import com.root.wishlist.fragment.members.MutualContactsFragment;
import com.root.wishlist.fragment.members.MyWishlistFragment;
import com.root.wishlist.fragment.members.TheirWishlistFragment;
import com.root.wishlist.interfaces.NetworkInterface;

import java.util.AbstractCollection;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;



public class MemberCompanyDetailsNew extends AppCompatActivity implements View.OnClickListener, PrivateProfileView, UserInformationView, NetworkInterface {

    private android.widget.TextView requestTobtn;
    private android.widget.TextView userName;
    private android.widget.TextView conmanydescription;
    public static android.support.design.widget.TabLayout tablayout;
    private android.support.v4.view.ViewPager viewpager;
    ViewpagerAdapter viewpagerAdapter;
    PrivateProfileInterface publicProfileInterface;
    Toolbar toolbar;
    int position = 0;
    String companyname, biodecsription, whatidiscussdescription, privateTitle1, privateProfilePicture;
    private String company;
    private de.hdodenhof.circleimageview.CircleImageView userProfilePic;
    private android.widget.ImageView backarrowimage;
    private LinearLayout myprofileimage;
    private String handle;
    private String shortBio;
    private String bio;
    private String image;
    private String businessDiscussion;
    private String businessAdditional;
    UserDetailsInterface userDetailsInterface;
    private Typeface normalFont;
    private View incomingTitle, outGoingTitle, archiveTitle;
    private TextView tabTitle;
    private TextView leadstxt;
    private TextView notificationtxt;
    private TextView mycontacttct;
    private TextView moretxt;
    private CoordinatorLayout coordinatorLayout;
    private Constants constants;

    private ArrayList<MutualContactResult> mResultArrayList;
    Context context;





    /*public static ArrayList<MutualContactResult> myWishlistArrayList;*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_company_details_new);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        StatusBarTransparent.colorStatusbar(MemberCompanyDetailsNew.this);



        setTitle("");
        normalFont = Typeface.createFromAsset(getAssets(), "Font/Gotham-Book.otf");
        this.myprofileimage = (LinearLayout) findViewById(R.id.userDetails);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.mainlayout);
        this.userProfilePic = (CircleImageView) findViewById(R.id.userProfilePic);
        this.conmanydescription = (TextView) findViewById(R.id.conmany_description);
        this.userName = (TextView) findViewById(R.id.user_name);
        this.requestTobtn = (TextView) findViewById(R.id.request_to_btn);
        this.viewpager = (ViewPager) findViewById(R.id.viewpager);
        this.tablayout = (TabLayout) findViewById(R.id.tablayout);
        requestTobtn.setOnClickListener(this);
        myprofileimage.setOnClickListener(this);

        constants = new Constants(this);

        userDetailsInterface = new UserDetaisPresentation(MemberCompanyDetailsNew.this, getApplicationContext());
        userDetailsInterface.setUser(getIntent().getStringExtra("userName"));
        String demo = getIntent().getStringExtra("short_Bio");
        String demo1 = getIntent().getStringExtra("userName");
        userName.setText(getIntent().getStringExtra("userName"));
        conmanydescription.setText(getIntent().getStringExtra("short_Bio"));
        Glide.with(getApplicationContext()).load(constants.BASE_URL + getIntent().getStringExtra("profileImage")).into(userProfilePic);

       tablayout.addTab(tablayout.newTab().setText("My Wishlist"));
        tablayout.addTab(tablayout.newTab().setText("Their Wishlist"));

          if(MutualContactsFragment.myWishlistArrayList1.size()==0) {

            tablayout.addTab(tablayout.newTab().setText("Mutual Contacts" + 10));
        }
          else
          {
              tablayout.addTab(tablayout.newTab().setText("Mutual Contacts" +MutualContactsFragment.myWishlistArrayList1.size()));
          }

        viewpagerAdapter = new ViewpagerAdapter(getSupportFragmentManager(), tablayout.getTabCount());
        viewpager.setAdapter(viewpagerAdapter);
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
        publicProfileInterface = new PrivateProfilePresentation(MemberCompanyDetailsNew.this, MemberCompanyDetailsNew.this);
        publicProfileInterface.getPrivateUserProfile();


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.userDetails:

                BottomSheetDialogFragment bottomSheetDialogFragment = new BottomSheetDialogPage(getApplicationContext(), handle, bio, shortBio, image, businessDiscussion, businessAdditional, 3);
                bottomSheetDialogFragment.show(getSupportFragmentManager(), bottomSheetDialogFragment.getTag());
                break;
            case R.id.request_to_btn:

                Intent intent = new Intent(getApplicationContext(), MyIntroRequestActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                intent.putExtra("handle", getIntent().getStringExtra("userName"));
                intent.putExtra("connectionname", getIntent().getStringExtra("userName"));
                startActivity(intent);
                break;


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
        //Log.d("bio", bio);
    }

    @Override
    public void getUserProfile(String title, String firstName, String lastName, String handle, String email, String shortBio, String phone, String image, String businessDiscussion, String businessAdditional, String bio) {

    }

    @Override
    public void connectionMessage(String message) {

        SnackBarMessage.displayMessage(conmanydescription, message);
    }

    public class ViewpagerAdapter extends FragmentStatePagerAdapter {

        int tabCount;

        public ViewpagerAdapter(FragmentManager fm, int tabCount) {
            super(fm);
            this.tabCount = tabCount;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    Log.d("dada", getIntent().getStringExtra("leads"));
                    MyWishlistFragment myWishlistFragment = new MyWishlistFragment(userName.getText().toString(), getIntent().getStringExtra("leads"));
                    return myWishlistFragment;
                case 1:
                    TheirWishlistFragment theirWishlistFragment = new TheirWishlistFragment(userName.getText().toString());

                    return theirWishlistFragment;

                case 2:
                    MutualContactsFragment mutualContactsFragment = new MutualContactsFragment(userName.getText().toString());

                    return mutualContactsFragment;

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
    public void setPrivateProfilePicture(String privateProfilePicture1) {
        privateProfilePicture = privateProfilePicture1;
    }

    @Override
    public void setPrivateTitle(String privateTitle) {
        privateTitle1 = privateTitle;
    }

    @Override
    public void setPrivateFirstName(String privateFirstName) {

    }

    @Override
    public void setPrivatetBio(String privatetBio) {
        biodecsription = privatetBio;
    }

    @Override
    public void setPrivateEmail(String privateEmail) {

    }

    @Override
    public void setPrivateComapny(String privateComapny) {

    }

    @Override
    public void setPrivatePhone(String privatePhone) {

    }

    @Override
    public void setPrivateShortBio(String shortBio) {

    }

    @Override
    public void setPrivateBusinessDiscussion(String businessDiscussion) {
        whatidiscussdescription = businessDiscussion;
    }

    @Override
    public void setPrivateBusinessAdditional(String businessAdditional) {

    }

    @Override
    public void setPrivateHandle(String privateHandle) {
        companyname = privateHandle;
    }

    @Override
    public void checknetwork(String connection) {

       // new AlertDialogBox(this).networkMessage(connection);
        try {
            NoNetworkConnection.networkMessage(this, "com.root.wishlist.activities.leads.MemberCompanyDetailsNew");
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
        super.onBackPressed();
        finish();
    }

    private void customHeading() {

        incomingTitle = View.inflate(getApplicationContext(), R.layout.tab_incoming, null);
        tabTitle = (TextView) incomingTitle.findViewById(R.id.companies_title);
        tabTitle.setTextSize(14);
        tabTitle.setText("Incoming");
        tabTitle.setTypeface(normalFont);

        outGoingTitle = View.inflate(getApplicationContext(), R.layout.tab_outgoing, null);
        tabTitle = (TextView) outGoingTitle.findViewById(R.id.companies_title);
        tabTitle.setTextSize(14);
        tabTitle.setText("Outgoing");
        tabTitle.setTypeface(normalFont);
        archiveTitle = View.inflate(getApplicationContext(), R.layout.tabmutual, null);
        tabTitle = (TextView) archiveTitle.findViewById(R.id.companies_title);
        tabTitle.setTextSize(14);
        tabTitle.setText("Archive");
        tabTitle.setTypeface(normalFont);
    }
}
