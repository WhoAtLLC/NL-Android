package com.root.wishlist.util.globalValues.ImagePicker;


import android.app.DownloadManager;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

public class ImagePickUp extends AppCompatActivity {

    Context context;
    protected int LOAD_IMAGE_CAMERA = 0, CROP_IMAGE = 1, LOAD_IMAGE_GALLARY = 2;
    private File pic;
    String[] firstlastName;
    public static final String MULTIPART_FORM_DATA = "multipart/form-data";
    private Uri picUri;
    public File filePath;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        selectImage();
    }

    public void selectImage() {

        final CharSequence[] options = {"Take Photo", "Choose from Gallery"};

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Select Pic Using...");
        builder.setItems(options, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (options[item].equals("Take Photo")) {

                    try {
                        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);

                        pic = new File(Environment.getExternalStorageDirectory(),
                                "tmp_" + String.valueOf(System.currentTimeMillis()) + ".jpg");

                        picUri = Uri.fromFile(pic);

                        cameraIntent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, picUri);

                        cameraIntent.putExtra("return-data", true);
                        startActivityForResult(cameraIntent, LOAD_IMAGE_CAMERA);
                    } catch (ActivityNotFoundException e) {
                        e.printStackTrace();
                    }

                } else if (options[item].equals("Choose from Gallery")) {
                    Intent intent = new Intent(Intent.ACTION_PICK,
                            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(Intent.createChooser(intent, "Select Picture"), LOAD_IMAGE_GALLARY);
                }
            }
        });
        builder.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == LOAD_IMAGE_CAMERA && resultCode == RESULT_OK) {
            CropImage();

        } else if (requestCode == LOAD_IMAGE_GALLARY) {
            if (data != null) {

                picUri = data.getData();
                CropImage();
            }
        } else if (requestCode == CROP_IMAGE) {
            if (data != null) {
                try {
                    // get the returned data
                    Bundle extras = data.getExtras();

                    // get the cropped bitmap
                    Bitmap photo = extras.getParcelable("data");
                    //cameraimageset.setImageBitmap(photo); use for bitmap
                    saveIntoFile(photo);

                    if (pic != null) {
                        if (pic.delete()) {
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void saveIntoFile(Bitmap bitmap) throws IOException {
        try {
            Bitmap bitmap1 = bitmap;
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
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
            filePath = new File(mainfile.toString());
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap1.compress(Bitmap.CompressFormat.JPEG, 100, stream);
            byte[] imageInByte = stream.toByteArray();
            long lengthbmp = imageInByte.length / 1000000;
            System.out.println("image size in mb==" + lengthbmp);

            filePath = new File(mainfile.toString());


            // System.out.println("File path is " + filePath.toString());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    protected void CropImage() {
        try {
            Intent intent = new Intent("com.android.camera.action.CROP");
            intent.setDataAndType(picUri, "image/*");

            intent.putExtra("crop", "true");
            intent.putExtra("outputX", 200);
            intent.putExtra("outputY", 200);
            intent.putExtra("aspectX", 3);
            intent.putExtra("aspectY", 4);
            intent.putExtra("scaleUpIfNeeded", true);
            intent.putExtra("return-data", true);

            startActivityForResult(intent, CROP_IMAGE);

        } catch (ActivityNotFoundException e) {
          /*  Common.showToast(this, "Your device doesn't support the crop action!");*/
        }
    }


    public void getBitmapFromURL(String src) {
        try {
            File direct = new File(Environment.getExternalStorageDirectory()
                    + "/wishlistImage");

            if (!direct.exists()) {
                direct.mkdirs();
            }
            DownloadManager mgr = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
            Uri downloadUri = Uri.parse(src);
            DownloadManager.Request request = new DownloadManager.Request(
                    downloadUri);
            request.setAllowedNetworkTypes(
                    DownloadManager.Request.NETWORK_WIFI
                            | DownloadManager.Request.NETWORK_MOBILE)
                    .setAllowedOverRoaming(false).setTitle("Demo")
                    .setDescription("Something useful. No, really.")
                    .setDestinationInExternalPublicDir("/wishlistImage", src + ".jpg");

            mgr.enqueue(request);
            filePath = new File(Environment.getExternalStorageDirectory() + "/wishlistImage/" + src + ".jpg");

            if (filePath.exists()) {
                //method call
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}
