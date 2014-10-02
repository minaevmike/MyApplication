package com.example.mike.myapplication;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.ListView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

/**
 * Created by Mike on 29.09.2014.
 */


public class GetWeather extends AsyncTask<String, Void, WeatherInfo> {
    FragmentActivity activity;
    public GetWeather(FragmentActivity a){
        activity = a;
    }
    ProgressDialog progressDialog;
    int orientation;
    volatile int isRunning = 0;
    ListView list;
    public void showDialog() {
        list = (ListView) activity.findViewById(R.id.list);
        if(list != null) {
            list.setVisibility(View.INVISIBLE);
        }
        progressDialog = new ProgressDialog(activity);
        progressDialog.setMessage("Getting weather");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.show();
    }
    @Override
    protected void onPreExecute() {
        orientation = activity.getRequestedOrientation();
        Log.i("Orintation", Integer.toString(orientation));
        //activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);
        showDialog();
    }

    protected String getLongLatByName(String name) {
        String json = null;
        String coords = null;
        try {
            //Log.d("URL","http://geocode-maps.yandex.ru/1.x/?geocode=" + URLEncoder.encode(name, "utf-8") + "&format=json");
            json = Connector.getByUrl("http://geocode-maps.yandex.ru/1.x/?geocode=" + URLEncoder.encode(name, "utf-8") + "&format=json");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (json != null) {
            try {
                JSONObject object = new JSONObject(json);
                Log.d("OBJECT", object.toString());
                JSONObject latlong = ((JSONObject)object.getJSONObject("response").getJSONObject("GeoObjectCollection").
                        getJSONArray("featureMember").get(0)).getJSONObject("GeoObject").getJSONObject("Point");
                coords = latlong.getString("pos");
                //Log.d("POSITION", latlong.getString("pos"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return coords;
    }

    @Override
    protected WeatherInfo doInBackground(String... strings) {
        String city = strings[0];
        //getLongLatByName(city);
        //Pair<String, String> coordinates = CityList.mCities.get(city);
        String coordinates = getLongLatByName(city);
        WeatherInfo answer = null;
        if (coordinates != null) {
            String[] coords = coordinates.split(" ");
            String url = "https://simple-weather.p.mashape.com/weatherdata?lat="+ coords[1] + "&lng=" + coords[0];
            Map<String, String> headers = new TreeMap<String, String>();
            headers.put("X-Mashape-Key", "4eDVJxGKn0mshsfKjopUw8qIneivp1OrtWCjsnITSKq0M5yGFJ");
            answer = (new JsonReqParser(Connector.getByUrl(url, headers))).info();
        }
        return answer;
    }


    @Override
    protected void onPostExecute(WeatherInfo result){
        Log.d("", "On post exec");
        if(progressDialog == null){
            Log.d("", "NULL");
        }
        progressDialog.dismiss();
        activity.setRequestedOrientation(orientation);
//        list.setVisibility(View.VISIBLE);
        City city1 = new City();
        Bundle bundle = new Bundle();
        bundle.putSerializable(WeatherInfo.WEATHER_INFO_TAG, result);   // Serializable is slow!!!
        city1.setArguments(bundle);
        if (result != null) {
            FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.container, city1);
            transaction.addToBackStack(null);
            transaction.commit();
        }
        else {
            AlertDialog dialog = Util.createAlertDialog(activity, "Internet", "No internet connection!!!!");
            dialog.show();
        }
    }
}