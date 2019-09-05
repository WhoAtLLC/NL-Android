package com.root.wishlist.activities.leads;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.root.wishlist.R;

import de.hdodenhof.circleimageview.CircleImageView;


public class PublicprofileActivity extends AppCompatActivity {

    private de.hdodenhof.circleimageview.CircleImageView profileimage;
    private android.widget.TextView companyname;
    private android.widget.TextView companydescription;
    private android.widget.TextView biotitle;
    private android.widget.TextView biodecsription;
    private android.widget.TextView whatidiscusstitle;
    private android.widget.TextView whatidiscussdescription;
    private com.github.clans.fab.FloatingActionButton contactusfloat;
    private com.github.clans.fab.FloatingActionButton privacypolicyfloat;
    private com.github.clans.fab.FloatingActionButton termsservicefloat;
    private com.github.clans.fab.FloatingActionButton signoutfloat;
    private com.github.clans.fab.FloatingActionMenu materialdesignandroidfloatingactionmenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publicprofile);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initial();
    }

    private void initial() {

        this.whatidiscussdescription = (TextView) findViewById(R.id.what_i_discuss_description);
        this.whatidiscusstitle = (TextView) findViewById(R.id.what_i_discuss_title);
        this.biodecsription = (TextView) findViewById(R.id.bio_decsription);
        this.biotitle = (TextView) findViewById(R.id.bio_title);
        this.companydescription = (TextView) findViewById(R.id.company_description);
        this.companyname = (TextView) findViewById(R.id.company_name);
        this.profileimage = (CircleImageView) findViewById(R.id.profile_image);

        Typeface fontface = Typeface.createFromAsset(getAssets(), "Font/Gotham-Book.otf");
        whatidiscussdescription.setTypeface(fontface);
        whatidiscusstitle.setTypeface(fontface);
        biodecsription.setTypeface(fontface);
        biotitle.setTypeface(fontface);
        companydescription.setTypeface(fontface);
        companyname.setTypeface(fontface);

        //for floating menu
        this.materialdesignandroidfloatingactionmenu = (FloatingActionMenu) findViewById(R.id.material_design_android_floating_action_menu);
      /*  this.signoutfloat = (FloatingActionButton) findViewById(R.id.signout_float);
        this.termsservicefloat = (FloatingActionButton) findViewById(R.id.terms_service_float);
        this.privacypolicyfloat = (FloatingActionButton) findViewById(R.id.privacy_policy_float);
        this.contactusfloat = (FloatingActionButton) findViewById(R.id.contactus_float);
*/
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
