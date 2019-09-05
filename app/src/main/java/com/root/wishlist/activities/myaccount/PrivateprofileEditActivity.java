package com.root.wishlist.activities.myaccount;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.system.ErrnoException;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.root.wishlist.activities.MyAccountActivity;
import com.root.wishlist.adapters.CountryCodeAdapret;
import com.root.wishlist.database.SharedDatabase;
import com.root.wishlist.util.globalValues.AlertDialogBox;
import com.root.wishlist.util.globalValues.CountryCodeClass;
import com.root.wishlist.util.globalValues.Constants;
import com.root.wishlist.util.globalValues.NoNetworkConnection;
import com.root.wishlist.util.globalValues.StatusBarTransparent;
import com.root.wishlist.util.globalValues.WishlistProgressDialog;
import com.root.wishlist.pojo.CountryCodeName;
import com.root.wishlist.presentation.profile.PrivateProfileInterface;
import com.root.wishlist.presentation.profile.PrivateProfilePresentation;
import com.root.wishlist.presentation.profile.PublicProfileEditInterface;
import com.root.wishlist.presentation.profile.PublicProfileEditPresentation;
import com.root.wishlist.R;
import com.root.wishlist.view.PrivateProfileView;
import com.root.wishlist.view.PublicProfileEditView;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;


public class PrivateprofileEditActivity extends Activity implements PrivateProfileView, View.OnClickListener, PublicProfileEditView {

