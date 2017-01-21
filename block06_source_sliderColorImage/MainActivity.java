package fr.centralesupelec.galtier.block06;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.media.TransportMediator;
import android.support.v4.view.MotionEventCompat;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class MainActivity extends Activity {
    private ImageView imageView1;
    private ImageView imageView2;
    private SeekBar seekBar;

    /* renamed from: fr.centralesupelec.galtier.block06.MainActivity.1 */
    class C01651 implements OnSeekBarChangeListener {
        int lastProgress;

        C01651() {
        }

        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            this.lastProgress = progress;
        }

        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        public void onStopTrackingTouch(SeekBar seekBar) {
            MainActivity.this.imageView2.setColorFilter(Color.argb(MotionEventCompat.ACTION_MASK, 0, this.lastProgress, 255 - this.lastProgress));
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0166R.layout.activity_main);
        this.imageView1 = (ImageView) findViewById(C0166R.id.imageView1);
        this.imageView2 = (ImageView) findViewById(C0166R.id.imageView2);
        this.seekBar = (SeekBar) findViewById(C0166R.id.seekBar);
        this.imageView2.setImageResource(C0166R.drawable.ic_drawing);
        this.seekBar.setProgress(TransportMediator.FLAG_KEY_MEDIA_NEXT);
        this.seekBar.setOnSeekBarChangeListener(new C01651());
    }
}
