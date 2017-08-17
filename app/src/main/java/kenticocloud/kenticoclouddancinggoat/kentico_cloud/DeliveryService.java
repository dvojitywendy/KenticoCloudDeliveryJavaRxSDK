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

    private DeliveryClientConfig _config;

    DeliveryService(DeliveryClientConfig config) {
        _config = config;
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
     */
    public SingleItemQuery item(@NonNull String itemCodename){
        return new SingleItemQuery(_config, itemCodename);
    }
}
