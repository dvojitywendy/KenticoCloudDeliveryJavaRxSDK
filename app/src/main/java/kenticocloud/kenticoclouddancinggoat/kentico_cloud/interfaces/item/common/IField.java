package kenticocloud.kenticoclouddancinggoat.kentico_cloud.interfaces.item.common;

import org.json.JSONObject;

/**
 * Created by RichardS on 17. 8. 2017.
 */

public interface IField {
    String getCodename();
    String getType();
    String getName();
    Object getValue();
    JSONObject getJsonValue();
}
