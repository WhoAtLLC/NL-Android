package com.root.wishlist.util.globalValues;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.root.wishlist.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserProfileDialog {
    Context context;
    private CircleImageView profileimage;
    private ImageView crossimage;
    private TextView companyname;
    private TextView companydescription;
    private TextView biotitle;
    private TextView biodecsription;
    private TextView whatidiscusstitle;
    private TextView whatidiscussdescription;

    private String handle;
    private String shortBio;
    private String bio;
    private String image;
    private String businessDiscussion;
    private String businessAdditional;
    private Constants constants;

    public UserProfileDialog(Context context, String handle, String shortBio, String bio, String image, String businessDiscussion, String businessAdditional) {
        this.context = context;
        this.handle = handle;
        this.shortBio = shortBio;
        this.bio = bio;
        this.image = image;
        this.shortBio = shortBio;
        this.businessDiscussion = businessDiscussion;
        this.businessAdditional = businessAdditional;
        constants = new Constants(context);
    }


    public void userDetails() {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.alert_publicprofile);
        int width = (int) (context.getResources().getDisplayMetrics().widthPixels * 0.90);
        int height = (int) (context.getResources().getDisplayMetrics().heightPixels * 0.80);
        dialog.getWindow().setLayout(width, height);
        this.whatidiscussdescription = (TextView) dialog.findViewById(R.id.what_i_discuss_description);
        this.whatidiscusstitle = (TextView) dialog.findViewById(R.id.what_i_discuss_title);
        this.biodecsription = (TextView) dialog.findViewById(R.id.bio_decsription);
        this.biotitle = (TextView) dialog.findViewById(R.id.bio_title);
        this.companydescription = (TextView) dialog.findViewById(R.id.company_description);
        this.companyname = (TextView) dialog.findViewById(R.id.company_name);
        this.crossimage = (ImageView) dialog.findViewById(R.id.cross_image);
        this.profileimage = (CircleImageView) dialog.findViewById(R.id.profile_image);
        Typeface boldFont = Typeface.createFromAsset(context.getAssets(), "Font/Gotham-Medium.otf");
        whatidiscussdescription.setTypeface(boldFont);
        whatidiscusstitle.setTypeface(boldFont);
        biodecsription.setTypeface(boldFont);
        biotitle.setTypeface(boldFont);
        companydescription.setTypeface(boldFont);
        companyname.setTypeface(boldFont);
        companyname.setText(handle);
        Glide.with(context).load(constants.BASE_URL + image).error(R.drawable.wish).into(profileimage);
        whatidiscussdescription.setText(businessDiscussion);
        biodecsription.setText(businessAdditional);
        dialog.show();
        crossimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

    }


}
