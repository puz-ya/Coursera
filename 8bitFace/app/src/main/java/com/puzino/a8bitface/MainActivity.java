package com.puzino.a8bitface;

import android.app.AlertDialog;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Fragment mFragmentImage;
    Fragment mFragmentNumbers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FragmentManager fragmentManager = getSupportFragmentManager();
        //fragment: image with 3 buttons
        mFragmentImage = fragmentManager.findFragmentById(R.id.fragment_image_3buttons);
        //fragment: buttons + \ - , changing ids
        mFragmentNumbers = new MainFragment02Buttons(); //fragmentManager.findFragmentById(R.id.fragment_select_images);

        if(savedInstanceState != null){
            mFragmentImage = fragmentManager.getFragment(savedInstanceState, "fragmentImage");
            mFragmentNumbers = fragmentManager.getFragment(savedInstanceState, "fragmentNumbers");
        }

        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(R.id.fragment_select_images, mFragmentNumbers, "fragmentNum");   //added Tag to fragment
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id){
            //case R.id.action_settings:
//                break;

            case R.id.action_about:

                //special email convertation
                final SpannableString sResult = new SpannableString(getString(R.string.about_message));
                Linkify.addLinks(sResult, Linkify.EMAIL_ADDRESSES);

                //creating view for AlertDialog
                final TextView messageTmp = new TextView(this);
                messageTmp.setText(sResult);
                messageTmp.setTextSize(TypedValue.COMPLEX_UNIT_PT, 10);
                messageTmp.setMovementMethod(LinkMovementMethod.getInstance());

                AlertDialog dialog = new AlertDialog.Builder(MainActivity.this)
                .setTitle(getString(R.string.about_title))
                .setView(messageTmp)
                .setPositiveButton("OK", null)
                .create();

                dialog.show();
                break;

            case R.id.action_exit:
                this.finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        getSupportFragmentManager().putFragment(savedInstanceState,"fragmentImage", mFragmentImage);
        getSupportFragmentManager().putFragment(savedInstanceState,"fragmentNumbers", mFragmentNumbers);

        super.onSaveInstanceState(savedInstanceState);
    }

}
