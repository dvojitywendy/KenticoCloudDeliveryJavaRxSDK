package kenticocloud.kenticoclouddancinggoat.kentico_cloud.query.item;

import android.support.annotation.NonNull;

import com.androidnetworking.common.Priority;
import com.rx2androidnetworking.Rx2AndroidNetworking;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Function;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.config.DeliveryClientConfig;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.interfaces.item.common.IField;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.interfaces.item.item.IContentItem;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.interfaces.item.item.IContentItemSystemAttributes;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.models.ContentItem;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.models.common.Filters;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.models.item.DeliveryItemListingResponse;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.models.item.RawModels;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.utils.JsonHelper;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.utils.MapHelper;

/**
 * Created by RichardS on 17. 8. 2017.
 */

public final class MultipleItemQuery extends BaseItemQuery {

    private final String _itemsUrlAction = "/items";

    public MultipleItemQuery(DeliveryClientConfig config) {
        super(config);
    }

    // type
    public MultipleItemQuery type(@NonNull String type){
        this._parameters.add(new Filters.EqualsFilter("system.type", type));
        return this;
    }

    // filters
    public MultipleItemQuery equalsFilter(@NonNull String field, String value){
        this._parameters.add(new Filters.EqualsFilter(field, value));
        return this;
    }

    // url builder
    private String getMultipleItemsUrl(){
        return getUrl(_itemsUrlAction, _parameters);
    }

    // observable
    public Observable<DeliveryItemListingResponse> get(){
        String url = getMultipleItemsUrl();

        return Rx2AndroidNetworking.get(url)
                .setPriority(Priority.MEDIUM)
                .build()
                .getObjectObservable(RawModels.DeliveryItemListingResponseRaw.class)
                .map(new Function<RawModels.DeliveryItemListingResponseRaw, DeliveryItemListingResponse>() {
                    @Override
                    public DeliveryItemListingResponse apply(RawModels.DeliveryItemListingResponseRaw deliveryItemListingResponseRaw) throws Exception {
                        // prepare a list of item
                        List<IContentItem> items = new ArrayList<IContentItem>();

                        // process each item
                        for(int i = 0; i < deliveryItemListingResponseRaw.items.length; i++){
                            // get item
                            RawModels.ContentItemRaw item = deliveryItemListingResponseRaw.items[i];

                            // get and parse fields
                            List<IField> fields = JsonHelper.getFields(item.elements);

                            // system attributes
                            IContentItemSystemAttributes system = MapHelper.mapSystemAttributes(item.system);

                            // create content item
                            IContentItem mappedItem = new ContentItem(system, fields);

                            // add item
                            items.add(mappedItem);
                        }

                        return new DeliveryItemListingResponse(items);
                    }
                });
    }
}
