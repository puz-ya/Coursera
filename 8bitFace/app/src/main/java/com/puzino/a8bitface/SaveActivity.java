package com.puzino.a8bitface;

import android.Manifest;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;

import static android.os.Build.VERSION.SDK;

public class SaveActivity extends AppCompatActivity {

    private static final int REQUEST_SAVE = 1503;

    ImageView mImageView;
    Bitmap mBitmap;
    String mFilePath = "tempImage.png";
    String mFullFilePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mImageView = (ImageView) findViewById(R.id.imageView_save);


        FileInputStream inputStream;
        try{
            ContextWrapper cw = new ContextWrapper(this);
            // path to /data/data/yourapp/app_data/imageDir
            File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
            // Create imageDir
            File mypath = new File(directory, mFilePath);

            inputStream = new FileInputStream(mypath);
            mBitmap = BitmapFactory.decodeStream(inputStream);
            mFullFilePath = directory +"/"+ mFilePath;
        }catch (Exception ex){
            Log.i("Data Input", "I/O Error SaveToFile");
            Toast.makeText(this, "Load Failed", Toast.LENGTH_LONG).show();
            return;
        }

        mImageView.setImageBitmap(mBitmap);

    }

    public void saveInternal(View view){

        if (android.os.Build.VERSION.SDK_INT >= 23) {
            requestSinglePermission();
        }else{
            fixMediaDir();
            saveToGallery();
        }

    }

    private void saveToGallery(){

        String newName = "8bitFace";
        Bitmap bitmap = ((BitmapDrawable) mImageView.getDrawable()).getBitmap();
        String saved = MediaStore.Images.Media.insertImage(getContentResolver(), bitmap, newName, "DEMO");

        //rescan gallery folder (even
        MediaScannerConnection.scanFile(this,
                new String[] { newName }, null,
                new MediaScannerConnection.OnScanCompletedListener() {
                    public void onScanCompleted(String path, Uri uri) {
                        Log.i("ExternalStorage", "Scanned " + path + ":");
                        Log.i("ExternalStorage", "-> uri=" + uri);
                    }
                });

        if(saved.isEmpty()){
            Toast.makeText(this, "Something wrong :(", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this, "Saved to gallery!", Toast.LENGTH_LONG).show();
        }
    }

    /** fixing path to external gallery
     * */
    public static boolean fixMediaDir() {

        boolean isCreated = false;

        File sdcard = Environment.getExternalStorageDirectory();
        if (sdcard != null) {
            File mediaDir = new File(sdcard, "DCIM/Camera");
            if (!mediaDir.exists()) {
                isCreated = mediaDir.mkdirs();
            }
        }
        return isCreated;
    }

    private void requestSinglePermission() {
        if (android.os.Build.VERSION.SDK_INT >= 23) {
            String locationPermission = Manifest.permission.WRITE_EXTERNAL_STORAGE;
            int hasPermission = checkSelfPermission(locationPermission);
            String[] permissions = new String[] { locationPermission };
            if (hasPermission != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(permissions, REQUEST_SAVE);
            } else {
                // Phew - we already have permission!
                fixMediaDir();
                saveToGallery();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_SAVE:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Handle permission granted
                    fixMediaDir();
                    saveToGallery();
                } else {
                    // Handle permission denied
                    //abort
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
/** alternative solution
 * */
    /*
    public void addImageToGallery(final String filePath, final Context context) {

        ContentValues values = new ContentValues();

        values.put(MediaStore.Images.Media.DATE_TAKEN, System.currentTimeMillis());
        values.put(MediaStore.Images.Media.MIME_TYPE, "image/png");
        values.put(MediaStore.MediaColumns.DATA, filePath);

        context.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
        //need to update gallery (rescan)
    } */

}
