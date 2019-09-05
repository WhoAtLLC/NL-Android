package com.root.wishlist.util.globalValues;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.os.AsyncTask;
import android.os.Environment;

import com.root.wishlist.database.SharedDatabase;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class SaveImageAsync extends AsyncTask<String, String, File> {
    private Context context;
    URL ImageUrl;
    Bitmap bmImg = null;
    SharedDatabase sharedDatabase;

    public SaveImageAsync(Context context) {
        this.context = context;
        sharedDatabase = new SharedDatabase(context);
    }
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
            MediaScannerConnection.scanFile(context,
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

    }

}