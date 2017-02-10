package com.puzino.block07_web_pages;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.NumberPicker;

public class MainActivity extends AppCompatActivity {

    NumberPicker mPossible;
    WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get number picker
        mPossible = (NumberPicker) findViewById(R.id.numberPicker);
        //forming array of text to put inside
        String[] possibleStrings = {
                "Android",
                "Checklist",
                "Coursera"
        };
        mPossible.setDisplayedValues(possibleStrings);
        mPossible.setMinValue(0);
        mPossible.setMaxValue(possibleStrings.length - 1);
    }

    /**
     * get parameter from number picker and load webpage
     * @param view - view of the button
     * */
    public void navigate(View view){
        int choosedID = mPossible.getValue();
    }
}
