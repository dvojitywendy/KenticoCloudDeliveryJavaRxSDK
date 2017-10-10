package kenticocloud.kenticoclouddancinggoat.kentico_cloud.query.item;

import android.support.annotation.NonNull;
import android.util.Log;

import com.androidnetworking.common.Priority;
import com.rx2androidnetworking.Rx2AndroidNetworking;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Function;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.config.DeliveryClientConfig;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.config.SDKConfig;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.interfaces.item.item.IContentItem;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.models.common.Parameters;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.models.exceptions.KenticoCloudException;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.models.item.DeliveryItemResponse;

public class SingleItemQuery<T extends IContentItem> extends BaseItemQuery<T> {

    private final String URL_PATH = "/items/";
    private final String itemCodename;

    public SingleItemQuery(@NonNull DeliveryClientConfig config, @NonNull String itemCodename) {
        super(config);
        this.itemCodename = itemCodename;
    }

    // parameters
    public SingleItemQuery<T> elementsParameter(@NonNull List<String> elements){
        this.parameters.add(new Parameters.ElementsParameter(elements));
        return this;
    }

    public SingleItemQuery<T> languageParameter(@NonNull String language){
        this.parameters.add(new Parameters.LanguageParameter(language));
        return this;
    }

    public SingleItemQuery<T> depthParameter(int limit){
        this.parameters.add(new Parameters.DepthParameter(limit));
        return this;
    }

    // url builder
    private String getSingleItemUrl(){
        String action = URL_PATH + this.itemCodename;

        return getUrl(action, parameters);
    }

    // observable
    public Observable<DeliveryItemResponse<T>> get() {
        String url = getSingleItemUrl();

        return Rx2AndroidNetworking.get(url)
                .setPriority(Priority.MEDIUM)
                .build()
                .getJSONObjectObservable()
                .map(new Function<JSONObject, DeliveryItemResponse<T>>() {
                    @Override
                    public DeliveryItemResponse<T> apply(JSONObject jsonObject) throws KenticoCloudException {
                        try {
                            return responseMapService.<T>mapItemResponse(jsonObject);
                        } catch (JSONException | IOException | IllegalAccessException ex) {
                            Log.e(SDKConfig.APP_TAG, ex.getMessage());
                            throw new KenticoCloudException("Could not get item response with error: " + ex.getMessage(), ex);
                        }
                    }
                });
    }
}
