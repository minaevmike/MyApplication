package com.example.mike.myapplication;

import java.util.ArrayList;

/**
 * Created by Andrey on 02.10.2014.
 */
public class WeatherInfo {
    private String city,
                   temp,
                   text,
                   units;

    private ArrayList<ForecastInfo> forecast;

    private class ForecastInfo {
        public String day,
                      date,
                      low,
                      high,
                      text;
    }

    public String getCity() {
        return city;
    }

    public String getTemp() {
        return temp;
    }

    public String getText() {
        return text;
    }

    public String getUnits() {
        return units;
    }

    public ArrayList<ForecastInfo> getForecast() {
        return forecast;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public void setForecast(ArrayList<ForecastInfo> forecast) {
        this.forecast = forecast;
    }
}
