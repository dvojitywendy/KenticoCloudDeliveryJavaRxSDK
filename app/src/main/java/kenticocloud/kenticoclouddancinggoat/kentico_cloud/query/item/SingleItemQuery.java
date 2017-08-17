package kenticocloud.kenticoclouddancinggoat.kentico_cloud.query.item;

import android.support.annotation.NonNull;

import com.androidnetworking.common.Priority;
import com.rx2androidnetworking.Rx2AndroidNetworking;

import io.reactivex.Observable;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.config.DeliveryClientConfig;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.models.common.Filters;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.models.item.DeliveryItemResponse;

/**
 * Created by RichardS on 17. 8. 2017.
 */
public class SingleItemQuery extends BaseItemQuery{

    private final String _itemsUrlAction = "/items";

    public SingleItemQuery(DeliveryClientConfig config) {
        super(config);
    }

    // filters
    public void equalsFilter(@NonNull String field, String value){
        this._parameters.add(new Filters.EqualsFilter(field, value));
    }

    // url builder
    private String getSingleItemUrl(){
        return getUrl(_itemsUrlAction, _parameters);
    }

    // observable
    public Observable<DeliveryItemResponse> get(){
        String url = getSingleItemUrl();

        return Rx2AndroidNetworking.get(url)
                .setPriority(Priority.MEDIUM)
                .build()
                .getObjectObservable(DeliveryItemResponse.class);
    }
}
