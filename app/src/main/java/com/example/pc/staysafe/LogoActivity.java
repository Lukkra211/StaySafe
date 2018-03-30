package com.example.pc.staysafe;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;

/**
 * Shows the logo and Erasmus banner before application really starts
 *
 * Here some time-consuming structures can be loaded or initialized
 * @Autor: Jan Rodak
 * #HonyJeBuh
 */
public class LogoActivity extends Activity {
    /** Delay in milliseconds */
    private int DELAY = 3000;
    private Intent intent;
    /** Animation that slowly change alpha value */
    private Animation fadeIn = new AlphaAnimation(0, 1);
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo);

       /* ImageView img_logo = findViewById(R.id.logo);
        Animation anim_welcome = AnimationUtils.loadAnimation(this, R.anim.welcome);
        img_logo.startAnimation(anim_welcome);*/

       setAnimations();
       inItParms();


        new Handler().postDelayed(TimeHandler, DELAY);
        //this.countdown.start();
    }

    /**
     * Thread waits 3.5 seconds and then finish current Activity and start HomeActivity.

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
    };*/

    /**
     * Initialize animations and link them to the xml objects
     */
    private void inItParms(){
        //this.intent = new Intent(this, OverviewActivity.class);
    }
    private void setAnimations() {
        this.fadeIn.setInterpolator(new DecelerateInterpolator());
        fadeIn.setDuration(DELAY / 2);

        /*
        findViewById(R.id.logo_activity_textView_appName).setAnimation(fadeIn);
        findViewById(R.id.logo_activity_textView_smalltext).setAnimation(fadeIn);*/
    }
    /**
     * Object implementing Runnable - it contains run method that can be executed by Handler object
     */
    private Runnable TimeHandler = new Runnable() {

        @Override
        public void run() {
            startActivity(intent);
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            finish();
        }
    };
}