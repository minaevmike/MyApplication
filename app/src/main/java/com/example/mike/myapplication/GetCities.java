package com.example.mike.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import com.example.mike.myapplication.quit.AppQuitDialogFragment;

import java.util.ArrayList;
import java.util.Locale;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

/**
 * Created by Mike on 07.10.2014.
 */
public class GetCities extends AsyncTask<Void, Void, Void> {
    private Activity context;
    GetCities(Activity c){
        context = c;
    }

    @Override
    protected void onPostExecute(Void result){
        Intent intent = new Intent(context, MyActivity.class);
        context.startActivity(intent);
        context.finish();
    }
    @Override
    protected Void doInBackground(Void... params) {

        ArrayList<String> cits = new ArrayList<String>();
        cits.add(AppQuitDialogFragment.Message);
        cits.add(AppQuitDialogFragment.Title);
        cits.add(GetWeather.Getting);
        for(int i = 0; i < CityList.cities.length; i++){
            cits.add(CityList.cities[i]);
        }
        TreeMap<String, String> map = Translater.Translate(cits, "en", Locale.getDefault().getLanguage());
        for(int i = 0; i < CityList.cities.length; i++){
            CityList.cities[i] = map.get(CityList.cities[i]);
        }
        AppQuitDialogFragment.Message = map.get(AppQuitDialogFragment.Message);
        AppQuitDialogFragment.Title = map.get(AppQuitDialogFragment.Title);
        GetWeather.Getting = map.get(GetWeather.Getting);
        return null;
    }
}
