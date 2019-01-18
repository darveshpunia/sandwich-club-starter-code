package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static final String JSON_OBJ_NAME = "name";
    public static final String JSON_OBJ_MAIN_NAME = "mainName";
    public static final String JSON_OBJ_ALSO_KNOWN_AS = "alsoKnownAs";
    public static final String JSON_OBJ_PLACE_OF_ORIGIN = "placeOfOrigin";
    public static final String JSON_OBJ_DESCRIPTION = "description";
    public static final String JSON_OBJ_IMAGE = "image";
    public static final String JSON_OBJ_INGREDIENTS = "ingredients";

    public static Sandwich parseSandwichJson(String json) {
        Sandwich sandwich = null;
        try {
            JSONObject object = new JSONObject(json);
            JSONObject name = object.optJSONObject(JSON_OBJ_NAME);
            String mainName = name.optString(JSON_OBJ_MAIN_NAME);
            JSONArray alsoKnownAs = name.optJSONArray(JSON_OBJ_ALSO_KNOWN_AS);
            String placeOfOrigin = object.optString(JSON_OBJ_PLACE_OF_ORIGIN);
            String description = object.optString(JSON_OBJ_DESCRIPTION);
            String image = object.optString(JSON_OBJ_IMAGE);
            JSONArray ingredients = object.optJSONArray(JSON_OBJ_INGREDIENTS);
            sandwich = new Sandwich(mainName, jsonToList(alsoKnownAs), placeOfOrigin, description, image, jsonToList(ingredients));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return sandwich;
    }

    private static List<String> jsonToList(JSONArray jsonArray) throws JSONException {
        List<String> list = new ArrayList<>();
        for (int i=0; i<jsonArray.length(); i++)
            list.add(jsonArray.getString(i));
        return list;
    }
}
