package kenticocloud.kenticoclouddancinggoat.kentico_cloud.interfaces.item.item;

import android.support.annotation.NonNull;

import org.json.JSONObject;

import java.util.Date;
import java.util.List;

import kenticocloud.kenticoclouddancinggoat.kentico_cloud.interfaces.item.common.IField;

/**
 * Created by RichardS on 17. 8. 2017.
 */
public interface IContentItem {

     IContentItemSystemAttributes getSystem();

     void setContentItemSystemAttributes(@NonNull IContentItemSystemAttributes system);

     List<IField> getElements();

     void setElements(@NonNull List<IField> elements);

     IField getField(@NonNull String fieldName);

     String GetStringValue(@NonNull String fieldName);

     int GetIntValue(@NonNull String fieldName);

     Date getDateValue(@NonNull String fieldName);
}
