package com.puzino.a8bitface;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by Puzino Yury (YD)
 * A placeholder fragment containing a simple view.
 */
public class MainFragment01Image extends Fragment
{

    private Activity mActivity = null;
    private ImageView mImageView;
    private Bitmap mBitmap;


    public MainFragment01Image() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_image_3buttons, container, false);

        mImageView = (ImageView) getActivity().findViewById(R.id.imageView);

        // Attach clicks to Minus buttons
        Button button = (Button) view.findViewById(R.id.button_save);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNewSaveActivity();
            }
        });

        /* not now
        button = (Button) view.findViewById(R.id.button_load);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onLoadSavedProperties();
            }
        });
        */

        button = (Button) view.findViewById(R.id.button_share);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onShareActivity();
            }
        });

        return view;
    }

    @Override   //new version, NO (Activity activity) :\
    public void onAttach(Context context){
        super.onAttach(context);

        if(context instanceof Activity){
            mActivity = (Activity) context;
        }
    }

    //new Activity: save configuration, save file (internal or SD)
    public void onNewSaveActivity(){

        saveTempImage();

        Intent intent = new Intent(getActivity(), SaveActivity.class);
        //no putExtra with bitmaps (1 MB file restrain)
        getActivity().startActivity(intent);

    }

    /*
    //load last saved configuration
    public void onLoadSavedProperties(){

    }
    //*/

    //new Activity: share buttons to choose
    public void onShareActivity(){

        saveTempImage();

        Intent intent = new Intent(getActivity(), ShareActivity.class);
        startActivity(intent);
    }

    private void saveTempImage(){

        mImageView = (ImageView) getActivity().findViewById(R.id.imageView);
        mBitmap = ((BitmapDrawable) mImageView.getDrawable()).getBitmap();

        //file name, dir path
        String filePath = "tempImage.png";
        FileOutputStream out;
        try {
            ContextWrapper cw = new ContextWrapper(getActivity());
            // path to /data/data/yourapp/app_data/imageDir
            File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
            // Create imageDir
            File mypath=new File(directory, filePath);

            out = new FileOutputStream(mypath);
            mBitmap.compress(Bitmap.CompressFormat.PNG, 90, out);
            out.flush();
            out.close();

        }catch (Exception ex){
            Log.i("Data Output", "I/O Error SaveToFile");
            Toast.makeText(getActivity(), "Save Failed", Toast.LENGTH_LONG).show();
        }
    }

}
