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


public class PublicProfileDialog {

    private Context mContext;
    String companynames, biodecsriptions, whatidiscussdescriptions, privateTitle1, privateProfilePicture;
    private de.hdodenhof.circleimageview.CircleImageView profileimage;
    private TextView companyname;
    private TextView companydescription;
    private TextView biotitle;
    private TextView biodecsription;
    private TextView whatidiscusstitle;
    private TextView whatidiscussdescription;
    private ImageView editprofile;
    private Constants constants;

    public PublicProfileDialog(Context context, String companyname, String biodecsription1, String whatidiscussdescription, String privateTitle1, String privateProfilePicture) {
        this.mContext = context;
        this.companynames = companyname;
        this.biodecsriptions = biodecsription1;
        this.whatidiscussdescriptions = whatidiscussdescription;
        this.privateTitle1 = privateTitle1;
        this.privateProfilePicture = privateProfilePicture;
        constants=new Constants(mContext);
    }

    public void publicProfileDialog() {
        final Dialog dialog = new Dialog(mContext);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.alert_publicprofile);
        int width = (int) (mContext.getResources().getDisplayMetrics().widthPixels * 0.75);
        int height = (int) (mContext.getResources().getDisplayMetrics().heightPixels * 0.75);
        dialog.getWindow().setLayout(width, height);
        this.editprofile = (ImageView) dialog.findViewById(R.id.cross_image);
        this.whatidiscussdescription = (TextView) dialog.findViewById(R.id.what_i_discuss_description);
        this.whatidiscusstitle = (TextView) dialog.findViewById(R.id.what_i_discuss_title);
        this.biodecsription = (TextView) dialog.findViewById(R.id.bio_decsription);
        this.biotitle = (TextView) dialog.findViewById(R.id.bio_title);
        this.companydescription = (TextView) dialog.findViewById(R.id.company_description);
        this.companyname = (TextView) dialog.findViewById(R.id.company_name);
        this.profileimage = (CircleImageView) dialog.findViewById(R.id.profile_image);
        Typeface boldFont = Typeface.createFromAsset(mContext.getAssets(), "Font/Gotham-Medium.otf");
        whatidiscussdescription.setTypeface(boldFont);
        whatidiscusstitle.setTypeface(boldFont);
        biodecsription.setTypeface(boldFont);
        biotitle.setTypeface(boldFont);
        companydescription.setTypeface(boldFont);
        companyname.setTypeface(boldFont);
        companyname.setText(companynames);
        biodecsription.setText(biodecsriptions);
        companydescription.setText(privateTitle1);
        whatidiscussdescription.setText(whatidiscussdescriptions);
        Glide.with(mContext).load(constants.BASE_URL + privateProfilePicture).error(R.drawable.user_profile).into(profileimage);
        dialog.show();
        dialog.setCancelable(false);
        editprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }


}
