package com.example.rkjc.news_app_2;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonUtils {

    public static boolean parseGithub(ArrayList<GithubItem> githubItemList, String jsonString){

        try {
            JSONObject topLevelObject = new JSONObject(jsonString);
            JSONArray itemsArray = topLevelObject.getJSONArray("items");
            //System.out.println("JsonArray size: " + articleArray.length());
            for(int i = 0; i < itemsArray.length(); i++){
                //System.out.println("looping");
                JSONObject itemObject = itemsArray.getJSONObject(i);
                String id = itemObject.getString("id");
                String name = itemObject.getString("name");
                String full_name = itemObject.getString("full_name");
                githubItemList.add(new GithubItem(id, name, full_name));
            }

            return true;
        }
        catch(org.json.JSONException e){
            e.printStackTrace();
        }

        return false;

    }
}
