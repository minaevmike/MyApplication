package com.example.mike.myapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * Created by Mike on 29.09.2014.
 */
public class City extends Fragment {
    public static String CITY = "city_name";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.city, null);
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        TextView newsDetailTextView = (TextView) view.findViewById(R.id.city_textview);
        Button understoodButton = (Button) view.findViewById(R.id.understood);
        understoodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().remove(City.this).commit();
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });
        // work to be done here...
        WeatherInfo wi = (WeatherInfo) getArguments().getSerializable(WeatherInfo.WEATHER_INFO_TAG);
        StringBuilder b = new StringBuilder();
        b.append(wi.getCity()).append("\n").append(wi.getText()).append("\n");
        ArrayList<WeatherInfo.ForecastInfo> infoArrayList = wi.getForecast();
        for(int i = 0; i < infoArrayList.size(); i++){
            b.append(infoArrayList.get(i).returnStringForecast());
        }
        newsDetailTextView.setText(b);
    }

}
