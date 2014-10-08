package com.example.mike.myapplication;

import android.util.Log;

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
            Log.v("", result.toString());
            JSONObject location  = result.getJSONObject("location");
            JSONObject units     = result.getJSONObject("units");
            JSONObject condition = result.getJSONObject("item").getJSONObject("condition");
            JSONObject wind = result.getJSONObject("wind");
            Double dir = Double.parseDouble(wind.getString("direction"));
            if(dir >= 45 && dir < 135){
                wi.windWord = "North ";
            } else if (dir >= 135 && dir < 225){
                wi.windWord = "West ";
            } else if (dir >=225 && dir < 315) {
                wi.windWord = "South ";
            } else {
                wi.windWord = "East ";
            }

            wi.noWindWeather = "Temperature if there is no wind :" + wind.getString("chill");
            wi.addWindInfo(wind.getString("speed"), wind.getString("direction"));
            wi.setCity(city);  // if translation fails we need default values
            wi.setText(condition.getString("text"));
            wi.setTemp(condition.getString("temp"));

            wi.tUnits = (units.getString("temperature"));
            wi.pUnits = units.getString("pressure");
            wi.dUnits = units.getString("distance");
            wi.sUnits = units.getString("speed");
            String weather = "Weather for today at";
            String fore = "Weather forecast for next few days:";
            wi.windWord += " wind: ";
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
            text.add(wi.dUnits);
            text.add(wi.sUnits);
            text.add(wi.windWord);
            text.add(wi.noWindWeather);
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
                wi.dUnits = trans.get(wi.dUnits);
                wi.sUnits = trans.get(wi.sUnits);
                wi.windWord = trans.get(wi.windWord);
                wi.noWindWeather = trans.get(wi.noWindWeather);
            }

            return wi;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

}
