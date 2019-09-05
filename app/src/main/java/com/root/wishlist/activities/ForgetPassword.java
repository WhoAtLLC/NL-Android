package com.root.wishlist.activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.root.wishlist.database.SharedDatabase;
import com.root.wishlist.util.globalValues.AlertDialogBox;
import com.root.wishlist.util.globalValues.Constants;
import com.root.wishlist.util.globalValues.GlobalClass;
import com.root.wishlist.util.globalValues.NoNetworkConnection;
import com.root.wishlist.presentation.registration.ForgetPasswordPresentation;
import com.root.wishlist.presentation.registration.Forgetpasswordint;
import com.root.wishlist.R;
import com.root.wishlist.view.ForgetPasswordView;
import com.root.wishlist.interfaces.UpdateMessage;

public class ForgetPassword extends AppCompatActivity implements View.OnClickListener, ForgetPasswordView, UpdateMessage, AlertDialogBox.FocusableValue, TextWatcher {

    Forgetpasswordint forgetPasswordPresentation;
    private android.widget.TextView forgetpassworttitletxt;
    private android.widget.TextView forgetpasswordmsgtxt;
    private android.widget.EditText userEmail;
    private android.widget.ImageView thikimg;
    private android.widget.ImageView back_arrow_image;
    private android.widget.TextView sendtxt;
    private android.widget.TextView errormessage;
    private SharedDatabase sharedDatabase;
    private AlertDialogBox alertDialogBox;
    private String returnActionAppInfo;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        new GlobalClass().statusBar(ForgetPassword.this);
        setContentView(R.layout.activity_forget_password);
        getSupportActionBar().hide();
        sharedDatabase = new SharedDatabase(this);
        alertDialogBox = new AlertDialogBox(this);
        initial();

        forgetPasswordPresentation = new ForgetPasswordPresentation(this, ForgetPassword.this);
        sendtxt.setOnClickListener(this);
        userEmail.addTextChangedListener(this);
        //back_arrow_image.setOnClickListener(this);

    }

    private void initial() {
        this.errormessage = (TextView) findViewById(R.id.error_message);
        this.sendtxt = (TextView) findViewById(R.id.send_txt);
        this.thikimg = (ImageView) findViewById(R.id.thik_img);
        this.userEmail = (EditText) findViewById(R.id.userEmail);
        //  this.back_arrow_image = (ImageView) findViewById(R.id.back_arrow_image);
        this.forgetpasswordmsgtxt = (TextView) findViewById(R.id.forgetpassword_msg_txt);
        this.forgetpassworttitletxt = (TextView) findViewById(R.id.forgetpasswort_title_txt);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "Font/Gotham-Book.otf");

        sendtxt.setTypeface(typeface);
        userEmail.setTypeface(typeface);
        forgetpasswordmsgtxt.setTypeface(typeface);


        Typeface boldFont = Typeface.createFromAsset(getAssets(), "Font/Gotham-Medium.otf");
        errormessage.setTypeface(boldFont);
        forgetpassworttitletxt.setTypeface(boldFont);
        userEmail.setText("" + getIntent().getStringExtra("email"));
    }

    @Override
    public void setEmailError(String userEmail) {


        new AlertDialogBox(this).validationdialog(userEmail, 2);


    }

    @Override
    public void navigateToHome() {

    }

    @Override
    public void loginStatus(String message) {

        if (message.equals("Created"))
            new AlertDialogBox(this).forgetPassword("Email sent successfully.Plese check your email", 2);

        else if (message.equals("Int")) {
            // new AlertDialogBox(this).networkMessage("No Internet Connection !");

            try {
                NoNetworkConnection.networkMessage(this, "com.root.wishlist.activities.ForgetPassword");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        } else
            new AlertDialogBox(this).forgetPassword(message, 2);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
           /* case R.id.back_arrow_image:
                startActivity(new Intent(getApplicationContext(), LoginActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_ANIMATION));
                break;*/
            case R.id.send_txt:
                forgetPasswordPresentation.validateEmail(userEmail.getText().toString());
                break;
        }
    }

    @Override
    public void focusChecked(int focus) {

        if (focus == 2) {
            errormessage.requestFocus();
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

        if (userEmail.getText().toString().toLowerCase().equals("xxxxxx")) {
            //showAppInfo();
            intent= new Intent(this,ChangeServerActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

   /* private void showAppInfo() {
        if (sharedDatabase.getTargetServer().equals(""))
            sharedDatabase.setTargetServer("http://wishlist.whoat.net/");
        String msg = "App Version: " + sharedDatabase.getVersion() + "\n\n" + "Server: " + sharedDatabase.getTargetServer() + "\n\n" + "If you yant to change the target server, you can write it below.";

        //alertDialogBox.appInfoDialog(msg);
          *//*  returnActionAppInfo= alertDialogBox.appInfoDialog(msg);
            if(returnActionAppInfo!=null)
            {
                if(!returnActionAppInfo.equals("server not changed"))
                    sharedDatabase.setTargetServer(returnActionAppInfo);
                else
                    sharedDatabase.setTargetServer(Constants.BASE_URL);
            }*//*


    }*/

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
