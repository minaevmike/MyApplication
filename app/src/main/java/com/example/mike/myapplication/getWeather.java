package com.example.mike.myapplication;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * Created by Mike on 29.09.2014.
 */


public class getWeather extends AsyncTask<String, Void, String> {
    FragmentActivity activity;
    public getWeather(FragmentActivity a){
        activity = a;
    }
    ProgressDialog progressDialog;
    ListView list;
    @Override
    protected void onPreExecute() {
        list = (ListView) activity.findViewById(R.id.list);
        list.setVisibility(View.INVISIBLE);
        progressDialog = new ProgressDialog(activity);
        progressDialog.setMessage("Getting whether");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    protected void getLongLatByName(String name) {
       /* try {
            URL url = new URL("http://geocode-maps.yandex.ru/1.x/?geocode=&format=json")
        }*/
    }

    @Override
    protected String doInBackground(String... strings) {
        String city = strings[0];
        Pair<String, String> coordinates = CityList.mCities.get(city);
        String answer = null;
        if (coordinates != null){
            try {
                URL url = new URL("https://simple-weather.p.mashape.com/weatherdata?lat="+coordinates.second + "&lng=" + coordinates.first);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setRequestProperty("X-Mashape-Key", "4eDVJxGKn0mshsfKjopUw8qIneivp1OrtWCjsnITSKq0M5yGFJ");
                connection.connect();
                int code = connection.getResponseCode();
                if (code == 200) {
                    InputStream in = connection.getInputStream();

                    answer = (new JsonReqParser(handleInputStream(in))).info();
                }
                connection.disconnect();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }

        return answer;
    }
    private String handleInputStream(InputStream in) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String result = "", line = "";
        while ((line = reader.readLine()) != null) {
            result += line;
        }
        return result;
    }

    @Override
    protected void onPostExecute(String result){
        progressDialog.dismiss();
        list.setVisibility(View.VISIBLE);
        City city1 = new City();
        Bundle bundle = new Bundle();
        bundle.putString(City.CITY, result);
        city1.setArguments(bundle);
        FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.container, city1);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}