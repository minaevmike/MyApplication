package com.example.mike.myapplication;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * Created by Mike on 29.09.2014.
 */
public class City extends Fragment {
    public static String CITY = "city_name";
    @Deprecated
    public static City getInstance(String city) {
        return null;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.city, null);
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        TextView newsDetailTextview = (TextView) view.findViewById(R.id.city_textview);
        Button understoodButton = (Button) view.findViewById(R.id.understood);
        understoodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().remove(City.this).commit();
            }
        });
        newsDetailTextview.setText("Город " + getArguments().getString(CITY));
    }

}
