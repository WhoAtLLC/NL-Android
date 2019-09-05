package com.root.wishlist.activities.registration;


import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.root.wishlist.R;
import com.root.wishlist.activities.LandingActivity;
import com.root.wishlist.activities.PrivacyPolicyActivity;
import com.root.wishlist.activities.TermAndConditionActivity;
import com.root.wishlist.database.SharedDatabase;
import com.root.wishlist.util.globalValues.AlertDialogBox;
import com.root.wishlist.util.globalValues.GlobalClass;
import com.root.wishlist.util.globalValues.NoNetworkConnection;
import com.root.wishlist.interfaces.UpdateMessage;
import com.root.wishlist.presentation.registration.FacebookToken;
import com.root.wishlist.presentation.registration.FacebookTokenPresentation;
import com.root.wishlist.presentation.registration.RegistrationStep1Presentation;
import com.root.wishlist.presentation.registration.RegistrationStep1PresentationImp;
import com.root.wishlist.view.FacebookUser;
import com.root.wishlist.view.RegistrationStep1View;

import org.json.JSONObject;

import java.util.Arrays;
import java.util.HashMap;


public class RegistrationActivity extends AppCompatActivity implements RegistrationStep1View, View.OnClickListener, UpdateMessage, AlertDialogBox.FocusableValue, FacebookUser {


    RegistrationStep1Presentation registrationStep1Presentation;
    private EditText userEmail;
    private ImageView correctEmailimg;
    private EditText userPassword;
    private ImageView showPassword, hidePassword;
    private TextView termstext;
    private TextView privacypolicytext;
    //for underlinetext
    private String terms, privacyPolicy;
    private TextView welcometitletxt;
    private TextView welcomemsgtxt;
    private TextView welcomemsg1txt;
    private TextView byregistertxt;
  //  private TextView signupText;
    private TextView or_Text;
    private TextView andtxt;
    private TextView register;
    private LinearLayout mainlayout;
    private ImageView backarrow;
    private ImageView facebookLogin;
    static int show_pass = 0;

    Typeface boldFont;
    boolean flagvalue = false;
    AlertDialogBox alertDialogBox;
    Typeface typeface;
    SharedDatabase sharedDatabase;
    CallbackManager callbackManager;
    FacebookToken facebookToken;
    private String firstName, lastName, fbEmail;
    HashMap<String, String> userOath = new HashMap<String, String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new GlobalClass().statusBar(RegistrationActivity.this);
        FacebookSdk.sdkInitialize(this.getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        setContentView(R.layout.activity_regsistration);
        getSupportActionBar().hide();
        initial();
        sharedDatabase = new SharedDatabase(getApplicationContext());
        fontface();
        facebookToken = new FacebookTokenPresentation(RegistrationActivity.this, getApplicationContext());
        alertDialogBox = new AlertDialogBox(this);
        facebookLogin.setOnClickListener(this);
        registrationStep1Presentation = new RegistrationStep1PresentationImp(this, RegistrationActivity.this);
        showPassword.setOnClickListener(this);
        hidePassword.setOnClickListener(this);
        backarrow.setOnClickListener(this);
        boldFont = Typeface.createFromAsset(getAssets(), "Font/Gotham-Medium.otf");
    }

