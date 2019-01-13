package mlexpert.tanishqsaluja.etrash;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class splashActivity extends AppCompatActivity {
    TextView t1, t2;
    Animation a;

    class myThread extends Thread {
        public void run() {
            try {
                Thread.sleep(3000);
            } catch (Exception e) {
                Log.e("TAG", e.getMessage());
            } finally {
                Intent intent = new Intent(getApplicationContext(), startActivity.class);
                startActivity(intent);
                finish();
            }
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        myThread m = new myThread();
        t1 = findViewById(R.id.text1);
        t2 = findViewById(R.id.text2);
        Typeface tf = Typeface.createFromAsset(getAssets(), "qb.otf");
        a = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        t1.setTypeface(tf);
        t2.setTypeface(tf);
        t1.setAnimation(a);
        t2.setAnimation(a);
        m.start();
    }
}
