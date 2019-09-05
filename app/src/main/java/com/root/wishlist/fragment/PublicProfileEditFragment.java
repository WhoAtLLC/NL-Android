package com.root.wishlist.fragment;

import android.Manifest;
import android.app.Activity;
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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.root.wishlist.R;
import com.root.wishlist.activities.MyAccountActivity;
import com.root.wishlist.activities.myaccount.PublicProfileEditActivity;
import com.root.wishlist.presentation.profile.PrivateProfileInterface;
import com.root.wishlist.presentation.profile.PrivateProfilePresentation;
import com.root.wishlist.presentation.profile.PublicProfileEditInterface;
import com.root.wishlist.presentation.profile.PublicProfileEditPresentation;
import com.root.wishlist.util.globalValues.Constants;
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

public class PublicProfileEditFragment extends Fragment implements View.OnClickListener, PublicProfileEditView, PrivateProfileView {
   /* @BindView(R.id.submitbutton_click)
    LinearLayout submitclcikimage;
    @BindView(R.id.camera_imageset)
    CircleImageView cameraimageset;
    @BindView(R.id.imageclick)
    ImageView cameraimage;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.user_name)
    EditText username;
    @BindView(R.id.counter_professional)
    TextView counterprofessional;
    @BindView(R.id.user_profile)
    EditText userprofile;
    @BindView(R.id.counter_public_bio)
    TextView counterpublicbio;
    @BindView(R.id.public_bio)
    EditText publicbio;
    @BindView(R.id.counter_intro)
    TextView counterintro;
    @BindView(R.id.public_intro)
    EditText publicintro;
    @BindView(R.id.counter_any_category)
    TextView counteranycategory;
    @BindView(R.id.user_titles)
    EditText usertitles;
    @BindView(R.id.user_titles1)
    EditText userTitles1;
    @BindView(R.id.CropImageView)
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
   // Unbinder unbinder;

    public static final int STORAGE_PERMISSION_CODE = 123;
    public File filePath;
    protected int LOAD_IMAGE_CAMERA = 0, CROP_IMAGE = 1, LOAD_IMAGE_GALLARY = 2;
    String[] firstlastName;
    HashMap<String, RequestBody> map = new HashMap<>();
    PrivateProfileInterface publicProfileInterface;
    PublicProfileEditInterface publicProfileEditInterface;
    WishlistProgressDialog wishlistProgressDialog;
    int counter = 0;
    Uri mCropImageUri;
    private String phone, company, title, privateProfilePictureUrl;
    private File pic;
    private Uri picUri;
    private Constants constants;
    public static final String MULTIPART_FORM_DATA = "multipart/form-data";



    LinearLayout submitclcikimage; CircleImageView cameraimageset;ImageView cameraimage;
  TextView counterprofessional,counterintro;  AppBarLayout appbar;
    EditText username;TextView counterpublicbio;  EditText userprofile, publicbio,publicintro,usertitles,userTitles1;
    TextView counteranycategory;
    com.theartofdev.edmodo.cropper.CropImageView mCropImageView;   Button CropImageViewNo;  Button CropImageView1; LinearLayout line; RelativeLayout crop;
    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         view = inflater.inflate(R.layout.fragmentpubliceditprofile, container, false);
     //   unbinder = ButterKnife.bind(this, view);

        initialviewfetchId();
        raiseRuntimePermisionForLocation();
        constants = new Constants(getActivity());

        wishlistProgressDialog = new WishlistProgressDialog(getActivity());
        StatusBarTransparent.colorStatusbar(getActivity());
        publicProfileInterface = new PrivateProfilePresentation(getActivity(), this);
        publicProfileInterface.getPrivateUserProfile();
        publicProfileEditInterface = new PublicProfileEditPresentation(getActivity(), this);


        CropImageViewNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                crop.setVisibility(View.GONE);

            }
        });

        cameraimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivityForResult(getPickImageChooserIntent(), 200);
            }
        });
        cameraimageset.setOnClickListener(new View.OnClickListener() {
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

    public void initialviewfetchId() {
     //   coordinatorLayout = (CoordinatorLayout) findViewById(R.id.main_content);
        usertitles = view. findViewById(R.id.user_titles);
        publicintro = view.findViewById(R.id.public_intro);
        publicbio = view.findViewById(R.id.public_bio);
        userprofile = view.findViewById(R.id.user_profile);
        username = view.findViewById(R.id.user_name);
        cameraimage = view.findViewById(R.id.imageclick);
        line=view.findViewById(R.id.line);
        submitclcikimage = view.findViewById(R.id.submitbutton_click);
        cameraimageset = view.findViewById(R.id.camera_imageset);
        counteranycategory = view.findViewById(R.id.counter_any_category);
        userTitles1=view.findViewById(R.id.user_titles1);
        counterintro = view.findViewById(R.id.counter_intro);
        counterpublicbio = view.findViewById(R.id.counter_public_bio);
        counterprofessional = view.findViewById(R.id.counter_professional);
        mCropImageView = view.findViewById(R.id.CropImageView);
        crop = view.findViewById(R.id.crop);
        CropImageViewNo = view. findViewById(R.id.CropImageViewNo);
        CropImageView1 = view.findViewById(R.id.CropImageView1);
        submitclcikimage.setOnClickListener(this);
        cameraimage.setOnClickListener(this);
        cameraimageset.setOnClickListener(this);
        userprofile.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                counter = charSequence.length();
                if (counter <= 50) {
                    counterprofessional.setText("" + (50 - counter));

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        publicbio.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                counter = charSequence.length();
                if (counter <= 50) {
                    counterpublicbio.setText("" + (50 - counter));

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        publicintro.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                counter = charSequence.length();
                if (counter <= 200) {
                    counterintro.setText("" + (200 - counter));

                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        usertitles.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                counter = charSequence.length();
                if (counter <= 200) {
                    counteranycategory.setText("" + (200 - counter));

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
                    wishlistProgressDialog.dialogShow();
                    hideKeybord();
                    getRequestBody();
                    break;

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void getRequestBody() {
        try {
            if (filePath.exists()) {
                System.out.print("imagepath:-" + filePath);
                RequestBody titleR = RequestBody.create(MediaType.parse(MULTIPART_FORM_DATA), title + "");
                RequestBody first_nameR = RequestBody.create(MediaType.parse(MULTIPART_FORM_DATA), firstlastName[0] + "");
                RequestBody last_nameR = RequestBody.create(MediaType.parse(MULTIPART_FORM_DATA), firstlastName[1] + "");
                RequestBody short_bioR = RequestBody.create(MediaType.parse(MULTIPART_FORM_DATA), userprofile.getText().toString());
                RequestBody phoneR = RequestBody.create(MediaType.parse(MULTIPART_FORM_DATA), phone);
                RequestBody companyR = RequestBody.create(MediaType.parse(MULTIPART_FORM_DATA), company + "");
                RequestBody myBusinessDiscussionR = RequestBody.create(MediaType.parse(MULTIPART_FORM_DATA), publicintro.getText().toString());
                RequestBody myBusinessOtherInfoR = RequestBody.create(MediaType.parse(MULTIPART_FORM_DATA), usertitles.getText().toString());
                RequestBody bioR = RequestBody.create(MediaType.parse(MULTIPART_FORM_DATA), publicbio.getText().toString());
                RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), filePath);
                MultipartBody.Part imageFileBody = MultipartBody.Part.createFormData("image", filePath.getName(), requestBody);
                map.put("title", titleR);
                map.put("first_name", first_nameR);
                map.put("last_name", last_nameR);
                map.put("short_bio", short_bioR);
                map.put("phone", phoneR);
                map.put("company", companyR);
                map.put("myBusinessDiscussion", myBusinessDiscussionR);
                map.put("myBusinessOtherInfo", myBusinessOtherInfoR);
                map.put("bio", bioR);
                publicProfileEditInterface.editProfile(imageFileBody, map);
            } else {

            }
        } catch (Exception ex) {
            wishlistProgressDialog.dismissDialog();
            ex.printStackTrace();
            Toast.makeText(getActivity(), "Something went wrong", Toast.LENGTH_SHORT).show();
        }
    }

    public void hideKeybord() {
        this.getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }


    @Override
    public void setPrivateProfilePicture(String privateProfilePicture) {
        try {
            String ppp = privateProfilePicture;
            if (ppp.startsWith("uploads")) {
                new SaveImageAsync().execute(constants.BASE_URL + privateProfilePicture);
                Glide.with(getActivity()).load(constants.BASE_URL + privateProfilePicture).into(cameraimageset);
                privateProfilePictureUrl = constants.BASE_URL + privateProfilePicture;
            } else {
                new SaveImageAsync().execute(privateProfilePicture);
                Glide.with(getActivity()).load(privateProfilePicture).into(cameraimageset);
                privateProfilePictureUrl = privateProfilePicture;
            }


        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void setPrivateTitle(String privateTitle) {
        title = privateTitle;
    }

    @Override
    public void setPrivateFirstName(String privateFirstName) {

        firstlastName = privateFirstName.split(" ");


    }

    @Override
    public void setPrivatetBio(String privatetBio) {
        publicbio.setText("" + privatetBio);
    }

    @Override
    public void setPrivateEmail(String privateEmail) {

    }

    @Override
    public void setPrivateComapny(String privateComapny) {

        company = privateComapny;


    }

    @Override
    public void setPrivatePhone(String privatePhone) {

        phone = privatePhone;

    }

    @Override
    public void setPrivateShortBio(String shortBio) {
        if (shortBio != null)
            userprofile.setText("" + shortBio);
    }

    @Override
    public void setPrivateBusinessDiscussion(String businessDiscussion) {
        if (businessDiscussion != null)
            publicintro.setText("" + businessDiscussion);
    }

    @Override
    public void setPrivateBusinessAdditional(String businessAdditional) {
        if (businessAdditional != null)
            usertitles.setText("" + businessAdditional);
    }

    @Override
    public void setPrivateHandle(String privateHandle) {
        username.setText(privateHandle);
    }

    @Override
    public void checknetwork(String connection) {
        //SnackBarMessage.displayMessage(coordinatorLayout, connection);

        // new AlertDialogBox(this).networkMessage(connection);
        try {
            NoNetworkConnection.networkMessage(getActivity(), "com.root.wishlist.activities.myaccount.PublicProfileEditActivity");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sucess(String message) {
        wishlistProgressDialog.dismissDialog();
        startActivity(new Intent(getActivity(), MyAccountActivity.class).setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION).putExtra("position", 0));
        getActivity().finish();
        getActivity().overridePendingTransition(0, 0);

    }

    /*@Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(getApplicationContext(), MyAccountActivity.class).setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION).putExtra("position", 0));
        finish();
        overridePendingTransition(0, 0);
    }*/

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
        cameraimageset.setImageBitmap(bitmap);

        filePath = new File(mainfile.toString());
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
                if (!dir.exists()) {
                    dir.mkdirs();
                }

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
        //unbinder.unbind();
    }
}
