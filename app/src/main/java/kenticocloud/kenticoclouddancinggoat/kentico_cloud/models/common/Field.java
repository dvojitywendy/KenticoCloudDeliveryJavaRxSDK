package kenticocloud.kenticoclouddancinggoat.kentico_cloud.models.common;

import android.support.annotation.NonNull;

import org.json.JSONObject;

import kenticocloud.kenticoclouddancinggoat.kentico_cloud.interfaces.item.common.IField;

/**
 * Created by RichardS on 17. 8. 2017.
 */

public class Field implements IField{

    private String _codename;
    private String _type;
    private String _name;
    private Object _value;
    private JSONObject _fieldJson;

    public Field(@NonNull String codename, @NonNull String type, @NonNull String name, @NonNull String value, @NonNull JSONObject fieldJson){
        _codename = codename;
        _type = type;
        _name = name;
        _value = value;
        _fieldJson = fieldJson;
    }

    public String getType() {
        return _type;
    }

    public String getName() {
        return _name;
    }

    public Object getValue() {
        return _value;
    }

    public JSONObject getJsonValue() {
        return _fieldJson;
    }

    public String getCodename() {
        return _codename;
    }
}
