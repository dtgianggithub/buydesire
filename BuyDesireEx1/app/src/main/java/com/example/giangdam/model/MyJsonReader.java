package com.example.giangdam.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * Created by Giang.Dam on 8/7/2015.
 */
public class MyJsonReader {


    public static String readAll(Reader reader) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        int cp;
        while ((cp = reader.read()) != -1  ){
            stringBuilder.append((char)cp);
        }

        return stringBuilder.toString();
    }

    public static JSONObject readJsonfromUrl(String url) throws IOException, JSONException {
        InputStream inputStream = new URL(url).openStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        String jsonText = readAll(bufferedReader);
        JSONObject jsonObject = new JSONObject(jsonText);
        inputStream.close();
        return  jsonObject;

    }
}
