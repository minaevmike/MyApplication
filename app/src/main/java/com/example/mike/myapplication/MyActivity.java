package com.example.mike.myapplication;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;


public class MyActivity extends FragmentActivity implements CityList.OnItemSelectedListener {
    //ExecutorService executorService = Executors.newCachedThreadPool();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        if (savedInstanceState == null) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.add(R.id.container,new CityList());
            fragmentTransaction.commit();
        }
    }

    @Override
    public void onArticleSelected(String city) {
        getWeather weather = new getWeather(this);
        weather.execute(city);
        /*ListView list = (ListView) findViewById(R.id.list);
        list.setVisibility(View.INVISIBLE);
        ProgressBar bar = (ProgressBar) findViewById(R.id.progress_bar);
        bar.setVisibility(View.VISIBLE);
        FutureTask<City> task = new FutureTask<City>(new getWeather(city));
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(task);
        City city1 = null;
        try{
            city1 = task.get();
        }catch (Exception e){
            e.printStackTrace();
        }
        executorService.shutdown();
        //City city1 = City.getInstance(city);
        list.setVisibility(View.VISIBLE);
        bar.setVisibility(View.INVISIBLE);
        if(city != null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.container, city1);
            transaction.addToBackStack(null);
            transaction.commit();
        }
        else{
            Log.i("City callble", "NULL");
        }*/
        //DetailFragmet fragmet = DetailFragmet.
        //DetailFragment newFragment = DetailFragment.getInstance(position);
    }
}
