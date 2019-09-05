package com.root.wishlist.activities;


import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
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
import com.root.wishlist.activities.registration.RegisterationUploadContact;
import com.root.wishlist.activities.registration.RegistrationActivity;
import com.root.wishlist.activities.registration.YourBusinessInterest;
import com.root.wishlist.database.SharedDatabase;
import com.root.wishlist.presentation.registration.FacebookTokenPresentation;
import com.root.wishlist.util.globalValues.AlertDialogBox;
import com.root.wishlist.util.globalValues.GlobalClass;
import com.root.wishlist.util.globalValues.NoNetworkConnection;
import com.root.wishlist.util.globalValues.WishlistProgressDialog;
import com.root.wishlist.presentation.registration.FacebookToken;
import com.root.wishlist.presentation.registration.LoginPresentationInt;
import com.root.wishlist.presentation.registration.LoginpresentationImp;
import com.root.wishlist.R;
import com.root.wishlist.view.FacebookUser;
import com.root.wishlist.view.LoginView;
import com.root.wishlist.interfaces.UpdateMessage;

import org.json.JSONObject;

import java.util.Arrays;
import java.util.HashMap;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, LoginView, UpdateMessage, AlertDialogBox.FocusableValue/*, FacebookUser */{

    private android.widget.EditText userEmail;
    private android.widget.EditText userPassword;
    private android.widget.ImageView userEmailthickicon;
    private android.widget.ImageView passwordeyeicon;
    private android.widget.ImageView fbLogin;
    private android.widget.TextView logintxt;
    private android.widget.LinearLayout loginbuttonlyt;
    private android.widget.TextView forgetpasswordtxt;
    private android.widget.TextView ortxt;
    private android.widget.TextView signuptxt;
    private android.widget.TextView notamembertxt;
    private android.widget.TextView registertxt;
    private android.widget.TextView ortext;
   // private android.widget.TextView loginwith_txt;
    private LinearLayout mainLayout;
    private ImageView backarrow;
    static int show_pass = 0;
    LoginPresentationInt loginPresentationInt;
    WishlistProgressDialog wishlistProgressDialog;
    SharedDatabase sharedDatabase;
    CallbackManager callbackManager;
    FacebookToken facebookToken;
    private String firstName, lastName, fbEmail;
    HashMap<String, String> userOath = new HashMap<String, String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new GlobalClass().statusBar(LoginActivity.this);
        FacebookSdk.sdkInitialize(this.getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        intilization();
        sharedDatabase = new SharedDatabase(getApplicationContext());
        wishlistProgressDialog = new WishlistProgressDialog(this);
        loginPresentationInt = new LoginpresentationImp(this, LoginActivity.this);
        //facebookToken = new FacebookTokenPresentation(LoginActivity.this, getApplicationContext());
        passwordeyeicon.setOnClickListener(this);
        registertxt.setOnClickListener(this);
        logintxt.setOnClickListener(this);
        backarrow.setOnClickListener(this);
        fbLogin.setOnClickListener(this);
    }

    private void intilization() {

        mainLayout = (LinearLayout) findViewById(R.id.mainlayout);
        backarrow = (ImageView) findViewById(R.id.back_arrow_image);
        this.registertxt = (TextView) findViewById(R.id.register_txt);
        this.notamembertxt = (TextView) findViewById(R.id.notamember_txt);
        this.ortext = (TextView) findViewById(R.id.or_txt);
      //  this.loginwith_txt = (TextView) findViewById(R.id.loginwith_txt);

        this.forgetpasswordtxt = (TextView) findViewById(R.id.forget_password_txt);
        this.loginbuttonlyt = (LinearLayout) findViewById(R.id.login_button_lyt);
        this.logintxt = (TextView) findViewById(R.id.login_txt);
        this.passwordeyeicon = (ImageView) findViewById(R.id.password_eye_icon);
        this.userEmailthickicon = (ImageView) findViewById(R.id.userEmail_thick_icon);
        this.fbLogin = (ImageView) findViewById(R.id.facebok_login);
        this.userEmail = (EditText) findViewById(R.id.userEmail);
        this.userPassword = (EditText) findViewById(R.id.userPassword);
        userEmail.setText("");
        userPassword.setText("");
        //for keyboad

        mainLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                new GlobalClass().hideKeyboardFrom(LoginActivity.this, view);
                return false;
            }
        });
        //font set
        Typeface fontface = Typeface.createFromAsset(getAssets(), "Font/Gotham-Book.otf");
        registertxt.setTypeface(fontface);
        notamembertxt.setTypeface(fontface);
        logintxt.setTypeface(fontface);
        userEmail.setTypeface(fontface);
        userPassword.setTypeface(fontface);
        Typeface boldFont = Typeface.createFromAsset(getAssets(), "Font/Gotham-Medium.otf");
        forgetpasswordtxt.setTypeface(boldFont);
      //  loginwith_txt.setTypeface(boldFont);
        ortext.setTypeface(boldFont);

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

    public void forgotPassword(View view) {
        Intent intent = new Intent(this, ForgetPassword.class);
        intent.putExtra("email", userEmail.getText().toString());
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
        overridePendingTransition(0, 0);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.password_eye_icon:
                if (show_pass == 0) {
                    userPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    show_pass = 1;
                } else {
                    userPassword.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_CLASS_TEXT);
                    show_pass = 0;
                }
                break;
            case R.id.register_txt:
                startActivity(new Intent(getApplicationContext(), RegistrationActivity.class).setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION | Intent.FLAG_ACTIVITY_CLEAR_TASK));
                finish();
                overridePendingTransition(0, 0);
                break;
            case R.id.login_txt:

                //  startActivity(new Intent(getApplicationContext(), LeadsActivity.class).setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION | Intent.FLAG_ACTIVITY_CLEAR_TASK));
                loginPresentationInt.loginValidate(userEmail.getText().toString(), userPassword.getText().toString());

                break;
            case R.id.back_arrow_image:
                startActivity(new Intent(getApplicationContext(), LandingActivity.class).setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION | Intent.FLAG_ACTIVITY_CLEAR_TASK));
                finish();
                overridePendingTransition(0, 0);
                break;
            case R.id.facebok_login:
                fbLogin();
                break;

        }
    }

    @Override
    public void setUsernameError(String usernameError) {

        new AlertDialogBox(this).validationdialog(usernameError, 9);
    }

    @Override
    public void setPasswordError(String userpassword) {

        new AlertDialogBox(this).validationdialog(userpassword, 10);
    }

    @Override
    public void checkNetworkConnection(String connection) {

        // new AlertDialogBox(this).validationdialog(connection, 1);
        noConnection();
    }

    private void noConnection() {
        try {
            NoNetworkConnection.networkMessage(this, "com.root.wishlist.activities.LoginActivity");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void loginStatus(String message) {

        sharedDatabase.userEmail(userEmail.getText().toString());
        switch (message) {
           /* case "contacts":
                startActivity(new Intent(getApplicationContext(), RegisterationUploadContact.class).putExtra("whoseCalling","syncContacts").setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION | Intent.FLAG_ACTIVITY_CLEAR_TASK));
                finish();
                overridePendingTransition(0, 0);
                break;*/

            case "completed":
                startActivity(new Intent(getApplicationContext(), LeadsActivity.class).setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION | Intent.FLAG_ACTIVITY_CLEAR_TASK));
                finish();
                overridePendingTransition(0, 0);
                break;
        /*    case "companies":
                startActivity(new Intent(getApplicationContext(), YourBusinessInterest.class).setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION | Intent.FLAG_ACTIVITY_CLEAR_TASK));
                finish();
                overridePendingTransition(0, 0);
                break;*/
            case "INC":
                noConnection();
                // new AlertDialogBox(this).networkMessage("No Internet Connection!");
            default:
                new AlertDialogBox(this).validationdialog(message, 1);
                break;
        }

    }

    @Override
    public void focusChecked(int focus) {

        if (focus == 9)
            userEmail.requestFocus();
        else if (focus == 10)
            userPassword.requestFocus();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
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
                      //   facebookToken.userToken(userOath);
                        Log.d("Email", fbEmail);
                        Log.d("fname", firstName);
                        Log.d("lname", lastName);
                        Log.d("access token is: ", token);
                        Log.d("Response", response.getJSONObject().toString());
                    }
                });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,first_name,last_name,link,gender,email");
        request.setParameters(parameters);
        request.executeAsync();
    }

   /* @Override
    public void userToken(String token) {
  sharedDatabase.setLoginwith("fb");
        Intent intent = new Intent(getApplicationContext(), LeadsActivity.class);
        intent.putExtra("firstName", firstName);
        intent.putExtra("lastName", lastName);
        intent.putExtra("fbEmail", fbEmail);
        intent.putExtra("code", "fb");
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        Log.d("userToken", token);
        Log.d("userToken", token);
        Log.d("userToken", token);
        Log.d("userToken", token);
    }*/
}