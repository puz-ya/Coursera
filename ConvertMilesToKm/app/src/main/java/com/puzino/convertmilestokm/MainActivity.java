package com.puzino.convertmilestokm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.text.DecimalFormat;
import java.text.Format;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //button 1
    public void convertToMiles(View view){
        EditText ed_km = (EditText) findViewById(R.id.editText_km);
        String sKm = ed_km.getText().toString();
        if(sKm.isEmpty() || sKm.equals(",") || sKm.equals(".")){
            sKm = "0.0";
        }
        double km = Double.valueOf(sKm);
        double miles = km*1.609344;

        //new format of double
        DecimalFormat df = new DecimalFormat("#.##");
        String sMiles = df.format(miles);

        EditText ed_miles = (EditText) findViewById(R.id.editText_miles);
        ed_miles.setText(sMiles);
    }

    //button 2
    public void convertToKm(View view){
        EditText ed_miles = (EditText) findViewById(R.id.editText_miles);
        String sMiles = ed_miles.getText().toString();
        if(sMiles.isEmpty() || sMiles.equals(",") || sMiles.equals(".")){
            sMiles = "0.0";
        }

        double miles = Double.valueOf(sMiles);
        double km = 0.0;
        if(miles != 0.0){
            km = miles / 1.609344;
        };

        //new format of double
        DecimalFormat df = new DecimalFormat("#.##");
        String sKm = df.format(km);

        EditText ed_km = (EditText) findViewById(R.id.editText_km);
        ed_km.setText(sKm);
    }
}
