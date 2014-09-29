package com.example.mike.myapplication;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Mike on 29.09.2014.
 */
/*
    text - text to translate
    langFrom - language of text (ex. 'en')
    langTo - language to translate (ex. 'ru')
 */
public class Translater {
    public static String[] Translate(String[] text, String langFrom, String langTo) {
        String translated[] = null;
        String url = "https://translate.yandex.net/api/v1.5/tr.json/translate?" +"key=trnsl.1.1.20140929T164623Z.c8aba05abcd636e6.4d13b3a40770dce5262c2eb97e70045ef98fceae"+
                "&lang=" + langFrom + "-" + langTo;
        for (int i = 0; i < text.length; ++i) {
            url += "&text=" + text[i];
        }
        String answer = Connector.getByUrl(url);
        try {
            JSONObject object = new JSONObject(answer);
            if(Integer.parseInt(object.get("code").toString()) == 200){
                translated =  new String[text.length];
                for(int i = 0; i < text.length; ++i) {
                    translated[i] = object.getJSONArray("text").get(i).toString();
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return translated;
    }


}
