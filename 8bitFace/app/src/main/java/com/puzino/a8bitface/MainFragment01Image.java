package com.puzino.a8bitface;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainFragment01Image extends Fragment {

    private Activity mActivity = null;

    public MainFragment01Image() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_image_3buttons, container, false);

        // Listener that acts when +\- is clicked
        View.OnClickListener mListener = new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.button_save:
                        onNewSaveActivity();
                        break;
                    case R.id.button_load:
                        onLoadSavedProperties();
                        break;
                    case R.id.button_share:
                        onShareActivity();
                        break;
                }
            }
        };

        // Attach clicks to Minus buttons
        Button button = (Button) view.findViewById(R.id.button_save);
        button.setOnClickListener(mListener);
        button = (Button) view.findViewById(R.id.button_load);
        button.setOnClickListener(mListener);
        button = (Button) view.findViewById(R.id.button_share);
        button.setOnClickListener(mListener);

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
        Intent intent = new Intent(mActivity, SaveActivity.class);
        startActivity(intent);

    }

    //load last saved configuration
    public void onLoadSavedProperties(){

    }

    //new Activity: share buttons to choose
    public void onShareActivity(){
        Intent intent = new Intent(mActivity, ShareActivity.class);
        startActivity(intent);
    }
}
