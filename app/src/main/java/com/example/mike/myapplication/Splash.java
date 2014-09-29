package com.example.mike.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

/**
 * Created by Mike on 28.09.2014.
 */
public class Splash extends Activity {
    private final int SPLASH_DELAY = 1000;
    @Override
    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.splash);
        new Handler().postDelayed(new Runnable(){
           @Override
        public void run() {
               Intent intent = new Intent(Splash.this, MyActivity.class);
               Splash.this.startActivity(intent);
               Splash.this.finish();
           }
        },SPLASH_DELAY);
    }
}
