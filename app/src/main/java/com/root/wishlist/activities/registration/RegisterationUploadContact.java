package com.root.wishlist.activities.registration;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.root.wishlist.R;
import com.root.wishlist.interfaces.UpdateMessage;
import com.root.wishlist.searvices.ContactFetchSearvice;
import com.root.wishlist.searvices.MyService;
import com.root.wishlist.util.globalValues.AlertDialogBox;
import com.root.wishlist.util.globalValues.GlobalClass;
import com.root.wishlist.util.globalValues.NoNetworkConnection;

public class RegisterationUploadContact extends AppCompatActivity implements View.OnClickListener, UpdateMessage {

    private static final int PERMISSIONS_REQUEST_READ_CONTACTS = 999;
    AlertDialogBox alertDialogBox;
    private android.widget.LinearLayout uploadContact;
    private TextView uploadcontacttitle;
    private TextView uploadcontact1txt;
    private TextView uploadcontact2txt;
    private TextView uploadcontact3txt;
    private TextView uploadContacttxt;
    private LinearLayout successStepsLLayout;
    private String whoseCalling;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new GlobalClass().statusBar(RegisterationUploadContact.this);
        setContentView(R.layout.activity_registeration_upload_contact);
        this.uploadcontact3txt = (TextView) findViewById(R.id.upload_contact3_txt);
        this.uploadcontact2txt = (TextView) findViewById(R.id.upload_contact2_txt);
        this.uploadcontact1txt = (TextView) findViewById(R.id.upload_contact1_txt);
        this.uploadcontacttitle = (TextView) findViewById(R.id.upload_contact_title);
        this.uploadContacttxt = (TextView) findViewById(R.id.uploadContact_txt);
        this.successStepsLLayout = (LinearLayout) findViewById(R.id.successStepsLLayout);

        if (getIntent().getExtras() != null)
            whoseCalling = getIntent().getExtras().getString("whoseCalling");

        if(whoseCalling.equals("resyncContacts"))
        {
            successStepsLLayout.setVisibility(View.GONE);
            uploadContacttxt.setText("Resync Contacts");
        }
        getSupportActionBar().hide();
        alertDialogBox = new AlertDialogBox(RegisterationUploadContact.this);
        this.uploadContact = (LinearLayout) findViewById(R.id.uploadContact);
        uploadContact.setOnClickListener(this);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "Font/Gotham-Book.otf");
        uploadcontacttitle.setTypeface(typeface);
        uploadContacttxt.setTypeface(typeface);
        Typeface boldFont = Typeface.createFromAsset(getAssets(), "Font/Gotham-Medium.otf");
        uploadcontact3txt.setTypeface(boldFont);
        uploadcontact2txt.setTypeface(boldFont);
        uploadcontact1txt.setTypeface(boldFont);
    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.uploadContact) {
            uploadContacts();
        }
    }

    @Override
    public void onBackPressed() {
        if(whoseCalling.equals("resyncContacts"))
            finish();

    }

    @Override
    public void loginStatus(String message) {
        if (message.equals("success")) {
            uploadContactsService();
            if(whoseCalling.equals("resyncContacts"))
                Toast.makeText(RegisterationUploadContact.this, "Upload Success", Toast.LENGTH_SHORT).show();
            else
            {
                Intent intent = new Intent(this, YourBusinessInterest.class);
                startActivity(intent);
                finish();
                overridePendingTransition(0, 0);
            }

            //Toast.makeText(RegisterationUploadContact.this, "Upload Success", Toast.LENGTH_SHORT).show();
        } else {
            // new AlertDialogBox(this).networkMessage(message);
            try {
                NoNetworkConnection.networkMessage(this, "com.root.wishlist.activities.registration.RegisterationUploadContact");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        if (requestCode == PERMISSIONS_REQUEST_READ_CONTACTS) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                uploadContacts();
            } else {
                Toast.makeText(this, "Until you grant the permission, we canot display the names", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void uploadContacts() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, PERMISSIONS_REQUEST_READ_CONTACTS);

        } else {
            new AlertDialogBox(RegisterationUploadContact.this).uploadContacts(whoseCalling);
        }
    }

    private void uploadContactsService() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, PERMISSIONS_REQUEST_READ_CONTACTS);

        } else
            //new ContactFetchSearvice(getApplicationContext()).execute();
            startService(new Intent(this, MyService.class));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

}
