package com.example.mike.myapplication;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.TreeMap;

/**
 * Created by Mike on 29.09.2014.
 */
/*
    text - text to translate
    langFrom - language of text (ex. 'en')
    langTo - language to translate (ex. 'ru')
 */
public class Translater {
    public static TreeMap<String, String> Translate(ArrayList<String> text, String langFrom, String langTo) {
        String translated[] = null;
        String url = "https://translate.yandex.net/api/v1.5/tr.json/translate?" +"key=trnsl.1.1.20140929T164623Z.c8aba05abcd636e6.4d13b3a40770dce5262c2eb97e70045ef98fceae"+
                "&lang=" + langFrom + "-" + langTo;
        for (int i = 0; i < text.size(); ++i) {
            url += "&text=" + text.get(i);
        }
        String answer = Connector.getByUrl(url);
        TreeMap<String, String> trans = new TreeMap<String, String>();
        try {
            JSONObject object = new JSONObject(answer);
            if(Integer.parseInt(object.get("code").toString()) == 200){

                for(int i = 0; i < text.size(); ++i) {
                    trans.put(text.get(i), object.getJSONArray("text").get(i).toString());
                }
            }
            else {
                for(int i = 0; i < text.size(); ++i) {
                    trans.put(text.get(i), text.get(i));
                }
            }
        } catch (Exception e) {
            for(int i = 0; i < text.size(); ++i) {
                trans.put(text.get(i), text.get(i));
            }
            e.printStackTrace();
        }
        return trans;
    }


}
