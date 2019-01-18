package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {
        Sandwich sandwich = null;
        try {
            JSONObject object = new JSONObject(json);
            JSONObject name = object.getJSONObject("name");
            String mainName = name.getString("mainName");
            JSONArray alsoKnownAs = name.getJSONArray("alsoKnownAs");
            String placeOfOrigin = object.getString("placeOfOrigin");
            String description = object.getString("description");
            String image = object.getString("image");
            JSONArray ingredients = object.getJSONArray("ingredients");
            sandwich = new Sandwich(mainName, jsonToList(alsoKnownAs), placeOfOrigin, description, image, jsonToList(ingredients));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return sandwich;
    }

    static List<String> jsonToList(JSONArray jsonArray) throws JSONException {
        List<String> list = new ArrayList<>();
        for (int i=0; i<jsonArray.length(); i++)
            list.add(jsonArray.getString(i));
        return list;
    }
}
