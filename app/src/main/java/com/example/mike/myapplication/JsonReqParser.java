package com.example.mike.myapplication;

import org.json.JSONException;
import org.json.JSONObject;

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

    public String info() {
        try {
            JSONObject result  =  reader.getJSONObject("query")
                                        .getJSONObject("results")
                                        .getJSONObject("channel");
            JSONObject location  = result.getJSONObject("location");
            JSONObject units     = result.getJSONObject("units");
            JSONObject condition = result.getJSONObject("item").getJSONObject("condition");

            StringBuilder b = new StringBuilder();
            String[] text = {location.getString("city"),condition.getString("text")};
            String[] trans = Translater.Translate(text, "en", "ru");
            if(trans != null) {
                b.append("City: ").append(trans[0]).append("\n")
                        .append("Temperature: ").append(condition.getString("temp")).append(units.getString("temperature")).append("\n")
                        .append(trans[1]).append("\n");
            }
            return b.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

}
