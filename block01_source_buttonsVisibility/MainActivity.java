package fr.centralesupelec.galtier.block01;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
    Button buttonBlueInvisible;
    Button buttonPinkPanther;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0165R.layout.activity_main);
        this.buttonBlueInvisible = (Button) findViewById(C0165R.id.buttonBlueInvisible);
        this.buttonPinkPanther = (Button) findViewById(C0165R.id.buttonPinkPanther);
    }

    public void toDo(View v) {
        if (v.equals(this.buttonBlueInvisible)) {
            this.buttonBlueInvisible.setVisibility(4);
        } else if (v.equals(this.buttonPinkPanther)) {
            Toast.makeText(getApplicationContext(), "to do, to do, to do, to do...", 0).show();
        }
    }
}
