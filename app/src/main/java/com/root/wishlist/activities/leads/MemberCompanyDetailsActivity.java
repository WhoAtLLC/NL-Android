package com.root.wishlist.activities.leads;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.root.wishlist.adapters.MutualContactAdopter;
import com.root.wishlist.pojo.members.MutualContactResult;
import com.root.wishlist.util.globalValues.Constants;
import com.root.wishlist.util.globalValues.NoNetworkConnection;
import com.root.wishlist.util.globalValues.PublicProfileDialog;
import com.root.wishlist.pojo.leads.ResultLeads;
import com.root.wishlist.presentation.profile.PrivateProfileInterface;
import com.root.wishlist.presentation.profile.PrivateProfilePresentation;
import com.root.wishlist.R;
import com.root.wishlist.view.PrivateProfileView;
import com.root.wishlist.fragment.members.MutualContactsFragment;
import com.root.wishlist.fragment.members.MyWishlistFragment;
import com.root.wishlist.fragment.members.TheirWishlistFragment;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;


public class MemberCompanyDetailsActivity extends AppCompatActivity implements View.OnClickListener, PrivateProfileView {

    private android.widget.TextView userName;
    private android.widget.TextView conmanydescription;
    private android.support.design.widget.TabLayout tablayout;
    private android.support.v4.view.ViewPager viewpager;
    ViewpagerAdapter viewpagerAdapter;
    PrivateProfileInterface publicProfileInterface;
    public static ArrayList<ResultLeads> companyLeadses = new ArrayList<>();
    int position = 0;
    String companyname, biodecsription, whatidiscussdescription, privateTitle1, privateProfilePicture;
    private String company;
    int arrayListPosition = 0;
    private de.hdodenhof.circleimageview.CircleImageView userProfilePic;
    private android.widget.ImageView backarrowimage;
    private ImageView myprofileimage;
    PublicProfileDialog publicProfileDialog;
    private com.github.clans.fab.FloatingActionButton requesttomeet;
    private com.github.clans.fab.FloatingActionMenu materialdesignandroidfloatingactionmenu;
    private Constants constants;
private ArrayList<MutualContactResult> mResultArrayList;
public static ArrayList<MutualContactResult> myWishlistArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_company_info);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        this.materialdesignandroidfloatingactionmenu = (FloatingActionMenu) findViewById(R.id.material_design_android_floating_action_menu);
        this.requesttomeet = (FloatingActionButton) findViewById(R.id.request_to_meet);
        this.myprofileimage = (ImageView) findViewById(R.id.myprofile_image);
        this.backarrowimage = (ImageView) findViewById(R.id.back_arrow_image);
        this.userProfilePic = (CircleImageView) findViewById(R.id.userProfilePic);
        this.conmanydescription = (TextView) findViewById(R.id.conmany_description);
        this.userName = (TextView) findViewById(R.id.user_name);
        this.viewpager = (ViewPager) findViewById(R.id.viewpager);
        this.tablayout = (TabLayout) findViewById(R.id.tablayout);

        constants = new Constants(this);
        // StatusBarTransparent.colorStatusbar(MemberCompanyDetailsActivity.this);
        requesttomeet.setOnClickListener(this);



        String demo = getIntent().getStringExtra("short_Bio");
        String demo1 = getIntent().getStringExtra("userName");
        userName.setText(getIntent().getStringExtra("userName"));
        conmanydescription.setText(getIntent().getStringExtra("short_Bio"));
        Glide.with(getApplicationContext()).load(constants.BASE_URL + getIntent().getStringExtra("profileImage")).into(userProfilePic);

        tablayout.addTab(tablayout.newTab().setText("My Wishlist"));
        tablayout.addTab(tablayout.newTab().setText("Their Wishlist"));
        tablayout.addTab(tablayout.newTab().setText("Mutual Contacts"));


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
        publicProfileInterface = new PrivateProfilePresentation(MemberCompanyDetailsActivity.this, MemberCompanyDetailsActivity.this);
        publicProfileInterface.getPrivateUserProfile();
        backarrowimage.setOnClickListener(this);
        myprofileimage.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_arrow_image:
                finish();
                break;
            case R.id.myprofile_image:
                publicProfileDialog = new PublicProfileDialog(this, companyname, biodecsription, whatidiscussdescription, privateTitle1, privateProfilePicture);
                publicProfileDialog.publicProfileDialog();
                break;
            case R.id.request_to_meet:
                Intent intent = new Intent(getApplicationContext(), MyIntroRequestActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                intent.putExtra("handle", getIntent().getStringExtra("userName"));
                startActivity(intent);
                break;
        }
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
                    MyWishlistFragment myWishlistFragment = new MyWishlistFragment(userName.getText().toString(), company);
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

        //new AlertDialogBox(this).networkMessage(connection);
        try {
            NoNetworkConnection.networkMessage(this, "com.root.wishlist.Activities.Leads.MemberCompanyDetailsActivity");
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

}