    private void initial() {

        mainlayout = (LinearLayout) findViewById(R.id.mainlayout);
        backarrow = (ImageView) findViewById(R.id.back_arrow_image);
        facebookLogin = (ImageView) findViewById(R.id.facebok_login);
        this.register = (TextView) findViewById(R.id.register);
        this.andtxt = (TextView) findViewById(R.id.and_txt);
        this.byregistertxt = (TextView) findViewById(R.id.by_register_txt);
        this.welcomemsg1txt = (TextView) findViewById(R.id.welcome_msg1_txt);
        this.welcomemsgtxt = (TextView) findViewById(R.id.welcome_msg_txt);
        this.welcometitletxt = (TextView) findViewById(R.id.welcome_title_txt);
      //  this.signupText = (TextView) findViewById(R.id.signout_txt);
        this.or_Text = (TextView) findViewById(R.id.or_txt);

        this.privacypolicytext = (TextView) findViewById(R.id.privacy_policy_text);
        this.termstext = (TextView) findViewById(R.id.terms_text);
        showPassword = (ImageView) findViewById(R.id.showPassword);
        hidePassword = (ImageView) findViewById(R.id.hidePassword);
        this.userPassword = (EditText) findViewById(R.id.userPassword);
        this.correctEmailimg = (ImageView) findViewById(R.id.correctEmail_img);
        this.userEmail = (EditText) findViewById(R.id.userEmail);

        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(userEmail, InputMethodManager.SHOW_IMPLICIT);

        //for underline text
        terms = getResources().getString(R.string.terms);
        privacyPolicy = getResources().getString(R.string.privacy_policy);

        SpannableString spannableString = new SpannableString(terms);
        spannableString.setSpan(new UnderlineSpan(), 0, terms.length(), 0);
        termstext.setText(spannableString);

        termstext.setOnClickListener(this);
        privacypolicytext.setOnClickListener(this);
        SpannableString spannableString1 = new SpannableString(privacyPolicy);
        spannableString1.setSpan(new UnderlineSpan(), 0, privacyPolicy.length(), 0);
        privacypolicytext.setText(spannableString1);

        mainlayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                new GlobalClass().hideKeyboardFrom(RegistrationActivity.this, view);
                return false;
            }
        });
        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d("login", String.valueOf(loginResult.getAccessToken()));
                getUserDetails(loginResult);
            }

            @Override
            public void onCancel() {

                LoginManager.getInstance().logOut();
            }

            @Override
            public void onError(FacebookException error) {

            }
        });

    }

    private void fontface() {

        typeface = Typeface.createFromAsset(getAssets(), "Font/Gotham-Book.otf");
        privacypolicytext.setTypeface(typeface);
        termstext.setTypeface(typeface);

        register.setTypeface(typeface);
        andtxt.setTypeface(typeface);
        byregistertxt.setTypeface(typeface);
        welcomemsg1txt.setTypeface(typeface);
        welcomemsgtxt.setTypeface(typeface);

        userPassword.setTypeface(typeface);
        userEmail.setTypeface(typeface);
        welcometitletxt.setTypeface(boldFont);
        or_Text.setTypeface(boldFont);
      //  signupText.setTypeface(boldFont);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }


    @Override
    public void setUsernameError(String usernameError) {

        alertDialogBox.validationdialog(usernameError, 4);

    }

    @Override
    public void setPasswordError(String userpassword) {
        userEmail.setCursorVisible(true);
        new AlertDialogBox(this).validationdialog(userpassword, 3);
        //userPassword.setError(userpassword);
    }

    @Override
    public void setNetworkMessage(String connection) {
        //  new AlertDialogBox(this).validationdialog(connection, 1);
        try {
            NoNetworkConnection.networkMessage(this, "com.root.wishlist.activities.registration.RegistrationActivity");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.showPassword:
                if (userPassword.getText().length() != 0) {

                    hidePassword.setVisibility(View.VISIBLE);
                    userPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    showPassword.setVisibility(View.GONE);
                    userPassword.setTypeface(typeface);
                    userPassword.setHint("Password (Minimum 8 characters)");

                }
                break;
            case R.id.hidePassword:

                showPassword.setVisibility(View.VISIBLE);
                userPassword.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_CLASS_TEXT);
                hidePassword.setVisibility(View.GONE);
                userPassword.setTypeface(typeface);
                userPassword.setHint("Password (Minimum 8 characters)");

                break;
            case R.id.register:
                sharedDatabase.userEmail(userEmail.getText().toString());
                /*Intent intent = new Intent(this, RegistrationUserDetailsActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);*/
                registrationStep1Presentation.registrationValidate(userEmail.getText().toString(), userPassword.getText().toString());
                break;
            case R.id.back_arrow_image:
                startActivity(new Intent(getApplicationContext(), LandingActivity.class).setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION | Intent.FLAG_ACTIVITY_CLEAR_TASK));
                break;
            case R.id.privacy_policy_text:
                startActivity(new Intent(getApplicationContext(), PrivacyPolicyActivity.class).setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION | Intent.FLAG_ACTIVITY_CLEAR_TASK));
                break;
            case R.id.terms_text:
                startActivity(new Intent(getApplicationContext(), TermAndConditionActivity.class).setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION | Intent.FLAG_ACTIVITY_CLEAR_TASK));
                break;
            case R.id.facebok_login:
                fbLogin();
                break;
        }
    }

    @Override
    public void loginStatus(String message) {
        Log.d("Login", message);

        if (message.equals("success")) {
            sharedDatabase.setLoginwith("app");
            Intent intent = new Intent(this, RegistrationUserDetailsActivity.class);
            intent.putExtra("code", "app");
            startActivity(intent);
            finish();
            overridePendingTransition(0, 0);
        } else {
            new AlertDialogBox(this).validationdialog(message, 2);
        }
    }

    @Override
    public void focusChecked(int focus) {

        if (focus == 4) {
            userEmail.requestFocus();
        } else if (focus == 3) {
            userPassword.requestFocus();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    void fbLogin() {
        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile", "user_friends", "email", "user_birthday"));
    }

    public void getUserDetails(final LoginResult loginResult) {
        GraphRequest request = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(),
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        String token = loginResult.getAccessToken().getToken();
                        fbEmail = object.optString("email");
                        firstName = object.optString("first_name");
                        lastName = object.optString("last_name");
                        userOath.put("oauth_token", token);
/*
                        ObjectMapper mapper = new ObjectMapper();
                        try {
                            String json = mapper.writeValueAsString(userOath);
                            Log.d("json", json);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }*/
                        Log.d("Email", fbEmail);
                        Log.d("fname", firstName);
                        Log.d("lname", lastName);
                        Log.d("access token is: ", token);
                        Log.d("Response", response.getJSONObject().toString());
                        facebookToken.userToken(userOath);

                    }
                });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,first_name,last_name,link,gender,email");
        request.setParameters(parameters);
        request.executeAsync();
    }

    @Override
    public void userToken(String token) {
        /*sharedDatabase.setLoginwith("fb");
        Intent intent = new Intent(getApplicationContext(), RegistrationUserDetailsActivity.class);
        intent.putExtra("firstName", firstName);
        intent.putExtra("lastName", lastName);
        intent.putExtra("fbEmail", fbEmail);
        intent.putExtra("code", "fb");
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        Log.d("userToken", token);
        Log.d("userToken", token);
        Log.d("userToken", token);
        Log.d("userToken", token);*/
    }
}
