package com.example.mike.myapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
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
        //TextView newsDetailTextView = (TextView) view.findViewById(R.id.city_textview);
        Button understoodButton = (Button) view.findViewById(R.id.understood);
        //TableRow weatherTableRow = (TableRow) view.findViewById(R.id.weather_table);
        TableLayout tableLayout = (TableLayout) view.findViewById(R.id.table_layout);
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
        b.append(wi.getWeatherWord()).append(wi.getCity()).append(": ").append(wi.getTemp()).append(wi.getUnits()).append(", ").
                append(wi.getText()).append("\n\n\n\n").append(wi.getFore()).append("\n\n");
        TableRow tempTableRow = new TableRow(getActivity());
        TextView textView = new TextView(getActivity());
        textView.setText(b.toString());
        tempTableRow.addView(textView);
        ImageView imageView = new ImageView(getActivity());
        imageView.setImageResource(R.drawable.ic_launcher1);
        tempTableRow.addView(imageView);
        tableLayout.addView(tempTableRow);

        ArrayList<WeatherInfo.ForecastInfo> infoArrayList = wi.getForecast();
        for(int i = 0; i < infoArrayList.size(); i++){
            TableRow tempTableRow1 = new TableRow(getActivity());
            TextView textView1 = new TextView(getActivity());
            textView1.setText(infoArrayList.get(i).returnStringForecast());
            ImageView imageView1 = new ImageView(getActivity());
            imageView1.setImageResource(R.drawable.ic_launcher);
            tempTableRow1.addView(textView1);
            tempTableRow1.addView(imageView1);
            tableLayout.addView(tempTableRow1);
        }
    }

}
