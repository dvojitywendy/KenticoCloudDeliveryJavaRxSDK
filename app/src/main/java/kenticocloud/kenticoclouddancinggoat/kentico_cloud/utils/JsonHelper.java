package kenticocloud.kenticoclouddancinggoat.kentico_cloud.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import kenticocloud.kenticoclouddancinggoat.kentico_cloud.interfaces.item.common.IField;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.models.common.Field;

/**
 * Created by RichardS on 17. 8. 2017.
 */

public final class JsonHelper {

    public static List<IField> getFields(Object elementsObject) throws JSONException {
        Gson gson = new GsonBuilder().create();

        // get elements json as string (because we don't know the json fields before hands)
        String elementsString = gson.toJson(elementsObject);

        // get JsonObject out of the elements json again
        JSONObject elementsJson = new JSONObject(elementsString);

        return getFields(elementsJson);
    }

    public static List<IField> getFields(JSONObject elementsJson) throws JSONException {
        List<IField> fields = new ArrayList<IField>();

        Iterator<?> keys = elementsJson.keys();
        while( keys.hasNext() ) {
            String key = (String)keys.next();
            if ( elementsJson.get(key) instanceof JSONObject ) {
                JSONObject fieldJson = (JSONObject)elementsJson.get(key);
                Field field = new Field(
                        key,
                        fieldJson.getString("type"),
                        fieldJson.getString("name"),
                        fieldJson.getString("value")
                );
                fields.add(field);
            }
        }

        return fields;
    }
}
