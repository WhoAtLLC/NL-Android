package com.root.wishlist.util.globalValues;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.facebook.FacebookDialog;
import com.facebook.FacebookSdk;
import com.facebook.applinks.AppLinkData;
import com.facebook.share.model.AppInviteContent;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.AppInviteDialog;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Parameter;
import java.net.URLEncoder;
import java.util.List;

import bolts.AppLinks;

public class InviteFriendMessage {




    public static void sendSms(Context context) {
        Intent sendIntent=new Intent(Intent.ACTION_VIEW);
        sendIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        sendIntent.setData(Uri.parse("sms:"));
        sendIntent.putExtra("sms_body", "Save time networking for new opportunities. Join me on WhoAt's WishList today");
        context.startActivity(sendIntent);
    }

    public static void sendEmail(Context context) {
        Intent intent=new Intent(Intent.ACTION_SENDTO);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_EMAIL, "shiv.prajapati@rootinfosol.com");
        intent.putExtra(Intent.EXTRA_SUBJECT, "Join me on WishList to generate new leads.");
        intent.putExtra(Intent.EXTRA_TEXT, "Save time networking for new opportunities. Join me on WhoAt's WishList today");

        context.startActivity(intent);
    }

    public static void suggestFuture(Context context) {
        Intent intent=new Intent(Intent.ACTION_SENDTO);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"ceo@whoat.io"});
        intent.putExtra(Intent.EXTRA_SUBJECT, "");
        context.startActivity(intent);
    }

    public static void supportMail(Context context) {
        Intent intent=new Intent(Intent.ACTION_SENDTO);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"support@whoat.io"});
        intent.putExtra(Intent.EXTRA_SUBJECT, "");
        context.startActivity(intent);
    }

    public static void inviteFriends(Activity context) {
        String appLinkUrl, previewImageUrl;
        appLinkUrl="https://www.mydomain.com/myapplink";
        previewImageUrl="https://www.mydomain.com/my_invite_image.jpg";

        if (AppInviteDialog.canShow()) {
            AppInviteContent content=new AppInviteContent.Builder()
                    .setApplinkUrl(appLinkUrl)
                    .setPreviewImageUrl(previewImageUrl)
                    .build();
            AppInviteDialog.show(context, content);
        }
    }







}
