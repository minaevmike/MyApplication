package com.example.mike.myapplication;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Locale;
import java.util.TreeMap;


/**
 * Created by Andrey
 * 29.09.2014.
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

    public WeatherInfo info(String city) {
        WeatherInfo wi = new WeatherInfo();
        try {
            JSONObject result  =  reader.getJSONObject("query")
                                        .getJSONObject("results")
                                        .getJSONObject("channel");
            JSONObject location  = result.getJSONObject("location");
            JSONObject units     = result.getJSONObject("units");
            JSONObject condition = result.getJSONObject("item").getJSONObject("condition");

            wi.setCity(city);  // if translation fails we need default values
            wi.setText(condition.getString("text"));
            wi.setTemp(condition.getString("temp"));
            wi.setUnits(units.getString("temperature"));
            String weather = "Weather";
            String fore = "Weather forecast for next few days:";
            //String[] text = {location.getString("city"),condition.getString("text")};
            ArrayList<String> text = new ArrayList<String>();
            text.add(condition.getString("text"));

            JSONArray forecast = result.getJSONObject("item").getJSONArray("forecast");

            for(int i = 0; i < forecast.length(); i++) {
                JSONObject c = (JSONObject) forecast.get(i);
                text.add(c.getString("day"));
                text.add(c.getString("date"));
                text.add(c.getString("text"));
            }
            text.add(fore);
            text.add(weather);
            TreeMap<String, String> trans = Translater.Translate(text, "en", Locale.getDefault().getLanguage());
            if(trans != null) {
                wi.setText(trans.get(condition.getString("text")));
                for(int i = 0; i < forecast.length(); i++){
                    JSONObject c = (JSONObject) forecast.get(i);
                    wi.addForecast(trans.get(c.getString("day")), trans.get(c.getString("date")), c.getString("low"),
                            c.getString("high"), trans.get(c.getString("text")));
                }
                wi.setFore(trans.get(fore));
                wi.setWeatherWord(trans.get(weather));
            }

            return wi;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

}
