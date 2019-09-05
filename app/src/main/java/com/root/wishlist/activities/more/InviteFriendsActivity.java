package com.root.wishlist.activities.more;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookDialog;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.applinks.AppLinkData;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.share.model.AppInviteContent;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.AppInviteDialog;
import com.facebook.share.widget.ShareDialog;
import com.root.wishlist.R;
import com.root.wishlist.activities.LoginActivity;
import com.root.wishlist.activities.MoreActivity;
import com.root.wishlist.util.globalValues.InviteFriendMessage;
import com.root.wishlist.util.globalValues.StatusBarTransparent;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterAuthToken;
import com.twitter.sdk.android.core.TwitterConfig;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterAuthClient;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.List;

import bolts.AppLinks;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.fabric.sdk.android.Fabric;

import static io.fabric.sdk.android.services.network.UrlUtils.urlEncode;


public class InviteFriendsActivity extends AppCompatActivity {

    @BindView(R.id.invite_message)
    TextView inviteMessage;
    @BindView(R.id.text_message)
    TextView textMessage;
    @BindView(R.id.message_invite)
    LinearLayout messageInvite;
    @BindView(R.id.text_email)
    TextView textEmail;

    @BindView(R.id.email_invite)
    LinearLayout emailInvite;

    @BindView(R.id.text_facebook)
    TextView textFacebook;
    @BindView(R.id.facebook_invite)
    LinearLayout facebookInvite;
    @BindView(R.id.text_twitter)
    TextView textTwitter;


    LinearLayout twitterInvite;
    private TwitterAuthClient client;


    @BindView(R.id.title)
    TextView title;
    ShareDialog shareDialog;
    CallbackManager callbackManager;
    static final String TWITTER_CALLBACK_URL="https://developer.twitter.com/en/apps/create";
    private static final String TWITTER_KEY="2MASKx45gvjtJpcZrCZwsRHjn";
    private static final String TWITTER_SECRET="w3QNKBhtFCnAD7V1rYvAs1VfrK4hoGQlMgzCXzAL358zUO7lm8";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(this.getApplicationContext());


        TwitterAuthConfig authConfig=new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        TwitterConfig twitterConfig=new TwitterConfig.Builder(this)
                .twitterAuthConfig(authConfig)
                .build();
        Twitter.initialize(twitterConfig);

        Twitter.initialize(this);

        setContentView(R.layout.activity_invite_friends);
        client=new TwitterAuthClient();
        ButterKnife.bind(this);



        Twitter twitter=Twitter.getInstance();
        Fabric.with(this);
        client=new TwitterAuthClient();
        twitterInvite=findViewById(R.id.twitter_invite);
        shareDialog = new ShareDialog(this);

        callbackManager=CallbackManager.Factory.create();

    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
       if (client != null)
            client.onActivityResult(requestCode, resultCode, data);
                   callbackManager.onActivityResult(requestCode, resultCode, data);

    }


    @OnClick({R.id.message_invite, R.id.email_invite, R.id.facebook_invite, R.id.twitter_invite})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.message_invite:
                InviteFriendMessage.sendSms(getApplicationContext());
                break;
            case R.id.email_invite:
                InviteFriendMessage.sendEmail(getApplicationContext());
                break;

            case R.id.facebook_invite:

                //   LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile", "user_friends", "email", "user_birthday"));

                //  InviteFriendMessage.inviteFriends(InviteFriendsActivity.this);
                ShareLinkContent linkContent=new ShareLinkContent.Builder()
                        /* setQuote("This is For Buisness")*/
                        .setContentUrl(Uri.parse("https://www.wishlist.whoat.net")).build();


                if (ShareDialog.canShow(ShareLinkContent.class)) {
                    shareDialog.show(linkContent);
                }

                Toast.makeText(getApplicationContext(), "Share The Link Of Your App  ", Toast.LENGTH_SHORT).show();
                break;


           case R.id.twitter_invite:



                       client.authorize(InviteFriendsActivity.this, new Callback<TwitterSession>() {
                           @Override
                           public void success(Result<TwitterSession> twitterSessionResult) {

                               TwitterSession session = TwitterCore.getInstance().getSessionManager().getActiveSession();
                               TwitterAuthToken authToken = session.getAuthToken();
                               String token=authToken.token;
                               String secret=authToken.secret;
                             shareTwitter("chl");


                               Toast.makeText(InviteFriendsActivity.this, "success", Toast.LENGTH_SHORT).show();
                           }

                           private void shareTwitter(String message ) {

                               Intent tweetIntent=new Intent(Intent.ACTION_SEND);
                               /*  tweetIntent.putExtra(Intent.EXTRA_TEXT, *//*"This is a Test."*//*);*/
                               tweetIntent.setType("text/plain");

                               PackageManager packManager=getPackageManager();
                               List<ResolveInfo> resolvedInfoList=packManager.queryIntentActivities(tweetIntent, PackageManager.MATCH_DEFAULT_ONLY);

                               boolean resolved=false;
                               for (ResolveInfo resolveInfo : resolvedInfoList) {
                                   if (resolveInfo.activityInfo.packageName.startsWith("com.twitter.android")) {
                                       tweetIntent.setClassName(
                                               resolveInfo.activityInfo.packageName,
                                               resolveInfo.activityInfo.name);
                                       resolved=true;
                                       break;
                                   }
                               }
                               if (resolved) {
                                   startActivity(tweetIntent);
                               } else {
                                   Intent i=new Intent();
                                           i.putExtra(Intent.EXTRA_TEXT, message);
                                   i.setAction(Intent.ACTION_VIEW);
                                   i.setData(Uri.parse("https://twitter.com/intent/tweet?text=" + urlEncode(message)));
                                   startActivity(i);
                                   /* Toast.makeText(this, "Twitter app isn't found", Toast.LENGTH_LONG).show();*/
                               }
                           }
                              private String urlEncode(String s) {
                                   try {
                                       return URLEncoder.encode(s, "UTF-8");
                                   } catch (UnsupportedEncodingException e) {
                                      /* Log.wtf(TAG, "UTF-8 should always be supported", e);*/
                                       return "";
                                   }
                               }




                           @Override
                           public void failure(TwitterException e) {
                               Toast.makeText(InviteFriendsActivity.this, "failure", Toast.LENGTH_SHORT).show();
                           }
                       });






           break;

        }
    }


    public void setFont() {
        Typeface noarmalFont=Typeface.createFromAsset(getAssets(), "Font/Gotham-Book.otf");
        inviteMessage.setTypeface(noarmalFont);
        textMessage.setTypeface(noarmalFont);
        textTwitter.setTypeface(noarmalFont);
        textFacebook.setTypeface(noarmalFont);

        textEmail.setTypeface(noarmalFont);
        title.setTypeface(noarmalFont);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //startActivity(new Intent(getApplicationContext(), MoreActivity.class).setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
        finish();
        overridePendingTransition(0, 0);
    }


}
