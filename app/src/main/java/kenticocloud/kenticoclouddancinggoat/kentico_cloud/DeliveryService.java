package kenticocloud.kenticoclouddancinggoat.kentico_cloud;

import android.support.annotation.NonNull;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;

import org.json.JSONArray;

import java.io.IOException;

import kenticocloud.kenticoclouddancinggoat.data.source.cafes.CafesDataSource;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.config.DeliveryClientConfig;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.query.item.MultipleItemQuery;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.query.item.SingleItemQuery;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

import static com.google.common.base.Preconditions.checkNotNull;

public class DeliveryService implements IDeliveryService{

    private static DeliveryService INSTANCE;

    private OkHttpClient client;
    private DeliveryClientConfig _config;


    public DeliveryService(DeliveryClientConfig config) {
        _config = config;
        client = new OkHttpClient();
    }

    public static IDeliveryService getInstance(DeliveryClientConfig config) {
        if (INSTANCE == null) {
            INSTANCE = new DeliveryService(config);
        }
        return INSTANCE;
    }


    /**
     * Gets query for multiple items
     */
    public MultipleItemQuery items(){
        return new MultipleItemQuery(_config);
    }

    /**
     * Gets query for single item
     * @param {string} codename - Codename of item to retrieve
     */
    public SingleItemQuery item(){
        return new SingleItemQuery(_config);
    }


    private void getResponse(@NonNull String url, @NonNull final IDeliveryService.GetResponseRxCallback callback){
        AndroidNetworking.get(url)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        callback.onResponse(response);
                    }
                    @Override
                    public void onError(ANError error) {
                        // handle error
                        callback.onError(error);
                    }
                });
    }

    @Override
    public void get(@NonNull String url, @NonNull final IDeliveryService.GetResponseCallback callback) throws IOException {
        client.newCall(getRequest(url))
                .enqueue(new Callback() {
                    @Override
                    public void onFailure(final Call call, IOException e) {
                        // Error
                        callback.onFailure(call, e);
                    }

                    @Override
                    public void onResponse(Call call, final Response response) throws IOException {
                        ResponseBody responseBody = response.body();
                        callback.onResponse(responseBody);
                    }
                });
    }

    private Request getRequest(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

       return request;
    }
}
