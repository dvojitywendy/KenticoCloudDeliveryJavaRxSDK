package kenticocloud.kenticoclouddancinggoat.kentico_cloud.query.item;

import android.support.annotation.NonNull;

import com.androidnetworking.common.Priority;
import com.rx2androidnetworking.Rx2AndroidNetworking;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Function;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.config.DeliveryClientConfig;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.interfaces.item.item.IContentItem;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.models.common.Filters;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.models.common.Parameters;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.models.item.DeliveryItemListingResponse;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.models.item.RawModels;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

/**
 * Created by RichardS on 17. 8. 2017.
 */

public final class MultipleItemQuery extends BaseItemQuery {

    private final String _itemsUrlAction = "/items";

    public MultipleItemQuery(@NonNull DeliveryClientConfig config) {
        super(config);
    }

    // type
    public MultipleItemQuery type(@NonNull String type){
        this._parameters.add(new Filters.EqualsFilter("system.type", type));
        return this;
    }

    // filters
    public MultipleItemQuery equalsFilter(@NonNull String field, @NonNull String value){
        this._parameters.add(new Filters.EqualsFilter(field, value));
        return this;
    }

    public MultipleItemQuery allFilter(@NonNull String field, @NonNull List<String> values){
        this._parameters.add(new Filters.AllFilter(field, values));
        return this;
    }

    public MultipleItemQuery rangeFilter(@NonNull String field, int lowerValue, int higherValue){
        this._parameters.add(new Filters.RangeFilter(field, lowerValue, higherValue));
        return this;
    }

    public MultipleItemQuery anyFilter(@NonNull String field, @NonNull List<String> values){
        this._parameters.add(new Filters.AnyFilter(field, values));
        return this;
    }

    public MultipleItemQuery containsFilter(@NonNull String field, @NonNull List<String> values){
        this._parameters.add(new Filters.ContainsFilter(field, values));
        return this;
    }


    public MultipleItemQuery inFilter(@NonNull String field, @NonNull List<String> values){
        this._parameters.add(new Filters.Infilter(field, values));
        return this;
    }


    public MultipleItemQuery greaterThanFilter(@NonNull String field, String value){
        this._parameters.add(new Filters.GreaterThanFilter(field, value));
        return this;
    }

    public MultipleItemQuery greaterThanOrEqualFilter(@NonNull String field, String value){
        this._parameters.add(new Filters.GreaterThanOrEqualFilter(field, value));
        return this;
    }

    public MultipleItemQuery lessThanFilter(@NonNull String field, String value){
        this._parameters.add(new Filters.LessThanFilter(field, value));
        return this;
    }

    public MultipleItemQuery lessThanOrEqualFilter(@NonNull String field, String value){
        this._parameters.add(new Filters.LessThanOrEqualFilter(field, value));
        return this;
    }

    // parameters
    public MultipleItemQuery elementsParameter(@NonNull List<String> elements){
        this._parameters.add(new Parameters.ElementsParameter(elements));
        return this;
    }

    public MultipleItemQuery languageParameter(@NonNull String language){
        this._parameters.add(new Parameters.LanguageParameter(language));
        return this;
    }

    public MultipleItemQuery depthParameter(int limit){
        this._parameters.add(new Parameters.DepthParameter(limit));
        return this;
    }

    public MultipleItemQuery limitParameter(int limit){
        this._parameters.add(new Parameters.LimitParameter(limit));
        return this;
    }

    public MultipleItemQuery skipParameter(int skip){
        this._parameters.add(new Parameters.SkipParameter(skip));
        return this;
    }

    public MultipleItemQuery orderParameter(@NonNull String field, @NonNull Parameters.OrderType type){
        this._parameters.add(new Parameters.OrderParameter(field, type));
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
                    public DeliveryItemListingResponse apply(RawModels.DeliveryItemListingResponseRaw responseRaw) throws Exception {
                        if (responseRaw.items == null) {
                            return null;
                        }

                        // get mapped items
                        List<IContentItem> items = _itemMapService.mapItems(responseRaw.items);

                        // map items
                        for(IContentItem item: items){
                            item.mapProperties();
                        }

                        return  new DeliveryItemListingResponse(items);
                    }
                });
    }
}
