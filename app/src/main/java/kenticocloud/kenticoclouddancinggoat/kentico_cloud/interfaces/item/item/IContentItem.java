package kenticocloud.kenticoclouddancinggoat.kentico_cloud.interfaces.item.item;

import android.support.annotation.NonNull;

import org.json.JSONObject;

import java.text.ParseException;
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

     String getStringValue(@NonNull String fieldName);

     int getIntValue(@NonNull String fieldName);

     Date getDateValue(@NonNull String fieldName) throws ParseException;

     void mapProperties() throws ParseException;
}
