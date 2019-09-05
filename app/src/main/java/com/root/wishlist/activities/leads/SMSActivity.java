package com.root.wishlist.activities.leads;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.root.wishlist.R;

public class SMSActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);

        Intent sendIntent = new Intent(Intent.ACTION_VIEW);
        sendIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        sendIntent.setData(Uri.parse("sms:"));
        sendIntent.putExtra("sms_body", "Save time networking for new opportunities. Join me on WhoAt's WishList today");
        sendIntent.putExtra("exit_on_sent", true);
        startActivity(sendIntent);
        setResult(1, sendIntent);
        finish();
    }
}
