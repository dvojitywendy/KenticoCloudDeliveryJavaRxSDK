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
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.models.item.DeliveryItemResponse;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.models.item.RawModels;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.utils.JsonHelper;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.utils.MapHelper;

/**
 * Created by RichardS on 17. 8. 2017.
 */
public class SingleItemQuery extends BaseItemQuery{

    private final String _itemsUrlAction = "/items/";
    private final String _itemCodename;

    public SingleItemQuery(@NonNull DeliveryClientConfig config, @NonNull String itemCodename) {
        super(config);
        _itemCodename = itemCodename;
    }

    // filters
    public void equalsFilter(@NonNull String field, String value){
        this._parameters.add(new Filters.EqualsFilter(field, value));
    }

    // url builder
    private String getSingleItemUrl(){
        String action = _itemsUrlAction + _itemCodename;

        return getUrl(action, _parameters);
    }

    // observable
    public Observable<DeliveryItemResponse> get() {
        String url = getSingleItemUrl();

        return Rx2AndroidNetworking.get(url)
                .setPriority(Priority.MEDIUM)
                .build()
                .getObjectObservable(RawModels.DeliveryItemResponseRaw.class)
                .map(new Function<RawModels.DeliveryItemResponseRaw, DeliveryItemResponse>() {
                    @Override
                    public DeliveryItemResponse<IContentItem> apply(RawModels.DeliveryItemResponseRaw responseRaw) throws Exception {
                        // prepare content item
                        IContentItem mappedItem = null;

                        if (responseRaw.item != null) {
                            // get item
                            RawModels.ContentItemRaw item = responseRaw.item;

                            // get and parse fields
                            List<IField> fields = JsonHelper.getFields(item.elements);

                            // system attributes
                            IContentItemSystemAttributes system = MapHelper.mapSystemAttributes(item.system);

                            // create content item
                            mappedItem = new ContentItem(system, fields);
                        }

                        return new DeliveryItemResponse<IContentItem>(mappedItem);
                    }
                });
    }
}
