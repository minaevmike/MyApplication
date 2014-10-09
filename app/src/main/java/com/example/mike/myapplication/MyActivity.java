package com.example.mike.myapplication;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import com.example.mike.myapplication.quit.AppQuitDialogFragment;
import com.example.mike.myapplication.quit.YesNoQuitListener;


public class MyActivity extends FragmentActivity implements CityList.OnItemSelectedListener, YesNoQuitListener {
    public static String picture_discription = "";
    public static final String LOG_TAG = "WEATHER: ";
    static GetWeather weather = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(LOG_TAG, "onCreate");
        setContentView(R.layout.activity_my);

        //if (savedInstanceState == null) {
        FragmentTransaction fragmentTransaction;
        Log.d(LOG_TAG, "BEFORE IF1");
        if (getSupportFragmentManager().findFragmentById(R.id.container) == null) {
            Log.d(LOG_TAG, "IN IF1");
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.add(R.id.container, new CityList());
            fragmentTransaction.commit();
        }
        Log.d(LOG_TAG, "BEFORE IF2");
        PictureFragment pictureFragment = (PictureFragment) getSupportFragmentManager().findFragmentById(R.id.picture);
        if(getResources().getConfiguration().orientation == 2 &&
                pictureFragment == null)
        {
            Log.d(LOG_TAG, "IN IF2");

            pictureFragment = new PictureFragment();
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.add(R.id.picture, pictureFragment);
            fragmentTransaction.commit();
            pictureFragment.SetPicture(picture_discription);
        }else if(pictureFragment != null){
            pictureFragment.SetPicture(picture_discription);
        }
        Log.d(LOG_TAG, "AFTER IF2");
        //weather = (GetWeather) getLastCustomNonConfigurationInstance();
        if (weather != null){
            Log.d("E", "NOT NULL");
            weather.activity = this;
            if(!weather.getStatus().equals(AsyncTask.Status.FINISHED)) {
                weather.showDialog();
            }
        }
    }

    @Override
    public void onArticleSelected(String city) {
        weather = new GetWeather(this);
        weather.execute(city);

    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(LOG_TAG, "onSaveInstanceState");
    }

    @Override
    protected void onPause() {
        Log.d(LOG_TAG, "onPause");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.d(LOG_TAG, "onStop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d(LOG_TAG, "onDestroy");
        super.onDestroy();
    }
    @Override
    protected void onResume() {
        Log.d(LOG_TAG, "onResume");
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        if(getSupportFragmentManager().getBackStackEntryCount() == 0) {
            new AppQuitDialogFragment().show(getSupportFragmentManager(), AppQuitDialogFragment.TAG);
        }
        else{
            super.onBackPressed();
        }
    }

    @Override
    public void onQuitYes() {
        finish();
        System.exit(0);
    }

    @Override
    public void onQuitNo() {
        /*Nothing*/
    }




}