    public static final String MULTIPART_FORM_DATA = "multipart/form-data";
    public static final int STORAGE_PERMISSION_CODE = 123;
    public File filePath;
    protected int LOAD_IMAGE_CAMERA = 0, CROP_IMAGE = 1, LOAD_IMAGE_GALLARY = 2;
    PrivateProfileInterface publicProfileInterface;
    String[] position;
    int index = 0;
    PublicProfileEditInterface publicProfileEditInterface;
    ArrayList<String> countryCode = new ArrayList<>();
    ArrayList<String> countryName = new ArrayList<>();
    ArrayList<CountryCodeName> totalCode = new ArrayList<>();
    WishlistProgressDialog wishlistProgressDialog;
    SharedDatabase sharedDatabase;
    Button CropImageViewNo, CropImageView1;
    CropImageView mCropImageView;
    RelativeLayout Crop;
    Uri mCropImageUri;
    CountryCodeAdapret countryCodeAdapret;
    private CircleImageView imageset;
    private ImageView imageclick;
    private HashMap<String, RequestBody> map = new HashMap<>();
    private String myBusinessDiscussion, myBusinessOtherInfo, short_bio, privateProfilePictureUrl;
    private File pic;
    private Uri picUri;
    private LinearLayout submitbuttonclick;
    private int spinnerPosition = 0;
    private EditText firstname;
    private EditText lastname;
    private EditText title;
    private EditText company;
    private EditText phonespinnerid;
    private EditText businessphone;
    private EditText cellphone;
    private EditText email;
    private ListView countrycodenamelist;
    private EditText searchcode;
    private CoordinatorLayout coordinatorLayout;
    private Constants constants;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_privateprofile_edit);
        initialFindViewById();
        raiseRuntimePermisionForLocation();

        constants = new Constants(this);

        wishlistProgressDialog = new WishlistProgressDialog(this);

        StatusBarTransparent.colorStatusbar(PrivateprofileEditActivity.this);
        sharedDatabase = new SharedDatabase(getApplicationContext());
        publicProfileInterface = new PrivateProfilePresentation(getApplicationContext(), PrivateprofileEditActivity.this);
        publicProfileInterface.getPrivateUserProfile();
        publicProfileEditInterface = new PublicProfileEditPresentation(getApplicationContext(), PrivateprofileEditActivity.this);
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
        imageclick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivityForResult(getPickImageChooserIntent(), 200);
            }
        });
        imageset.setOnClickListener(new View.OnClickListener() {
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

    public void initialFindViewById() {
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.main_content);
        this.email = (EditText) findViewById(R.id.email);
        this.cellphone = (EditText) findViewById(R.id.cell_phone);
        this.businessphone = (EditText) findViewById(R.id.business_phone);
        this.phonespinnerid = (EditText) findViewById(R.id.phonespinner_id);
        this.company = (EditText) findViewById(R.id.company);
        this.title = (EditText) findViewById(R.id.title);
        this.lastname = (EditText) findViewById(R.id.last_name);
        this.firstname = (EditText) findViewById(R.id.first_name);
        this.imageclick = (ImageView) findViewById(R.id.imageclick);
        this.imageset = (CircleImageView) findViewById(R.id.imageset);
        submitbuttonclick = (LinearLayout) findViewById(R.id.submitbutton_click);
        imageclick.setOnClickListener(this);
        submitbuttonclick.setOnClickListener(this);
        imageset.setOnClickListener(this);
        phonespinnerid.setOnClickListener(this);
        businessphone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (businessphone.getText().length() >= 7) {
                    businessphone.setBackgroundResource(R.drawable.unselect_edittext);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        try {
            switch (view.getId()) {

                case R.id.submitbutton_click:
                    // validPhoneNumbe();
                    wishlistProgressDialog.dialogShow();
                    hideKeybord();
                    getRequestBody();
                    break;

                case R.id.phonespinner_id:
                    spinner();
                    break;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void validPhoneNumbe() {


        if ((phonespinnerid.getText().length() == 0 && businessphone.getText().length() == 0) || (phonespinnerid.getText().length() > 0 && businessphone.getText().length() >= 7)) {
            wishlistProgressDialog.dialogShow();
            hideKeybord();
            getRequestBody();
        } else if (phonespinnerid.getText().length() == 0 && businessphone.getText().length() > 0) {
            new AlertDialogBox(this).forgetPassword("Please select country code.", 2);
            phonespinnerid.setBackgroundResource(R.drawable.edittext_validation_border);
        } else if (businessphone.getText().length() <= 9) {
            new AlertDialogBox(this).forgetPassword("Please enter valid phone number.", 2);
            businessphone.setBackgroundResource(R.drawable.edittext_validation_border);
            businessphone.requestFocus();
        }

    }

    public void getRequestBody() {
        try {
            if (filePath != null) {
                RequestBody phoneR;
                RequestBody titleR = RequestBody.create(MediaType.parse(MULTIPART_FORM_DATA), title.getText().toString() + "");
                RequestBody first_nameR = RequestBody.create(MediaType.parse(MULTIPART_FORM_DATA), firstname.getText().toString() + "");
                RequestBody last_nameR = RequestBody.create(MediaType.parse(MULTIPART_FORM_DATA), lastname.getText().toString() + "");
                RequestBody short_bioR = RequestBody.create(MediaType.parse(MULTIPART_FORM_DATA), short_bio + "");
                phoneR = RequestBody.create(MediaType.parse(MULTIPART_FORM_DATA), phonespinnerid.getText().toString() + "-" + businessphone.getText().toString() + "");
                RequestBody companyR = RequestBody.create(MediaType.parse(MULTIPART_FORM_DATA), company.getText().toString());
                RequestBody myBusinessDiscussionR = RequestBody.create(MediaType.parse(MULTIPART_FORM_DATA), myBusinessDiscussion);
                RequestBody myBusinessOtherInfoR = RequestBody.create(MediaType.parse(MULTIPART_FORM_DATA), myBusinessOtherInfo);
                RequestBody emailR = RequestBody.create(MediaType.parse(MULTIPART_FORM_DATA), email.getText().toString());
                RequestBody bioR = RequestBody.create(MediaType.parse(MULTIPART_FORM_DATA), short_bio + "");
                RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), filePath);
                MultipartBody.Part imageFileBody = MultipartBody.Part.createFormData("image", filePath.getName(), requestBody);
                map.put("title", titleR);
                map.put("first_name", first_nameR);
                map.put("last_name", last_nameR);
                map.put("short_bio", short_bioR);
                map.put("phone", phoneR);
                map.put("company", companyR);
                map.put("email", emailR);
                map.put("myBusinessDiscussion", myBusinessDiscussionR);
                map.put("myBusinessOtherInfo", myBusinessOtherInfoR);
                map.put("bio", bioR);
                publicProfileEditInterface.editProfile(imageFileBody, map);
            } else {


                //getBitmapFromURL(Constants.BASE_URL + privateProfilePictureUrl);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            wishlistProgressDialog.dismissDialog();
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();
        }
    }

    public void spinner() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.country_code_name);
        this.searchcode = (EditText) dialog.findViewById(R.id.search_code);
        this.countrycodenamelist = (ListView) dialog.findViewById(R.id.country_code_name_list);
        for (int i = 0; i < CountryCodeClass.code.length; i++) {
            totalCode.add(new CountryCodeName(CountryCodeClass.countryName[i].toString(), CountryCodeClass.code[i].toString()));
        }

        //Log.d("dadadad", String.valueOf(totalCode));
        countryCodeAdapret = new CountryCodeAdapret(getApplicationContext(), totalCode);
        countrycodenamelist.setAdapter(countryCodeAdapret);


        searchcode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                countryCodeAdapret.getFilter().filter(charSequence.toString());
                countryCodeAdapret.notifyDataSetChanged();

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        countrycodenamelist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Object MyObject = parent.getAdapter().getItem(position);
                Log.d("object", (String) MyObject);
//                CountryCodeName model=(CountryCodeName)countryCodeAdapret.getItem(position);
//                Log.d("dadadada",model.getCountryCode()+"ddd"+model.getCountryName());
                Log.d("dada", totalCode.get(position).getCountryCode().toString());
                phonespinnerid.setText((String) MyObject);
                dialog.dismiss();

            }
        });
        dialog.show();

    }


    @Override
    public void setPrivateProfilePicture(String privateProfilePicture) {

        String ppp = privateProfilePicture;
        if (ppp.startsWith("uploads")) {
            new SaveImageAsync().execute(constants.BASE_URL + privateProfilePicture);
            Glide.with(getApplicationContext()).load(constants.BASE_URL + privateProfilePicture).into(imageset);
            privateProfilePictureUrl = constants.BASE_URL + privateProfilePicture;
        } else {
            new SaveImageAsync().execute(privateProfilePicture);
            Glide.with(getApplicationContext()).load(privateProfilePicture).into(imageset);
            privateProfilePictureUrl = privateProfilePicture;
        }

    }

    @Override
    public void setPrivateTitle(String privateTitle) {
        title.setText(privateTitle);
    }

    @Override
    public void setPrivateFirstName(String privateFirstName) {
        String[] firstlastName = privateFirstName.split(" ");
        firstname.setText(firstlastName[0]);
        lastname.setText(firstlastName[1]);

    }

    @Override
    public void setPrivatetBio(String privatetBio) {

    }

    @Override
    public void setPrivateEmail(String privateEmail) {
        email.setText(privateEmail);
    }

    @Override
    public void setPrivateComapny(String privateComapny) {
        company.setText(privateComapny);

    }

    @Override
    public void setPrivatePhone(String privatePhone) {
        position = privatePhone.split("-");
        try {
            businessphone.setText(position[1]);
            phonespinnerid.setText(position[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setPrivateShortBio(String shortBio) {
        short_bio = shortBio;

    }

    @Override
    public void setPrivateBusinessDiscussion(String businessDiscussion) {
        myBusinessDiscussion = businessDiscussion;
    }

    @Override
    public void setPrivateBusinessAdditional(String businessAdditional) {
        myBusinessOtherInfo = businessAdditional;
    }

    @Override
    public void setPrivateHandle(String privateHandle) {

    }

    @Override
    public void checknetwork(String connection) {
        // new AlertDialogBox(this).networkMessage(connection);
        // SnackBarMessage.displayMessage(coordinatorLayout, connection);

        try {
            NoNetworkConnection.networkMessage(this, "com.root.wishlist.activities.myaccount.PrivateprofileEditActivity");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
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

    public void raiseRuntimePermisionForLocation() {
        if (ContextCompat.checkSelfPermission(PrivateprofileEditActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(PrivateprofileEditActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(PrivateprofileEditActivity.this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            } else {

                ActivityCompat.requestPermissions(PrivateprofileEditActivity.this,
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            Uri imageUri = getPickImageResultUri(data);
            Crop.setVisibility(View.VISIBLE);
            boolean requirePermissions = false;
            if (!requirePermissions) {
                mCropImageView.setImageUriAsync(imageUri);
            }
            if (resultCode == 0) {

                Crop.setVisibility(View.GONE);
            }


        }
    }

    public void saveIntoFile(Bitmap bitmap) throws IOException {
        Random random = new Random();
        int ii = 100000;
        ii = random.nextInt(ii);
        String fname = "MyPic_" + ii + ".png";
        File direct = new File(Environment.getExternalStorageDirectory() + "/MyFolder");

        if (!direct.exists()) {
            File wallpaperDirectory = new File("/sdcard/MyFolder/");
            wallpaperDirectory.mkdirs();
        }

        File mainfile = new File(new File("/sdcard/MyFolder/"), fname);
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
        imageset.setImageBitmap(bitmap);

        filePath = new File(mainfile.toString());
    }

    public void hideKeybord() {
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    @Override
    public void sucess(String message) {
        wishlistProgressDialog.dismissDialog();
        startActivity(new Intent(getApplicationContext(), MyAccountActivity.class).setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION).putExtra("position", 1));
        finish();
        overridePendingTransition(0, 0);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(getApplicationContext(), MyAccountActivity.class).setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION).putExtra("position", 1));
        finish();
        overridePendingTransition(0, 0);
    }

    class SaveImageAsync extends AsyncTask<String, String, File> {
        URL ImageUrl;
        Bitmap bmImg = null;

        @Override
        protected File doInBackground(String... args) {
            InputStream is = null;
            File imageFile = null;
            try {

                ImageUrl = new URL(args[0]);
                HttpURLConnection conn = (HttpURLConnection) ImageUrl
                        .openConnection();
                conn.setDoInput(true);
                conn.connect();
                is = conn.getInputStream();

                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inPreferredConfig = Bitmap.Config.RGB_565;
                bmImg = BitmapFactory.decodeStream(is, null, options);

            } catch (IOException e) {
                e.printStackTrace();
            }

            try {

                String path = ImageUrl.getPath();
                String idStr = path.substring(path.lastIndexOf('/') + 1);
                File filepath = Environment.getExternalStorageDirectory();
                File dir = new File(filepath.getAbsolutePath()
                        + "/wishList/");
                dir.mkdirs();
                String fileName = idStr;
                File file = new File(dir, fileName);
                FileOutputStream fos = new FileOutputStream(file);
                bmImg.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                fos.flush();
                fos.close();
                imageFile = file;
                MediaScannerConnection.scanFile(getApplicationContext(),
                        new String[]{imageFile.getPath()},
                        new String[]{"image/jpeg"}, null);


            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (is != null) {
                    try {
                        is.close();
                    } catch (Exception e) {
                    }
                }
            }
            return imageFile;
        }

        @Override
        protected void onPostExecute(File imageFile) {
            filePath = imageFile;
        }
    }


}
