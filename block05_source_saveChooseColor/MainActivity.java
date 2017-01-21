package fr.centralesupelec.galtier.block05;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v4.view.InputDeviceCompat;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.RelativeLayout;

public class MainActivity extends Activity {
    private static final String COLOR_ID_KEY = "colorId";
    private int colorID;
    private RelativeLayout relativeLayout;

    /* renamed from: fr.centralesupelec.galtier.block05.MainActivity.1 */
    class C01651 implements OnCheckedChangeListener {
        final /* synthetic */ Editor val$editor;

        C01651(Editor editor) {
            this.val$editor = editor;
        }

        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId) {
                case C0166R.id.radioButton_blue /*2131492946*/:
                    MainActivity.this.colorID = -16776961;
                    break;
                case C0166R.id.radioButton_magenta /*2131492947*/:
                    MainActivity.this.colorID = -65281;
                    break;
                case C0166R.id.radioButton_yellow /*2131492948*/:
                    MainActivity.this.colorID = InputDeviceCompat.SOURCE_ANY;
                    break;
                default:
                    MainActivity.this.colorID = -3355444;
                    break;
            }
            MainActivity.this.relativeLayout.setBackgroundColor(MainActivity.this.colorID);
            this.val$editor.putInt(MainActivity.COLOR_ID_KEY, MainActivity.this.colorID);
            this.val$editor.commit();
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0166R.layout.activity_main);
        SharedPreferences preferences = getSharedPreferences("COLOR_PREF", 0);
        Editor editor = preferences.edit();
        this.colorID = -3355444;
        if (preferences.contains(COLOR_ID_KEY)) {
            this.colorID = preferences.getInt(COLOR_ID_KEY, 0);
        }
        this.relativeLayout = (RelativeLayout) findViewById(C0166R.id.relativeLayout);
        this.relativeLayout.setBackgroundColor(this.colorID);
        ((RadioGroup) findViewById(C0166R.id.radioGroup)).setOnCheckedChangeListener(new C01651(editor));
    }
}
