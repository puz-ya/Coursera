package com.puzino.a8bitface;

import android.Manifest;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;

public class ShareActivity extends AppCompatActivity {

    private Bitmap mBitmap;
    String mFilePath = "tempImage.png";
    String mFullFilePath;

    private static final int REQUEST_SHARE = 1503;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.title_activity_share);

        setSupportActionBar(toolbar);

        shareSavedTempImage();
    }

    /** fixing path to external gallery
     * */
    public static void fixMediaDir() {
        File sdcard = Environment.getExternalStorageDirectory();
        if (sdcard == null) { return; }

        File dcim = new File(sdcard, "DCIM");
        if (!dcim.exists()) {
            boolean bDCIM = dcim.mkdir();
        }

        File camera = new File(dcim, "Camera");
        if (camera.exists()) { return; }
        boolean bCamera = camera.mkdir();
    }

    public void shareSavedTempImage(){
        if (android.os.Build.VERSION.SDK_INT >= 23) {
            requestSinglePermission();
        }else{
            fixMediaDir();
            shareToOtherApps();
        }
    }

    public void shareToOtherApps(){
        FileInputStream inputStream;
        File mypath;
        try{
            ContextWrapper cw = new ContextWrapper(this);
            // path to /data/data/yourapp/app_data/imageDir
            File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
            // Create imageDir
            mypath = new File(directory, mFilePath);

            inputStream = new FileInputStream(mypath);
            mBitmap = BitmapFactory.decodeStream(inputStream);
            mFullFilePath = directory +"/"+ mFilePath;
        }catch (Exception ex){
            Log.i("Data Input", "I/O Error SaveToFile");
            Toast.makeText(this, "Load Failed", Toast.LENGTH_LONG).show();
            return;
        }

        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);

        //to share we must store temp image in MediaStore for access from other apps
        String path = MediaStore.Images.Media.insertImage(getContentResolver(), mBitmap, "TEMP", "DEMO");

        shareIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse(path));
        shareIntent.setType("image/png");

        // Create intent to show the chooser dialog
        Intent chooser = Intent.createChooser(shareIntent, getResources().getText(R.string.send_to));

        // Verify the original intent will resolve to at least one activity
        if (shareIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(chooser);
        }
    }

    private void requestSinglePermission() {
        if (android.os.Build.VERSION.SDK_INT >= 23) {
            String locationPermission = Manifest.permission.WRITE_EXTERNAL_STORAGE;
            int hasPermission = checkSelfPermission(locationPermission);
            String[] permissions = new String[] { locationPermission };
            if (hasPermission != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(permissions, REQUEST_SHARE);
            } else {
                // Phew - we already have permission!
                fixMediaDir();
                shareToOtherApps();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_SHARE:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Handle permission granted
                    fixMediaDir();
                    shareToOtherApps();
                } else {
                    // Handle permission denied
                    //abort
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
}
