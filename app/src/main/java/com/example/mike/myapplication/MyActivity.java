package com.example.mike.myapplication;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;


public class MyActivity extends FragmentActivity implements DetailFragmet.OnItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        if (savedInstanceState == null) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.add(R.id.container,new DetailFragmet());
            fragmentTransaction.commit();
            //getFragmentManager().beginTransaction()
              //      .add(R.id.container, new DetailFragmet())
                //    .commit();
        }
    }

    @Override
    public void onArticleSelected(String city) {
        DetailFragmet fragmet = DetailFragmet.getInstanse(city);
        //DetailFragmet fragmet = DetailFragmet.
        //DetailFragment newFragment = DetailFragment.getInstance(position);
    }
}
