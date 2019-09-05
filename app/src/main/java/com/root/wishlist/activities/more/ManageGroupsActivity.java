package com.root.wishlist.activities.more;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.root.wishlist.R;
import com.root.wishlist.util.globalValues.InviteFriendMessage;
import com.root.wishlist.util.globalValues.StatusBarTransparent;

import butterknife.ButterKnife;

public class ManageGroupsActivity extends AppCompatActivity {

    TextView learnmore;
    TextView contactus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_groups);

        learnmore=(TextView)findViewById(R.id.managegrouplearn);
        contactus=(TextView)findViewById(R.id.managegroupesmail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
      //  setSupportActionBar(toolbar);
        StatusBarTransparent.colorStatusbar(ManageGroupsActivity.this);
       // ButterKnife.bind(this);
       // setFont();
       // requestWindowFeature(Window.FEATURE_NO_TITLE);


        contactus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InviteFriendMessage.supportMail(ManageGroupsActivity.this);
            }
        });
   learnmore.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View v) {
           Intent browserIntent = new Intent(Intent.ACTION_VIEW,
                   Uri.parse("http://www.wishlist.whoat.io"));
           startActivity(browserIntent);
       }
   });





    }
}
