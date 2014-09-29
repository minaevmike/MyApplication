package com.example.mike.myapplication;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ListView;

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
    @Override
    protected String doInBackground(String... strings) {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return strings[0];
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