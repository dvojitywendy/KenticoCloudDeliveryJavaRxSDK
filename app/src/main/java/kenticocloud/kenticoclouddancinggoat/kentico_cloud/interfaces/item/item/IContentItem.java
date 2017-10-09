package kenticocloud.kenticoclouddancinggoat.kentico_cloud.interfaces.item.item;

import android.support.annotation.NonNull;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import kenticocloud.kenticoclouddancinggoat.kentico_cloud.interfaces.item.common.IField;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.models.elements.ContentElement;

/**
 * Created by RichardS on 17. 8. 2017.
 */
public interface IContentItem {

     IContentItemSystemAttributes getSystem();

     void setContentItemSystemAttributes(@NonNull IContentItemSystemAttributes system);

     List<ContentElement> getElements();

     void setElements(@NonNull List<ContentElement> elements);
}
