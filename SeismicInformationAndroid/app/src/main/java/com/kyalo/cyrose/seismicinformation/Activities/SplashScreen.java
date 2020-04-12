package com.kyalo.cyrose.seismicinformation.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import com.kyalo.cyrose.seismicinformation.R;

/*
 * Name: Cyrose Kyalo
 * Matric Number: S1803439
 */

public class SplashScreen extends Activity {

    int SPLASH_SCREEN_TIME_OUT=2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i=new Intent(SplashScreen.this,
                        MainActivity.class);
                //Intent is used to switch from one activity to another.

                startActivity(i);
                //invoke the SecondActivity.

                finish();
                //the current activity will get finished.
            }
        }, SPLASH_SCREEN_TIME_OUT);
    }
}
