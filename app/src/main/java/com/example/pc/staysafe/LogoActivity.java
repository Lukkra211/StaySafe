package com.example.pc.staysafe;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

/**
 * Shows the logo and Erasmus banner before application really starts
 *
 * Here some time-consuming structures can be loaded or initialized
 */
public class LogoActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo);

        ImageView img_logo = findViewById(R.id.logo);
        Animation anim_welcome = AnimationUtils.loadAnimation(this, R.anim.welcome);
        img_logo.startAnimation(anim_welcome);
        
        this.countdown.start();
    }

    /**
     * Thread waits 3.5 seconds and then finish current Activity and start HomeActivity.
     */
    final Thread countdown = new Thread() {
        public void run() {
            try {
                sleep(3500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            startActivity(new Intent(getBaseContext(), HomeActivity.class));
            finish();
        }
    };
}