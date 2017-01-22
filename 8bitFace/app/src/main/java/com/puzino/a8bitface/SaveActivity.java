package com.puzino.a8bitface;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class SaveActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        int hat = getIntent().getExtras().getInt("hat", -1);
        int head = getIntent().getExtras().getInt("head", -1);
        int body = getIntent().getExtras().getInt("body", -1);

        if(savedInstanceState != null){
            //
        }

        TextView textView = (TextView) findViewById(R.id.textView_save);
        textView.setText(Integer.toString(hat));

    }

}
