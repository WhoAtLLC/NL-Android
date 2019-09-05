package com.root.wishlist.activities;


import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.root.wishlist.util.globalValues.StatusBarTransparent;
import com.root.wishlist.presentation.profile.PrivacyPolicyInterface;
import com.root.wishlist.presentation.profile.PrivacyPolicyPresentation;
import com.root.wishlist.R;
import com.root.wishlist.view.PrivacyPolicyView;


public class PrivacyPolicyActivity extends AppCompatActivity implements PrivacyPolicyView {

    private WebView privacypolicy;
    private android.widget.TextView title;
    private android.widget.ImageView backarrowimage;
    PrivacyPolicyInterface privacyPolicyInterface;
    Typeface boldFont;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy_policy);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        this.title = (TextView) findViewById(R.id.title_txt);
        this.privacypolicy = (WebView) findViewById(R.id.privacy_policy);
        getSupportActionBar().hide();
        this.backarrowimage = (ImageView) findViewById(R.id.back_arrow_image);
        StatusBarTransparent.colorStatusbar(PrivacyPolicyActivity.this);
        privacyPolicyInterface = new PrivacyPolicyPresentation(getApplicationContext(), PrivacyPolicyActivity.this);
        privacyPolicyInterface.getPrivacyPolicy();
        boldFont = Typeface.createFromAsset(getAssets(), "Font/Gotham-Medium.otf");
        title.setTypeface(boldFont);

        backarrowimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


    @Override
    public void setId(int id) {

    }

    @Override
    public void setSlug(String slug) {

    }

    @Override
    public void setDateCreated(String dateCreated) {

    }

    @Override
    public void setDateChanged(String dateChanged) {

    }

    @Override
    public void setGraphId(Object graphId) {

    }

    @Override
    public void setLabel(String label) {

    }

    @Override
    public void setContent(String content) {

        privacypolicy.getSettings().setJavaScriptEnabled(true);
        privacypolicy.loadData(content, "text/html; charset=utf-8","UTF-8");
        privacypolicy.setBackgroundColor(000000);
    }
}