package kenticocloud.kenticoclouddancinggoat.kentico_cloud;

import android.support.annotation.NonNull;

import java.io.IOException;
import okhttp3.Call;
import okhttp3.ResponseBody;

/**
 * Created by RichardS on 10. 8. 2017.
 */
public interface IDeliveryService{

     //Request get(String url) throws IOException;

     void get(String url, @NonNull final IDeliveryService.GetResponseCallback callback) throws  IOException;

     interface GetResponseCallback {

          void onResponse(ResponseBody responseBody);

          void onFailure(final Call call, IOException e);
     }
}
