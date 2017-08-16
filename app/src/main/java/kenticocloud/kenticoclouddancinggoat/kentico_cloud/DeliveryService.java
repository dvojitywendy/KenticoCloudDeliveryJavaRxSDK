package kenticocloud.kenticoclouddancinggoat.kentico_cloud;

import android.support.annotation.NonNull;

import java.io.IOException;

import kenticocloud.kenticoclouddancinggoat.data.source.cafes.CafesDataSource;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

import static com.google.common.base.Preconditions.checkNotNull;

public class DeliveryService implements IDeliveryService{

    private static DeliveryService INSTANCE;

    public OkHttpClient client;

    public DeliveryService() {
        client = new OkHttpClient();
    }

    public static IDeliveryService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DeliveryService();
        }
        return INSTANCE;
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
