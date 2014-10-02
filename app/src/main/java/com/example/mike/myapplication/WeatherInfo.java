package com.example.mike.myapplication;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Andrey
 * 02.10.2014.
 */
public class WeatherInfo implements Serializable {
    public static String WEATHER_INFO_TAG = "WEATHER_INFO_TAG";

    private String city,
                   temp,
                   text,
                   units;

    private ArrayList<ForecastInfo> forecast = new ArrayList<ForecastInfo>();
/* A ANDREY PRIVATE VERNUT'

 */
    public class ForecastInfo implements Serializable{
        public String day,
                      date,
                      low,
                      high,
                      text;

        private ForecastInfo(String day, String date, String low, String high, String text) {
            this.day = day;
            this.date = date;
            this.low = low;
            this.high = high;
            this.text = text;
        }
        public String returnStringForecast(){
            return day + "\n" + date + "\n" + low + "\n" + high + "\n" + text + "\n";
        }
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

    public void addForecast(String day, String date, String low, String high, String text) {
        forecast.add(new ForecastInfo(day, date, low, high, text));
    }
}
