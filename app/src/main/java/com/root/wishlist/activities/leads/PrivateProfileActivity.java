package com.root.wishlist.activities.leads;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.root.wishlist.R;

import de.hdodenhof.circleimageview.CircleImageView;


public class PrivateProfileActivity extends AppCompatActivity {

    private de.hdodenhof.circleimageview.CircleImageView profileimage;
    private android.widget.TextView Employeename;
    private android.widget.TextView profilename;
    private android.widget.TextView companyname;
    private android.widget.TextView employeecontactno;
    private android.widget.TextView employeeemail;
    private android.widget.TextView biotitle;
    private android.widget.TextView biodecsription;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_private_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initial();
    }
    public void initial()
    {
        this.biodecsription = (TextView) findViewById(R.id.bio_decsription);
        this.biotitle = (TextView) findViewById(R.id.bio_title);
        this.employeeemail = (TextView) findViewById(R.id.employee_email);
        this.employeecontactno = (TextView) findViewById(R.id.employee_contact_no);
        this.companyname = (TextView) findViewById(R.id.company_name);
        this.profilename = (TextView) findViewById(R.id.profile_name);
        this.Employeename = (TextView) findViewById(R.id.Employee_name);
        this.profileimage = (CircleImageView) findViewById(R.id.profile_image);

        Typeface fontface = Typeface.createFromAsset(getAssets(), "Font/Gotham-Book.otf");
        biodecsription.setTypeface(fontface);
        biotitle.setTypeface(fontface);
        employeeemail.setTypeface(fontface);
        employeecontactno.setTypeface(fontface);
        companyname.setTypeface(fontface);
        profilename.setTypeface(fontface);
        Employeename.setTypeface(fontface);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId()==android.R.id.home)
        {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }


}
