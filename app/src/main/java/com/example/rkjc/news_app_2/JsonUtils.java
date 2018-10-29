package com.example.rkjc.news_app_2;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

public class JsonUtils {
    private static final String TAG = "### JsonUtils ###";

    public static ArrayList<NewsItem> parseNews(String jsonString){
        ArrayList<NewsItem> newsItemList = new ArrayList<>();
        try {
            JSONObject topLevelObject = new JSONObject(jsonString);
            JSONArray itemsArray = topLevelObject.getJSONArray("articles");

            Log.d(TAG + " parseNews", itemsArray.toString());

            for(int i = 0; i < itemsArray.length(); i++){
                JSONObject itemObject = itemsArray.getJSONObject(i);
                String title = itemObject.getString("title");
                String description = itemObject.getString("description");
                String time = itemObject.getString("publishedAt");
                String url = itemObject.getString("url");
                newsItemList.add(new NewsItem(title, description, time, url));
            }

        }
        catch(JSONException e){
            e.printStackTrace();
        }

        return newsItemList;
    }
}


