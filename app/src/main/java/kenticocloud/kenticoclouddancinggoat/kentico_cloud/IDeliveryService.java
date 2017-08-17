package kenticocloud.kenticoclouddancinggoat.kentico_cloud;

import android.support.annotation.NonNull;

import com.androidnetworking.error.ANError;

import org.json.JSONArray;

import java.io.IOException;

import kenticocloud.kenticoclouddancinggoat.kentico_cloud.query.item.MultipleItemQuery;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.query.item.SingleItemQuery;
import okhttp3.Call;
import okhttp3.ResponseBody;

/**
 * Created by RichardS on 10. 8. 2017.
 */
public interface IDeliveryService{

     void get(String url, @NonNull final IDeliveryService.GetResponseCallback callback) throws  IOException;

     //void getRx(@NonNull final IDeliveryService.GetResponseRxCallback callback);

     interface GetResponseCallback {

          void onResponse(ResponseBody responseBody);

          void onFailure(final Call call, IOException e);
     }

     interface GetResponseRxCallback {

          void onResponse(JSONArray jsonArray);

          void onError(ANError error);
     }


     /**
      * Gets query for multiple items
      */
      MultipleItemQuery items();

     /**
      * Gets query for single item
      * @param {string} codename - Codename of item to retrieve
      */
      SingleItemQuery item();
}
