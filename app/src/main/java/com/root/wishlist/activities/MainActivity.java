package com.root.wishlist.activities;

import android.app.TabActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TextView;

import com.root.wishlist.R;


public class MainActivity extends TabActivity {

    TabHost TabHostWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TabHost mTabHost = getTabHost();

        //Assign id to Tabhost.
        TabHostWindow = (TabHost) findViewById(android.R.id.tabhost);

        //Creating tab menu.
        TabHost.TabSpec TabMenu1 = TabHostWindow.newTabSpec("First tab");
        TabHost.TabSpec TabMenu2 = TabHostWindow.newTabSpec("Second Tab");
        TabHost.TabSpec TabMenu3 = TabHostWindow.newTabSpec("Third Tab");
        TabHost.TabSpec TabMenu4 = TabHostWindow.newTabSpec("Third Tab");

        //Setting up tab 1 name.
        TabMenu1.setIndicator("Leads");
        //Set tab 1 activity to tab 1 menu.
        TabMenu1.setContent(new Intent(this, LeadsActivity.class));

        //Setting up tab 2 name.
        TabMenu2.setIndicator("Notification");
        //Set tab 3 activity to tab 1 menu.
        TabMenu2.setContent(new Intent(this, NotificationActivity.class));

        //Setting up tab 2 name.
        TabMenu3.setIndicator("MyAccount");
        //Set tab 3 activity to tab 3 menu.
        TabMenu3.setContent(new Intent(this, MyAccountActivity.class));

        //Adding tab1, tab2, tab3 to tabhost view.
        TabMenu4.setIndicator("More");
        //Set tab 3 activity to tab 3 menu.
        TabMenu4.setContent(new Intent(this, MoreActivity.class));


        TabHostWindow.addTab(TabMenu1);
        TabHostWindow.addTab(TabMenu2);
        TabHostWindow.addTab(TabMenu3);
        TabHostWindow.addTab(TabMenu4);
    }

    @Override
    protected void onResume() {
        super.onResume();
        TabHost tabhost = getTabHost();
        for(int i=0;i<tabhost.getTabWidget().getChildCount();i++)
        {
            TextView tv = (TextView) tabhost.getTabWidget().getChildAt(i).findViewById(android.R.id.title);
            tv.setTextColor(Color.parseColor("#FF5454"));
            tv.setTextSize(10);
        }
    }
}
