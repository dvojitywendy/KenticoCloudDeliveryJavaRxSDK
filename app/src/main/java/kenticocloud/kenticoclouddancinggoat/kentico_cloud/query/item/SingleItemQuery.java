package kenticocloud.kenticoclouddancinggoat.kentico_cloud.query.item;

import android.support.annotation.NonNull;

import com.androidnetworking.common.Priority;
import com.rx2androidnetworking.Rx2AndroidNetworking;

import org.json.JSONObject;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Function;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.config.DeliveryClientConfig;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.interfaces.item.item.IContentItem;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.models.common.Parameters;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.models.item.CloudResponses;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.models.item.DeliveryItemResponse;

/**
 * Created by RichardS on 17. 8. 2017.
 */
public class SingleItemQuery<T extends IContentItem> extends BaseItemQuery<T> {

    private final String _itemsUrlAction = "/items/";
    private final String _itemCodename;

    public SingleItemQuery(@NonNull DeliveryClientConfig config, @NonNull String itemCodename, Class<T> tClass) {
        super(config, tClass);
        _itemCodename = itemCodename;
    }

    // parameters
    public SingleItemQuery<T> elementsParameter(@NonNull List<String> elements){
        this._parameters.add(new Parameters.ElementsParameter(elements));
        return this;
    }

    public SingleItemQuery<T> languageParameter(@NonNull String language){
        this._parameters.add(new Parameters.LanguageParameter(language));
        return this;
    }

    public SingleItemQuery<T> depthParameter(int limit){
        this._parameters.add(new Parameters.DepthParameter(limit));
        return this;
    }

    // url builder
    private String getSingleItemUrl(){
        String action = _itemsUrlAction + _itemCodename;

        return getUrl(action, _parameters);
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
                    public DeliveryItemResponse<T> apply(JSONObject jsonObject) throws Exception {
                        return _responseMapService.<T>mapItemResponse(_tClass, jsonObject);
                    }
                });
    }
}
