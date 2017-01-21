package fr.centralesupelec.galtier.block04;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Switch;

public class MainActivity extends Activity {
    MediaPlayer pianoMusic;

    /* renamed from: fr.centralesupelec.galtier.block04.MainActivity.1 */
    class C01651 implements OnCheckedChangeListener {
        C01651() {
        }

        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            MainActivity.this.pianoMusic.setLooping(isChecked);
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0166R.layout.activity_main);
        ((Switch) findViewById(C0166R.id.switch_looping)).setOnCheckedChangeListener(new C01651());
        this.pianoMusic = MediaPlayer.create(this, C0166R.raw.piano);
    }

    public void playMusic(View v) {
        this.pianoMusic.start();
    }

    public void pauseMusic(View v) {
        if (this.pianoMusic.isPlaying()) {
            this.pianoMusic.pause();
        }
    }
}
