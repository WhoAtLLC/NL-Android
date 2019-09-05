package com.root.wishlist.util.globalValues;


import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.root.wishlist.R;

import de.hdodenhof.circleimageview.CircleImageView;


public class BottomSheetDialogPage extends BottomSheetDialogFragment {

    Context context;

    private CircleImageView profileimage;
    private ImageView crossimage;
    private TextView companyname;
    private TextView companydescription;
    private TextView biotitle;
    private TextView biodecsription;
    private TextView whatidiscusstitle;
    private TextView whatidiscussdescription;
    private TextView user_email;
    private TextView cell_phone;
    private TextView titleProfile;
    CardView mail_id, phone_id;
    private String handle;
    private String shortBio;
    private String bio;
    private String image;
    private String businessDiscussion;
    private String businessAdditional;
    private String email = null;
    private String phone = null;
    int identity;
    private Constants constants;

    @SuppressLint("ValidFragment")
    public BottomSheetDialogPage() {
        super();
    }

    @SuppressLint("ValidFragment")
    public BottomSheetDialogPage(Context context, String handle, String bio, String shortBio, String image, String businessDiscussion, String businessAdditional, int identity) {
        this.context = context;
        this.handle = handle;
        this.bio = bio;
        this.image = image;
        this.businessDiscussion = businessDiscussion;
        this.businessAdditional = businessAdditional;
        this.shortBio = shortBio;
        this.identity = identity;
        constants = new Constants(context);

    }

    @SuppressLint("ValidFragment")
    public BottomSheetDialogPage(Context context, String handle, String shortBio, String bio, String image, String businessDiscussion, String businessAdditional, String phone, String email) {
        this.context = context;
        this.handle = handle;
        this.shortBio = shortBio;
        this.bio = bio;
        this.image = image;
        this.shortBio = shortBio;
        this.businessDiscussion = businessDiscussion;
        this.businessAdditional = businessAdditional;
        this.phone = phone;
        this.email = email;
     //   Log.d("bio", bio);

    }

    private BottomSheetBehavior.BottomSheetCallback mBottomSheetBehaviorCallback = new BottomSheetBehavior.BottomSheetCallback() {

        @Override
        public void onStateChanged(@NonNull View bottomSheet, int newState) {
            if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                dismiss();
            }

        }

        @Override
        public void onSlide(@NonNull View bottomSheet, float slideOffset) {
        }
    };

    @Override
    public void setupDialog(Dialog dialog, int style) {
        super.setupDialog(dialog, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
        View contentView = View.inflate(getContext(), R.layout.activity_profile_details_dialog, null);
        this.whatidiscussdescription = (TextView) contentView.findViewById(R.id.what_i_discuss_description);
        this.whatidiscusstitle = (TextView) contentView.findViewById(R.id.what_i_discuss_title);
        this.biodecsription = (TextView) contentView.findViewById(R.id.bio_decsription);
        this.biotitle = (TextView) contentView.findViewById(R.id.bio_title);
        this.companydescription = (TextView) contentView.findViewById(R.id.company_description);
        this.companyname = (TextView) contentView.findViewById(R.id.company_name);
        this.titleProfile = (TextView) contentView.findViewById(R.id.title_profile);
        this.crossimage = (ImageView) contentView.findViewById(R.id.cross_image);
        this.profileimage = (CircleImageView) contentView.findViewById(R.id.profile_image);
        user_email = (TextView) contentView.findViewById(R.id.user_email);
        cell_phone = (TextView) contentView.findViewById(R.id.cell_phone);
        phone_id = (CardView) contentView.findViewById(R.id.phone_id);
        mail_id = (CardView) contentView.findViewById(R.id.email_id);
        Typeface boldFont = Typeface.createFromAsset(context.getAssets(), "Font/Gotham-Medium.otf");
        whatidiscusstitle.setTypeface(boldFont);
        biotitle.setTypeface(boldFont);
        companydescription.setTypeface(boldFont);
        companyname.setTypeface(boldFont);
        companyname.setText(handle);
        Typeface medium = Typeface.createFromAsset(context.getAssets(), "Font/Gotham-Book.otf");
        whatidiscussdescription.setTypeface(medium);
        biodecsription.setTypeface(medium);
        cell_phone.setTypeface(medium);
        user_email.setTypeface(medium);
        titleProfile.setTypeface(medium);
        Glide.with(context).load(constants.BASE_URL + image).error(R.drawable.default_user).into(profileimage);
        whatidiscussdescription.setText(businessDiscussion + "\n\n" + businessAdditional);
        biodecsription.setText(bio);
        if (email != null) {
            user_email.setText(email);
            cell_phone.setText(phone);
            companydescription.setVisibility(View.VISIBLE);
            companydescription.setText(shortBio);
            titleProfile.setText("Private Profile");
        } else {
            phone_id.setVisibility(View.GONE);
            mail_id.setVisibility(View.GONE);
            if (identity == 2) {
                companydescription.setVisibility(View.VISIBLE);
                companydescription.setText(shortBio);
            } else if (identity == 3) {
                companydescription.setVisibility(View.VISIBLE);
                companydescription.setText(shortBio);
                whatidiscussdescription.setText(businessDiscussion + "\n\n" + businessAdditional);
                biodecsription.setText(bio);
            }
        }


        dialog.setContentView(contentView);
        dialog.show();
        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) ((View) contentView.getParent()).getLayoutParams();
        CoordinatorLayout.Behavior behavior = params.getBehavior();

        if (behavior != null && behavior instanceof BottomSheetBehavior) {
            ((BottomSheetBehavior) behavior).setBottomSheetCallback(mBottomSheetBehaviorCallback);
        }
    }


}