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
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.models.elements.ContentElement;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.utils.DateHelper;

/**
 * Created by RichardS on 17. 8. 2017.
 */

public abstract class ContentItem implements IContentItem {

    private IContentItemSystemAttributes _system;
    private List<ContentElement> _elements;

    @Override
    public IContentItemSystemAttributes getSystem() {
        return _system;
    }

    @Override
    public void setContentItemSystemAttributes(@NonNull IContentItemSystemAttributes system) {
        _system = system;
    }

    @Override
    public List<ContentElement> getElements() {
        return _elements;
    }

    @Override
    public void setElements(@NonNull List<ContentElement> elements) {
        _elements = elements;
    }

}