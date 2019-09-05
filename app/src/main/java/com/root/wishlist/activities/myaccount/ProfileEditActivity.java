package com.root.wishlist.activities.myaccount;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.root.wishlist.R;
import com.root.wishlist.adapters.MyAccountViewPagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProfileEditActivity extends AppCompatActivity {

    @BindView(R.id.tablayout)
    TabLayout tablayout;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    MyAccountViewPagerAdapter myAccountViewPagerAdapter;
    int position = 0;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_edit);
        ButterKnife.bind(this);


        setSupportActionBar(toolbar);
        getSupportActionBar().hide();

        tablayout.addTab(tablayout.newTab().setText("Public"));
        tablayout.addTab(tablayout.newTab().setText("Private"));
        myAccountViewPagerAdapter = new MyAccountViewPagerAdapter(getSupportFragmentManager(), tablayout.getTabCount(), "profileEdit");
        viewpager.setOffscreenPageLimit(2);
        viewpager.setAdapter(myAccountViewPagerAdapter);

        viewpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tablayout));
        tablayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                // floatMenu(tab.getPosition());
                viewpager.setCurrentItem(tab.getPosition());
                //Toast.makeText(MyAccountActivity.this, "select", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                // Toast.makeText(MyAccountActivity.this, "unselect", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                //Toast.makeText(MyAccountActivity.this, "release", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        viewpager.setCurrentItem(position);
        myAccountViewPagerAdapter.notifyDataSetChanged();
    }
}
