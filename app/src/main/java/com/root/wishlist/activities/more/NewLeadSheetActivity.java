package com.root.wishlist.activities.more;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.root.wishlist.util.globalValues.StatusBarTransparent;
import com.root.wishlist.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class NewLeadSheetActivity extends AppCompatActivity {


    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.daily_txt)
    TextView dailyTxt;
    @BindView(R.id.weekly_txt)
    TextView weeklyTxt;
    @BindView(R.id.monthly_txt)
    TextView monthlyTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_lead_sheet);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        StatusBarTransparent.colorStatusbar(NewLeadSheetActivity.this);
        ButterKnife.bind(this);
        setFont();
    }

    public void setFont() {
        Typeface normalfont = Typeface.createFromAsset(getAssets(), "Font/Gotham-Book.otf");
        dailyTxt.setTypeface(normalfont);
        weeklyTxt.setTypeface(normalfont);
        monthlyTxt.setTypeface(normalfont);
        title.setTypeface(normalfont);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
       // startActivity(new Intent(getApplicationContext(), MoreActivity.class).setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
        finish();
        overridePendingTransition(0, 0);
    }
}
