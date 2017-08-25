package kenticocloud.kenticoclouddancinggoat.kentico_cloud.models;

import android.support.annotation.NonNull;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import kenticocloud.kenticoclouddancinggoat.kentico_cloud.interfaces.item.common.IField;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.interfaces.item.item.IContentItem;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.interfaces.item.item.IContentItemSystemAttributes;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.utils.DateHelper;

/**
 * Created by RichardS on 17. 8. 2017.
 */

public abstract class ContentItem implements IContentItem{

    private IContentItemSystemAttributes _system;
    private List<IField> _elements;

    @Override
    public IContentItemSystemAttributes getSystem() {
        return _system;
    }

    @Override
    public void setContentItemSystemAttributes(@NonNull IContentItemSystemAttributes system) {
        _system = system;
    }

    @Override
    public List<IField> getElements() {
        return _elements;
    }

    @Override
    public void setElements(@NonNull List<IField> elements) {
        _elements = elements;
    }

    @Override
    public IField getField(@NonNull String fieldName) {
        for (IField field: _elements) {
            if (field.getCodename().equalsIgnoreCase(fieldName)){
                return field;
            }
        }

        // no field found
        return null;
    }

    @Override
    public String getStringValue(@NonNull String fieldName) {
        IField field = getField(fieldName);
        return field == null ? null : (String)field.getValue();
    }

    @Override
    public int getIntValue(@NonNull String fieldName) throws NullPointerException{
        IField field = getField(fieldName);

        Object fieldValue = field.getValue();

        if (fieldValue == null){
            return 0;
        }

        return Integer.parseInt(fieldValue.toString());
    }

    @Override
    public double getDoubleValue(@NonNull String fieldName) throws NullPointerException{
        IField field = getField(fieldName);

        Object fieldValue = field.getValue();

        if (fieldValue == null){
            return 0;
        }

        return Double.parseDouble(fieldValue.toString());
    }

    @Override
    public Date getDateValue(@NonNull String fieldName) throws ParseException {
        IField field = getField(fieldName);

        if (field == null){
            return null;
        }

        return DateHelper.parseIso8601((String)field.getValue());
    }

    @Override
    public String getAssetUrl(@NonNull String fieldName) throws JSONException {
        String valueField = "value";
        String urlField = "url";
        IField field = getField(fieldName);

        if (field == null){
            return null;
        }

        JSONObject fieldJson = field.getJsonValue();

        if (fieldJson == null){
            return null;
        }

        JSONArray valueJsonArray = fieldJson.getJSONArray(valueField);

        if (valueJsonArray == null){
            return null;
        }

        // get through all items in array and get the 'url' field
        // there doesn't seem to be better support for this by default...
        for (int i=0; i < valueJsonArray.length(); i++) {
            JSONObject fieldV = valueJsonArray.getJSONObject(i);

            // This is bad - it works only with 1 assets, return array in future
            return fieldV.getString(urlField);
        }

        return null;
    }
}
