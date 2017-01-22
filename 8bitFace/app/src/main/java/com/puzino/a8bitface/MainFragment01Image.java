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
public class MainFragment01Image extends Fragment implements View.OnClickListener{

    private Activity mActivity = null;
    private FragmentInterface mFragmentInterface;


    public MainFragment01Image() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_image_3buttons, container, false);

        // Attach clicks to Minus buttons
        Button button = (Button) view.findViewById(R.id.button_save);
        button.setOnClickListener(this);
        button = (Button) view.findViewById(R.id.button_load);
        button.setOnClickListener(this);
        button = (Button) view.findViewById(R.id.button_share);
        button.setOnClickListener(this);

        return view;
    }

    @Override   //new version, NO (Activity activity) :\
    public void onAttach(Context context){
        super.onAttach(context);

        if(context instanceof Activity){
            mActivity = (Activity) context;

            //get interface
            this.mFragmentInterface = (FragmentInterface) mActivity;
        }
    }

    //list of interfaces
    static interface FragmentInterface{
        void itemSave();
        void itemLoad();
        void itemShare();
    }

    /*
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
    //*/

    // acts when +\- is clicked
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_save:
                if(mFragmentInterface != null){
                    mFragmentInterface.itemSave();
                }
                break;
            case R.id.button_load:
                if(mFragmentInterface != null){
                    mFragmentInterface.itemLoad();
                }
                break;
            case R.id.button_share:
                if(mFragmentInterface != null){
                    mFragmentInterface.itemShare();
                }
                break;
        }
    }
}
