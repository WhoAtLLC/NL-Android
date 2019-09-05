package com.root.wishlist.activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.root.wishlist.util.globalValues.StatusBarTransparent;
import com.root.wishlist.presentation.profile.PrivacyPolicyInterface;
import com.root.wishlist.presentation.profile.PrivacyPolicyPresentation;
import com.root.wishlist.R;
import com.root.wishlist.view.PrivacyPolicyView;


public class TermAndConditionActivity extends AppCompatActivity implements PrivacyPolicyView {

    PrivacyPolicyInterface privacyPolicyInterface;
    private WebView termAndCondition;
    private TextView title;
    private ImageView backarrowimage;
    Typeface boldFont;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_conditions);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        this.title = (TextView) findViewById(R.id.title_txt);
        this.backarrowimage = (ImageView) findViewById(R.id.back_arrow_image);
        StatusBarTransparent.colorStatusbar(TermAndConditionActivity.this);
        this.termAndCondition = (WebView) findViewById(R.id.privacy_policy);
        getSupportActionBar().hide();
        privacyPolicyInterface = new PrivacyPolicyPresentation(getApplicationContext(), TermAndConditionActivity.this);
        privacyPolicyInterface.getTerm();
        boldFont = Typeface.createFromAsset(getAssets(), "Font/Gotham-Medium.otf");
        title.setTypeface(boldFont);
        title.setText("Terms Of Service");
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
        //termAndCondition.setText(label);
    }

    @Override
    public void setContent(String content) {
        termAndCondition.getSettings().setJavaScriptEnabled(true);
        termAndCondition.loadData(content, "text/html; charset=utf-8", "UTF-8");
        termAndCondition.setBackgroundColor(000000);
        Log.d("dada", content);

    }

    @Override
    public void onBackPressed() {
        Intent data = new Intent();
        setResult(RESULT_OK, data);
        super.onBackPressed();

        overridePendingTransition(0, 0);
        finish();
    }
}
