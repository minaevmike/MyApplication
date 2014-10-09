package com.example.mike.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Locale;
import java.util.TreeMap;

/**
 * Created by Mike on 28.09.2014.
 */
public class Splash extends Activity {
    private final int SPLASH_DELAY = 1000;
    @Override
    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.splash);
        GetCities cities = new GetCities(Splash.this);
        cities.execute();
    }
}
