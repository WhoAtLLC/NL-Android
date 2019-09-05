package com.root.wishlist.activities.registration;

import android.Manifest;
import android.app.Activity;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.system.ErrnoException;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.root.wishlist.R;
import com.root.wishlist.activities.PrivacyPolicyActivity;
import com.root.wishlist.activities.TermAndConditionActivity;
import com.root.wishlist.database.SharedDatabase;
import com.root.wishlist.interfaces.UpdateMessage;
import com.root.wishlist.presentation.registration.UserDetailsPresentation;
import com.root.wishlist.presentation.registration.UserDetailsimp;
import com.root.wishlist.util.globalValues.AlertDialogBox;
import com.root.wishlist.util.globalValues.GlobalClass;
import com.root.wishlist.util.globalValues.NoNetworkConnection;
import com.root.wishlist.view.UserDetailsView;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class RegistrationUserDetailsActivity extends AppCompatActivity implements View.OnClickListener, UserDetailsView, UpdateMessage, AlertDialogBox.FocusableValue {

    private CircleImageView profileimage;
    private TextView register;
    private EditText userFirstName;
    private ImageView correctEmailimg;
    private EditText userLastName;
    private EditText userScreenName;
    private EditText userEmail;
    private TextView termstext;
    private TextView privacypolicytext;
    private LinearLayout fbEmailDetails;
    //for underlinetext
    private String terms, privacyPolicy;
    private ImageView backarrow;
    Intent intent;
    private TextView byregistertxt;
    private TextView andtxt;
    UserDetailsPresentation userDetailsPresentation;
    public static final int STORAGE_PERMISSION_CODE = 123;
    //for image
    protected int LOAD_IMAGE_CAMERA = 0, CROP_IMAGE = 1, LOAD_IMAGE_GALLARY = 2;
    Context context;
    private File pic;
    public File filePath;
    private Uri picUri;
    SharedDatabase sharedDatabase;
    View fb_email_view;
    AlertDialog.Builder builder;
    Button CropImageViewNo, CropImageView1;
    CropImageView mCropImageView;
    RelativeLayout Crop;
    Uri mCropImageUri;
    MultipartBody.Part imageFileBody;
    public static final String MULTIPART_FORM_DATA = "multipart/form-data";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new GlobalClass().statusBar(RegistrationUserDetailsActivity.this);
        setContentView(R.layout.activity_registration_user_details);
        getSupportActionBar().hide();
        initial();
        fontFace();
        raiseRuntimePermisionForLocation();
        sharedDatabase = new SharedDatabase(getApplicationContext());
        if (sharedDatabase.getLoginwith().equals("fb")) {
            fbEmailDetails.setVisibility(View.VISIBLE);
            fb_email_view.setVisibility(View.VISIBLE);

            userFirstName.setText("" + getIntent().getStringExtra("firstName"));
            userLastName.setText("" + getIntent().getStringExtra("lastName"));
            userEmail.setText("" + getIntent().getStringExtra("fbEmail"));
        }

        register.setOnClickListener(this);
        backarrow.setOnClickListener(this);
        userDetailsPresentation = new UserDetailsimp(this, RegistrationUserDetailsActivity.this);
        mCropImageView = (CropImageView) findViewById(R.id.CropImageView);

        Crop = (RelativeLayout) findViewById(R.id.crop);
        CropImageViewNo = (Button) findViewById(R.id.CropImageViewNo);

        CropImageViewNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Crop.setVisibility(View.GONE);

            }
        });
        CropImageView1 = (Button) findViewById(R.id.CropImageView1);
        profileimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivityForResult(getPickImageChooserIntent(), 200);
            }
        });

        CropImageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Bitmap cropped = mCropImageView.getCroppedImage(500, 500);
                    if (cropped != null) {
                        Crop.setVisibility(View.GONE);
                        saveIntoFile(cropped);
                    }
                } catch (NullPointerException e) {
                    e.printStackTrace();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }


    public void initial() {

        this.andtxt = (TextView) findViewById(R.id.and_txt);
        this.byregistertxt = (TextView) findViewById(R.id.by_register_txt);
        fb_email_view = findViewById(R.id.fb_email_view);
        this.register = (TextView) findViewById(R.id.register);
        this.privacypolicytext = (TextView) findViewById(R.id.privacy_policy_text);
        this.termstext = (TextView) findViewById(R.id.terms_text);
        this.userScreenName = (EditText) findViewById(R.id.userScreenName);
        this.userLastName = (EditText) findViewById(R.id.userLastName);
        this.correctEmailimg = (ImageView) findViewById(R.id.correctEmail_img);
        this.userFirstName = (EditText) findViewById(R.id.userFirstName);
        this.userEmail = (EditText) findViewById(R.id.userEmail);
        this.profileimage = (CircleImageView) findViewById(R.id.profile_image);
        this.backarrow = (ImageView) findViewById(R.id.back_arrow_image);
        fbEmailDetails = (LinearLayout) findViewById(R.id.fb_email_lyt);
        terms = getResources().getString(R.string.terms);
        privacyPolicy = getResources().getString(R.string.privacy_policy);
        mCropImageView = (CropImageView) findViewById(R.id.CropImageView);
        Crop = (RelativeLayout) findViewById(R.id.crop);
        CropImageViewNo = (Button) findViewById(R.id.CropImageViewNo);
        CropImageView1 = (Button) findViewById(R.id.CropImageView1);
        profileimage.setOnClickListener(this);
        SpannableString spannableString = new SpannableString(terms);
        spannableString.setSpan(new UnderlineSpan(), 0, terms.length(), 0);
        termstext.setText(spannableString);

        SpannableString spannableString1 = new SpannableString(privacyPolicy);
        spannableString1.setSpan(new UnderlineSpan(), 0, privacyPolicy.length(), 0);
        privacypolicytext.setText(spannableString1);
        privacypolicytext.setOnClickListener(this);
        termstext.setOnClickListener(this);


    }

    private void fontFace() {
        Typeface typeface = Typeface.createFromAsset(getAssets(), "Font/Gotham-Book.otf");
        andtxt.setTypeface(typeface);
        byregistertxt.setTypeface(typeface);
        register.setTypeface(typeface);
        privacypolicytext.setTypeface(typeface);
        termstext.setTypeface(typeface);

        Typeface boldFont = Typeface.createFromAsset(getAssets(), "Font/Gotham-Medium.otf");
        userScreenName.setTypeface(boldFont);
        userLastName.setTypeface(boldFont);
        userFirstName.setTypeface(boldFont);
        userEmail.setTypeface(boldFont);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.back_arrow_image:
                intent = new Intent(this, RegistrationActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                break;
            case R.id.register:

                if (filePath != null) {
                    RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), filePath);
                    imageFileBody = MultipartBody.Part.createFormData("image", filePath.getName(), requestBody);
                }
                userDetailsPresentation.userDetails(imageFileBody, userFirstName.getText().toString(),
                        userLastName.getText().toString(), userScreenName.getText().toString());

                break;
            case R.id.privacy_policy_text:
                startActivity(new Intent(getApplicationContext(), PrivacyPolicyActivity.class).setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION | Intent.FLAG_ACTIVITY_CLEAR_TASK));
                break;
            case R.id.terms_text:
                startActivity(new Intent(getApplicationContext(), TermAndConditionActivity.class).setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION | Intent.FLAG_ACTIVITY_CLEAR_TASK));
                break;
        }


    }

    public Intent getPickImageChooserIntent() {
        Uri outputFileUri = getCaptureImageOutputUri();

        List<Intent> allIntents = new ArrayList<>();
        PackageManager packageManager = getPackageManager();

        // collect all camera intents

        Intent captureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        List<ResolveInfo> listCam = packageManager.queryIntentActivities(captureIntent, 0);
        for (ResolveInfo res : listCam) {
            Intent intent = new Intent(captureIntent);
            intent.setComponent(new ComponentName(res.activityInfo.packageName, res.activityInfo.name));
            intent.setPackage(res.activityInfo.packageName);
            if (outputFileUri != null) {
                intent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
            }
            allIntents.add(intent);
        }

        // collect all gallery intents

        Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        List<ResolveInfo> listGallery = packageManager.queryIntentActivities(galleryIntent, 0);
        for (ResolveInfo res : listGallery) {
            Intent intent = new Intent(galleryIntent);
            intent.setComponent(new ComponentName(res.activityInfo.packageName, res.activityInfo.name));
            intent.setPackage(res.activityInfo.packageName);
            allIntents.add(intent);
        }

        // the main intent is the last in the  list (fucking android) so pickup the useless one

        Intent mainIntent = allIntents.get(allIntents.size() - 1);
        for (Intent intent : allIntents) {
            if (intent.getComponent().getClassName().equals("com.android.documentsui.DocumentsActivity")) {
                mainIntent = intent;
                break;
            }
        }
        allIntents.remove(mainIntent);
        Intent chooserIntent = Intent.createChooser(mainIntent, "Select source");
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, allIntents.toArray(new Parcelable[allIntents.size()]));
        return chooserIntent;
    }

    /**
     * Get URI to image received from capture  by camera.
     */
    private Uri getCaptureImageOutputUri() {
        Uri outputFileUri = null;
        File getImage = getExternalCacheDir();
        if (getImage != null) {
            outputFileUri = Uri.fromFile(new File(getImage.getPath(), "pickImageResult.jpeg"));
        }
        return outputFileUri;
    }

    public Uri getPickImageResultUri(Intent data) {
        boolean isCamera = true;
        if (data != null && data.getData() != null) {
            String action = data.getAction();
            isCamera = action != null && action.equals(MediaStore.ACTION_IMAGE_CAPTURE);
        }
        return isCamera ? getCaptureImageOutputUri() : data.getData();
    }

    /**
     * Test if we can open the given Android URI to test if permission required error is thrown.<br>
     */
    public boolean isUriRequiresPermissions(Uri uri) {
        try {
            ContentResolver resolver = getContentResolver();
            InputStream stream = resolver.openInputStream(uri);
            stream.close();
            return false;
        } catch (FileNotFoundException e) {
            if (e.getCause() instanceof ErrnoException) {
                return true;
            }
        } catch (Exception e) {
        }
        return false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {

            Uri imageUri = getPickImageResultUri(data);
            Crop.setVisibility(View.VISIBLE);
            getWindow().setSoftInputMode(
                    WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
            );
            boolean requirePermissions = false;
            if (!requirePermissions) {

                mCropImageView.setImageUriAsync(imageUri);
            }
            if (resultCode == 0) {

                Crop.setVisibility(View.GONE);
            }


        }
    }

    public void raiseRuntimePermisionForLocation() {
        if (ContextCompat.checkSelfPermission(RegistrationUserDetailsActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(RegistrationUserDetailsActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(RegistrationUserDetailsActivity.this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            } else {

                ActivityCompat.requestPermissions(RegistrationUserDetailsActivity.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        1);

            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                } else {

                }
                return;
            }
        }
        if (mCropImageUri != null && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            mCropImageView.setImageUriAsync(mCropImageUri);
        } else {
            if (getApplicationContext() != null) {
                Toast.makeText(this, "Required permissions are not granted", Toast.LENGTH_LONG).show();
            }
        }
    }


    public void saveIntoFile(Bitmap bitmap) throws IOException {
        Random random = new Random();
        int ii = 100000;
        ii = random.nextInt(ii);
        String fname = "MyPic_" + ii + ".png";
        File direct = new File(Environment.getExternalStorageDirectory() + "/WishList");

        if (!direct.exists()) {
            File wallpaperDirectory = new File("/sdcard/WishList/");
            wallpaperDirectory.mkdirs();
        }

        File mainfile = new File(new File("/sdcard/WishList/"), fname);
        if (mainfile.exists()) {
            mainfile.delete();
        }
        // raiseRuntimePermisionForLocation();
        FileOutputStream fileOutputStream;
        fileOutputStream = new FileOutputStream(mainfile);
        int height = bitmap.getHeight();
        int width = bitmap.getWidth();

        bitmap.compress(Bitmap.CompressFormat.JPEG, 70, fileOutputStream);
        fileOutputStream.flush();
        fileOutputStream.close();
        profileimage.setImageBitmap(bitmap);

        filePath = new File(mainfile.toString());
    }

    @Override
    public void onBackPressed() {

        //startActivity(new Intent(getApplicationContext(), RegistrationActivity.class));

    }

    @Override
    public void setFirstnameError(String fname) {

        new AlertDialogBox(this).validationdialog(fname, 6);

    }

    @Override
    public void setLastnameError(String lname) {

        new AlertDialogBox(this).validationdialog(lname, 7);

    }

    @Override
    public void setNicknameError(String sname) {

        new AlertDialogBox(this).validationdialog(sname, 8);
    }

    @Override
    public void checkNetworkConnection(String connection) {

        //new AlertDialogBox(this).validationdialog(connection, 1);
        try {
            NoNetworkConnection.networkMessage(this, "com.root.wishlist.activities.registration.RegistrationUserDetailsActivity");
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
        if (message.equals("success")) {
            Intent intent = new Intent(this, RegisterationUploadContact.class);
            intent.putExtra("whoseCalling","syncContacts");
            startActivity(intent);
            finish();
            overridePendingTransition(0, 0);
        } else {
            new AlertDialogBox(this).validationdialog(message, 8);
        }
    }

    @Override
    public void focusChecked(int focus) {
        if (focus == 6) {
            userFirstName.requestFocus();
        } else if (focus == 7) {
            userLastName.requestFocus();
        } else if (focus == 8) {
            userScreenName.requestFocus();
        }
    }

    //


}



