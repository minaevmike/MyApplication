package com.example.mike.myapplication;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Andrey on 29.09.2014.
 */
public class JsonReqParser {
    private JSONObject reader;

    public JsonReqParser(String data) {
        try {
            reader = new JSONObject(data);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public WeatherInfo info() {
        WeatherInfo wi = new WeatherInfo();
        try {
            JSONObject result  =  reader.getJSONObject("query")
                                        .getJSONObject("results")
                                        .getJSONObject("channel");
            JSONObject location  = result.getJSONObject("location");
            JSONObject units     = result.getJSONObject("units");
            JSONObject condition = result.getJSONObject("item").getJSONObject("condition");

            StringBuilder b = new StringBuilder();
            wi.setCity(location.getString("city"));  // if translation fails we need default values
            wi.setText(condition.getString("text"));
            wi.setTemp(condition.getString("temp"));
            wi.setUnits(units.getString("temperature"));

            String[] text = {location.getString("city"),condition.getString("text")};
            String[] trans = Translater.Translate(text, "en", "ru");
            if(trans != null) {
                wi.setCity(trans[0]);
                wi.setText(trans[1]);
            }

            JSONArray forecast = result.getJSONObject("item").getJSONArray("forecast");

            for(int i = 0; i < forecast.length(); i++) {
                JSONObject c = (JSONObject) forecast.get(i);
                wi.addForecast(c.getString("day"), c.getString("date"), c.getString("low"),
                        c.getString("high"), c.getString("text"));
            }
            return wi;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

}
