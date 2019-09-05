package com.root.wishlist.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.kyleduo.switchbutton.SwitchButton;
import com.root.wishlist.activities.more.InviteFriendsActivity;
import com.root.wishlist.activities.more.ManageGroupsActivity;
import com.root.wishlist.activities.more.NewLeadSheetActivity;
import com.root.wishlist.activities.myaccount.ProfileEditActivity;
import com.root.wishlist.activities.registration.OpenOrPrivateNetwork;
import com.root.wishlist.activities.registration.RegisterationUploadContact;
import com.root.wishlist.activities.registration.YourBusinessInterest;
import com.root.wishlist.database.SharedDatabase;
import com.root.wishlist.presentation.registration.SelectNetworkPresentation;
import com.root.wishlist.presentation.registration.SelectNetworkPresentationInt;
import com.root.wishlist.searvices.ContactFetchSearvice;
import com.root.wishlist.searvices.MyService;
import com.root.wishlist.util.globalValues.InviteFriendMessage;
import com.root.wishlist.util.globalValues.StatusBarTransparent;
import com.root.wishlist.R;
import com.root.wishlist.view.SelectNetworkView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MoreActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int PERMISSIONS_REQUEST_READ_CONTACTS = 1;
    @BindView(R.id.my_leads)
    LinearLayout myLeads;
    @BindView(R.id.my_notifications)
    LinearLayout myNotifications;
    @BindView(R.id.my_account)
    LinearLayout myAccount;
    @BindView(R.id.more)
    LinearLayout more;
    @BindView(R.id.new_leadsheet_txt)
    TextView newLeadsheetTxt;
    @BindView(R.id.new_leadsheet_lyt)
    LinearLayout newLeadsheetLyt;
    @BindView(R.id.invite_yoursales_txt)
    TextView inviteYoursalesTxt;
    @BindView(R.id.invite_yoursales_lyt)
    LinearLayout inviteYoursalesLyt;
    @BindView(R.id.manage_group_txt)
    TextView manageGroupTxt;
    @BindView(R.id.manage_group_lyt)
    LinearLayout manageGroupLyt;
    @BindView(R.id.re_sync_txt)
    TextView reSyncTxt;
    @BindView(R.id.re_sync_lyt)
    LinearLayout reSyncLyt;
    @BindView(R.id.my_wishlist_txt)
    TextView myWishlistTxt;
    @BindView(R.id.my_wishlist_lyt)
    LinearLayout myWishlistLyt;
    @BindView(R.id.public_private_txt)
    TextView publicPrivateTxt;
    @BindView(R.id.public_private_lyt)
    LinearLayout publicPrivateLyt;
    @BindView(R.id.switch1)
    SwitchButton switch1;
    @BindView(R.id.suggest_feature_txt)
    TextView suggestFeatureTxt;
    @BindView(R.id.suggest_feature_lyt)
    LinearLayout suggestFeatureLyt;
    @BindView(R.id.support_txt)
    TextView supportTxt;
    @BindView(R.id.support_lyt)
    LinearLayout supportLyt;
    @BindView(R.id.follow_us_txt)
    TextView followUsTxt;
    @BindView(R.id.follow_us_lyt)
    LinearLayout followUsLyt;

    @BindView(R.id.contactus_float)
     LinearLayout contactusfloat;
    @BindView(R.id.privacy_policy_float)
    LinearLayout privacypolicyfloat;
    @BindView(R.id.terms_service_float)
    LinearLayout termsservicefloat;
    @BindView(R.id.signout_float)
    LinearLayout signoutfloat;

    Intent intent;
    private TextView leadstxt;
    private LinearLayout myleads;
    private TextView notificationtxt;
    private LinearLayout mynotifications;
    private TextView mycontacttct;
    private LinearLayout myaccount;
    private TextView moretxt;


    SharedDatabase sharedDatabase;

    SelectNetworkPresentationInt selectNetworkPresentationInt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);

        this.moretxt = (TextView) findViewById(R.id.more_txt);
        this.mycontacttct = (TextView) findViewById(R.id.mycontact_tct);
        this.notificationtxt = (TextView) findViewById(R.id.notification_txt);
        this.leadstxt = (TextView) findViewById(R.id.leads_txt);
        ButterKnife.bind(this);
        getSupportActionBar().hide();
        StatusBarTransparent.colorStatusbar(MoreActivity.this);
        more.setBackgroundColor(getResources().getColor(R.color.headermenu));
        myNotifications.setBackgroundColor(getResources().getColor(R.color.appColor));
        myAccount.setBackgroundColor(getResources().getColor(R.color.appColor));
        myLeads.setBackgroundColor(getResources().getColor(R.color.appColor));
        setFont();
        sharedDatabase = new SharedDatabase(getApplicationContext());
      // selectNetworkPresentationInt = new SelectNetworkPresentation(MoreActivity.this, getApplicationContext());

        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {

                    sharedDatabase.myBusiness(false);
                    sharedDatabase.setCompanyCoach(false);
                    sharedDatabase.totalCompanylist(false);
                    sharedDatabase.setYourConnectionAt(false);
                    sharedDatabase.setNotification_page(false);
                    sharedDatabase.setMyintro(false);
                    sharedDatabase.setLeadsCompany(false);
                } else {

                }
            }
        });

    }

    @Override
    public void onBackPressed() {
        intent = new Intent(getApplicationContext(), LeadsActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
        overridePendingTransition(0, 0);
        finish();
    }

    @OnClick({R.id.my_leads, R.id.my_notifications, R.id.my_account, R.id.more, R.id.new_leadsheet_lyt, R.id.invite_yoursales_lyt, R.id.manage_group_lyt, R.id.re_sync_lyt, R.id.my_wishlist_lyt, R.id.public_private_lyt, R.id.suggest_feature_lyt, R.id.support_lyt, R.id.follow_us_lyt,R.id.contactus_float,R.id.privacy_policy_float,R.id.terms_service_float,R.id.signout_float})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.my_leads:
                myLeads.setBackgroundColor(getResources().getColor(R.color.headermenu));
                myNotifications.setBackgroundColor(getResources().getColor(R.color.appColor));
                myAccount.setBackgroundColor(getResources().getColor(R.color.appColor));
                more.setBackgroundColor(getResources().getColor(R.color.appColor));
                startActivity(new Intent(getApplicationContext(), LeadsActivity.class).setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
                finish();
                overridePendingTransition(0, 0);
                break;
            case R.id.my_notifications:
                myNotifications.setBackgroundColor(getResources().getColor(R.color.headermenu));
                myLeads.setBackgroundColor(getResources().getColor(R.color.appColor));
                myAccount.setBackgroundColor(getResources().getColor(R.color.appColor));
                more.setBackgroundColor(getResources().getColor(R.color.appColor));
                startActivity(new Intent(getApplicationContext(), NotificationActivity.class).setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
                finish();
                overridePendingTransition(0, 0);
                break;
            case R.id.my_account:
                myAccount.setBackgroundColor(getResources().getColor(R.color.headermenu));
                myNotifications.setBackgroundColor(getResources().getColor(R.color.appColor));
                myLeads.setBackgroundColor(getResources().getColor(R.color.appColor));
                more.setBackgroundColor(getResources().getColor(R.color.appColor));
                startActivity(new Intent(getApplicationContext(), MyAccountActivity.class).setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
                finish();
                overridePendingTransition(0, 0);
                break;
            case R.id.more:
                moreActivity();
                break;

            case R.id.new_leadsheet_lyt:
                intent = new Intent(new Intent(getApplicationContext(), NewLeadSheetActivity.class));
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
                //finish();
                overridePendingTransition(0, 0);
                break;
            case R.id.invite_yoursales_lyt:
                intent = new Intent(new Intent(getApplicationContext(), InviteFriendsActivity.class));
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
                // finish();
                overridePendingTransition(0, 0);
                break;

            case R.id.manage_group_lyt:
                intent=new Intent(this, ManageGroupsActivity.class);
                startActivity(intent);
                overridePendingTransition(0,0);
                break;

            case R.id.re_sync_lyt:
               // uploadContactsService();
                intent = new Intent(this, RegisterationUploadContact.class);
                intent.putExtra("whoseCalling","resyncContacts");
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
                break;
            case R.id.my_wishlist_lyt:
                intent = new Intent(new Intent(getApplicationContext(), YourBusinessInterest.class));
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                intent.putExtra("flagvalue", 1);
                startActivity(intent);
                finish();
                overridePendingTransition(0, 0);
                break;
            case R.id.public_private_lyt:
                intent = new Intent(new Intent(getApplicationContext(), OpenOrPrivateNetwork.class));
                intent.putExtra("flagvalue", 1);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
                finish();
                overridePendingTransition(0, 0);
                break;
            case R.id.suggest_feature_lyt:
                InviteFriendMessage.suggestFuture(getApplicationContext());
                break;
            case R.id.support_lyt:
                InviteFriendMessage.supportMail(getApplicationContext());
                break;
            case R.id.follow_us_lyt:
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/WhoAt_io"));
                startActivity(intent);
                finish();
                overridePendingTransition(0, 0);
                break;

            case R.id.contactus_float:
            //    materialdesignandroidfloatingactionmenu.close(true);
                InviteFriendMessage.supportMail(this);
               // position = 0;
                break;
            case R.id.privacy_policy_float:
                intent = new Intent(getApplicationContext(), PrivacyPolicyActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_ANIMATION);
             //   materialdesignandroidfloatingactionmenu.close(true);
                startActivity(intent);
                overridePendingTransition(0, 0);
             //   position = 0;
                break;
            case R.id.terms_service_float:
                intent = new Intent(getApplicationContext(), TermAndConditionActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_ANIMATION);
          //      materialdesignandroidfloatingactionmenu.close(true);
                startActivity(intent);
                overridePendingTransition(0, 0);
             //   position = 0;
                break;
            case R.id.signout_float:
               logOut();
                break;


        }
    }

    private void moreActivity() {
        more.setBackgroundColor(getResources().getColor(R.color.headermenu));
        myNotifications.setBackgroundColor(getResources().getColor(R.color.appColor));
        myAccount.setBackgroundColor(getResources().getColor(R.color.appColor));
        myLeads.setBackgroundColor(getResources().getColor(R.color.appColor));
        startActivity(new Intent(getApplicationContext(), MoreActivity.class).setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION | Intent.FLAG_ACTIVITY_CLEAR_TASK));
    }

    public void setFont() {
        Typeface noarmalFont = Typeface.createFromAsset(getAssets(), "Font/Gotham-Book.otf");
        newLeadsheetTxt.setTypeface(noarmalFont);
        followUsTxt.setTypeface(noarmalFont);
        supportTxt.setTypeface(noarmalFont);
        suggestFeatureTxt.setTypeface(noarmalFont);
        publicPrivateTxt.setTypeface(noarmalFont);
        myWishlistTxt.setTypeface(noarmalFont);
        reSyncTxt.setTypeface(noarmalFont);
        manageGroupTxt.setTypeface(noarmalFont);
        inviteYoursalesTxt.setTypeface(noarmalFont);
        newLeadsheetTxt.setTypeface(noarmalFont);

        moretxt.setTypeface(noarmalFont);
        mycontacttct.setTypeface(noarmalFont);
        notificationtxt.setTypeface(noarmalFont);
        leadstxt.setTypeface(noarmalFont);
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
    }

    @Override
    protected void onResume() {
        super.onResume();
        more.setBackgroundColor(getResources().getColor(R.color.headermenu));
        myNotifications.setBackgroundColor(getResources().getColor(R.color.appColor));
        myAccount.setBackgroundColor(getResources().getColor(R.color.appColor));
        myLeads.setBackgroundColor(getResources().getColor(R.color.appColor));
    }

    private void logOut() {
        sharedDatabase.saveLeadsCompany(null);
        sharedDatabase.addStep("");
        sharedDatabase.userToken("");
        //selectNetworkPresentationInt.setNetwork("open");
        sharedDatabase.setNetwork("open");
        intent = new Intent(getApplicationContext(), LandingActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
        overridePendingTransition(0, 0);
    }






}










  /* private void uploadContactsService() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, PERMISSIONS_REQUEST_READ_CONTACTS);

        } else {
            // new ContactFetchSearvice(getApplicationContext()).execute();
            new Handler().post(new Runnable() {
                @Override
                public void run() {
                    startService(new Intent(getApplicationContext(), MyService.class));
                }
            });

        }
    }*/

   /* @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        if (requestCode == PERMISSIONS_REQUEST_READ_CONTACTS) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                uploadContactsService();
            } else {
                Toast.makeText(this, "Until you grant the permission, we canot display the names", Toast.LENGTH_SHORT).show();
            }
        }
    }*/