package com.root.wishlist.fragment;

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
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.system.ErrnoException;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.root.wishlist.R;
import com.root.wishlist.activities.MyAccountActivity;
import com.root.wishlist.adapters.CountryCodeAdapret;
import com.root.wishlist.database.SharedDatabase;
import com.root.wishlist.pojo.CountryCodeName;
import com.root.wishlist.presentation.profile.PrivateProfileInterface;
import com.root.wishlist.presentation.profile.PrivateProfilePresentation;
import com.root.wishlist.presentation.profile.PublicProfileEditInterface;
import com.root.wishlist.presentation.profile.PublicProfileEditPresentation;
import com.root.wishlist.util.globalValues.AlertDialogBox;
import com.root.wishlist.util.globalValues.Constants;
import com.root.wishlist.util.globalValues.CountryCodeClass;
import com.root.wishlist.util.globalValues.NoNetworkConnection;
import com.root.wishlist.util.globalValues.StatusBarTransparent;
import com.root.wishlist.util.globalValues.WishlistProgressDialog;
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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class PrivateProfileEditFragment extends Fragment implements PrivateProfileView, View.OnClickListener, PublicProfileEditView {






  /* @BindView(R.id.submitbutton_click)
    LinearLayout submitbuttonclick;
    @BindView(R.id.imageset)
    CircleImageView imageset;
    @BindView(R.id.imageclick)
    ImageView imageclick;*/
    /*@BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;
    @BindView(R.id.appbar)
    AppBarLayout appbar;*/
    /*@BindView(R.id.first_name)
    EditText firstname;
    @BindView(R.id.last_name)
    EditText lastname;
    @BindView(R.id.title)
    EditText title;
    @BindView(R.id.company)
    EditText company;
    @BindView(R.id.phonespinner_id)
    EditText phonespinnerid;
    @BindView(R.id.business_phone)
    EditText businessphone;
    @BindView(R.id.cell_phone)
    EditText cellPhone;
    @BindView(R.id.email)
    EditText email;*/
    /*@BindView(R.id.CropImageView)
    com.theartofdev.edmodo.cropper.CropImageView mCropImageView;
    @BindView(R.id.CropImageViewNo)
    Button CropImageViewNo;
    @BindView(R.id.CropImageView1)
    Button CropImageView1;
    @BindView(R.id.line)
    LinearLayout line;
    @BindView(R.id.crop)
    RelativeLayout crop;
    @BindView(R.id.main_content)
    CoordinatorLayout mainContent;*/
   /* Unbinder unbinder;*/

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
    Uri mCropImageUri;
    CountryCodeAdapret countryCodeAdapret;
    private HashMap<String, RequestBody> map = new HashMap<>();
    private String myBusinessDiscussion, myBusinessOtherInfo, short_bio, privateProfilePictureUrl;
    private File pic;
    private Uri picUri;
    private int spinnerPosition = 0;
    private Constants constants;
    private EditText searchcode;
    private ListView countrycodenamelist;


    EditText firstname,lastname,company,phonespinnerid,businessphone,cellPhone,email;
    ImageView imageclick; LinearLayout submitbuttonclick;
    com.theartofdev.edmodo.cropper.CropImageView mCropImageView; EditText title;
   // com.theartofdev.edmodo.cropper.CropImageView mCropImageView;
    Button CropImageViewNo,CropImageView1;  LinearLayout line; RelativeLayout crop; /*CoordinatorLayout mainContent;*/
    CircleImageView imageset;
    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragmentprivateeditprofile, container, false);




       // unbinder = ButterKnife.bind(this, view);






        initialFindViewById();




        raiseRuntimePermisionForLocation();

        constants = new Constants(getActivity());

        wishlistProgressDialog = new WishlistProgressDialog(getActivity());

        StatusBarTransparent.colorStatusbar(getActivity());
        sharedDatabase = new SharedDatabase(getActivity());
        publicProfileInterface = new PrivateProfilePresentation(getActivity(), this);
        publicProfileInterface.getPrivateUserProfile();
        publicProfileEditInterface = new PublicProfileEditPresentation(getActivity(), this);

        CropImageViewNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                crop.setVisibility(View.GONE);

            }
        });
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
                        crop.setVisibility(View.GONE);
                        saveIntoFile(cropped);
                    }
                } catch (NullPointerException e) {
                    e.printStackTrace();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        return view;
    }


    public void initialFindViewById() {

        firstname=view.findViewById(R.id.first_name);
        lastname=view.findViewById(R.id.last_name);
        company=view.findViewById(R.id.company);
        phonespinnerid=view.findViewById(R.id.phonespinner_id);
        businessphone=view.findViewById(R.id.phonespinner_id);
        cellPhone=view.findViewById(R.id.business_phone);
        email=view.findViewById(R.id.email);
        imageclick=view.findViewById(R.id.imageclick);
        submitbuttonclick=view.findViewById(R.id.submitbutton_click);
     imageset=view.findViewById(R.id.imageset);
        mCropImageView=view.findViewById(R.id.CropImageView);

        CropImageViewNo=view.findViewById(R.id.CropImageViewNo);
        imageclick=view.findViewById(R.id.imageclick);
        CropImageView1=view.findViewById(R.id.CropImageView1);
        line=view.findViewById(R.id.line);
        crop=view.findViewById(R.id.crop);
        title=view.findViewById(R.id.title);

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
            new AlertDialogBox(getActivity()).forgetPassword("Please select country code.", 2);
            phonespinnerid.setBackgroundResource(R.drawable.edittext_validation_border);
        } else if (businessphone.getText().length() <= 9) {
            new AlertDialogBox(getActivity()).forgetPassword("Please enter valid phone number.", 2);
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
            Toast.makeText(getActivity(), "Something went wrong", Toast.LENGTH_SHORT).show();
        }
    }

    public void spinner() {
        final Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(getActivity().getWindow().FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.country_code_name);
        this.searchcode = (EditText) dialog.findViewById(R.id.search_code);
        this.countrycodenamelist = (ListView) dialog.findViewById(R.id.country_code_name_list);
        for (int i = 0; i < CountryCodeClass.code.length; i++) {
            totalCode.add(new CountryCodeName(CountryCodeClass.countryName[i].toString(), CountryCodeClass.code[i].toString()));
        }

        //Log.d("dadadad", String.valueOf(totalCode));
        countryCodeAdapret = new CountryCodeAdapret(getActivity(), totalCode);
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
            Glide.with(getActivity()).load(constants.BASE_URL + privateProfilePicture).into(imageset);
            privateProfilePictureUrl = constants.BASE_URL + privateProfilePicture;
        } else {
            new SaveImageAsync().execute(privateProfilePicture);
            Glide.with(getActivity()).load(privateProfilePicture).into(imageset);
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
            NoNetworkConnection.networkMessage(getActivity(), "com.root.wishlist.activities.myaccount.PrivateprofileEditActivity");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        }
    }

    public Intent getPickImageChooserIntent() {
        Uri outputFileUri = getCaptureImageOutputUri();

        List<Intent> allIntents = new ArrayList<>();
        PackageManager packageManager = getActivity().getPackageManager();

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
        File getImage = getActivity().getExternalCacheDir();
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
            ContentResolver resolver = getActivity().getContentResolver();
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
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            } else {

                ActivityCompat.requestPermissions(getActivity(),
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
            if (getActivity() != null) {
                Toast.makeText(getActivity(), "Required permissions are not granted", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            Uri imageUri = getPickImageResultUri(data);
            crop.setVisibility(View.VISIBLE);
            boolean requirePermissions = false;
            if (!requirePermissions) {
                mCropImageView.setImageUriAsync(imageUri);
            }
            if (resultCode == 0) {

                crop.setVisibility(View.GONE);
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
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    @Override
    public void sucess(String message) {
        wishlistProgressDialog.dismissDialog();
        startActivity(new Intent(getActivity(), MyAccountActivity.class).setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION).putExtra("position", 1));
        getActivity().finish();
        getActivity().overridePendingTransition(0, 0);
    }

   /* @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(getApplicationContext(), MyAccountActivity.class).setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION).putExtra("position", 1));
        finish();
        overridePendingTransition(0, 0);
    }*/

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
                MediaScannerConnection.scanFile(getActivity(),
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
      /*  unbinder.unbind();*/
    }
}
