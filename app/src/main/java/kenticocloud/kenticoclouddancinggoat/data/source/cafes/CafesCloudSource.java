package kenticocloud.kenticoclouddancinggoat.data.source.cafes;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.NonNull;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import kenticocloud.kenticoclouddancinggoat.data.models.Article;
import kenticocloud.kenticoclouddancinggoat.data.models.Cafe;
import kenticocloud.kenticoclouddancinggoat.injection.Injection;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.IDeliveryService;
import okhttp3.Call;
import okhttp3.ResponseBody;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by RichardS on 15. 8. 2017.
 */

public class CafesCloudSource implements CafesDataSource {

    private static CafesCloudSource INSTANCE;

    private IDeliveryService _deliveryService;

    // Prevent direct instantiation.
    private CafesCloudSource(@NonNull Context context) {
        checkNotNull(context);
        _deliveryService = Injection.provideDeliveryService();
    }

    public static CafesCloudSource getInstance(@NonNull Context context) {
        if (INSTANCE == null) {
            INSTANCE = new CafesCloudSource(context);
        }
        return INSTANCE;
    }

    @Override
    public void getCafes(@NonNull final LoadCafesCallback callback) {

        String url = "https://deliver.kenticocloud.com/683771be-aa26-4887-b1b6-482f56418ffd/items?system.type=cafe";
        try {
            // view needs to be updated on the main thread, thats why we wrap it in Runnable and use Handler
            final Handler handler = new Handler();

            _deliveryService.get(url, new IDeliveryService.GetResponseCallback() {
                @Override
                public void onResponse(final ResponseBody responseBody) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                String data = responseBody.string();
                                List<Cafe> cafes = new ArrayList<Cafe>();
                                JSONObject items = new JSONObject(data);
                                JSONArray jArray = items.getJSONArray("items");

                                for (int i = 0; i < jArray.length(); i++) {
                                    JSONObject item = jArray.getJSONObject(i);
                                    JSONObject elements = item.getJSONObject("elements");
                                    JSONObject city = elements.getJSONObject("city");
                                    Cafe cafe = new Cafe(city.getString("value"));
                                    cafes.add(cafe);
                                }

                                callback.onCafesLoaded(cafes);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }

                @Override
                public void onFailure(Call call, IOException e) {
                    // to do - add new callback to load cafes = create base interface for all callbacks
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getCafe(@NonNull String articleId, @NonNull GetCafeCallback callback) {
        Cafe cafe = new Cafe("Single cafe 1");

        callback.onCafeLoaded(cafe);
    }
}
