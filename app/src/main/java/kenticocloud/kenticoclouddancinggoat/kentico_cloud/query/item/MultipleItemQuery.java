package kenticocloud.kenticoclouddancinggoat.kentico_cloud.query.item;

import android.support.annotation.NonNull;

import com.androidnetworking.common.Priority;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rx2androidnetworking.Rx2AndroidNetworking;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Function;
import kenticocloud.kenticoclouddancinggoat.data.models.Article;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.config.DeliveryClientConfig;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.interfaces.item.item.IContentItem;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.models.common.Filters;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.models.common.Parameters;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.models.item.CloudResponses;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.models.item.DeliveryItemListingResponse;

/**
 * Created by RichardS on 17. 8. 2017.
 */

public final class MultipleItemQuery<T extends IContentItem> extends BaseItemQuery<T> {

    private final String _itemsUrlAction = "/items";

    public MultipleItemQuery(@NonNull DeliveryClientConfig config, Class<T> tClass) {
        super(config, tClass);
    }

    // type
    public MultipleItemQuery<T> type(@NonNull String type){
        this._parameters.add(new Filters.EqualsFilter("system.type", type));
        return this;
    }

    // filters
    public MultipleItemQuery<T> equalsFilter(@NonNull String field, @NonNull String value){
        this._parameters.add(new Filters.EqualsFilter(field, value));
        return this;
    }

    public MultipleItemQuery<T> allFilter(@NonNull String field, @NonNull List<String> values){
        this._parameters.add(new Filters.AllFilter(field, values));
        return this;
    }

    public MultipleItemQuery<T> rangeFilter(@NonNull String field, int lowerValue, int higherValue){
        this._parameters.add(new Filters.RangeFilter(field, lowerValue, higherValue));
        return this;
    }

    public MultipleItemQuery<T> anyFilter(@NonNull String field, @NonNull List<String> values){
        this._parameters.add(new Filters.AnyFilter(field, values));
        return this;
    }

    public MultipleItemQuery<T> containsFilter(@NonNull String field, @NonNull List<String> values){
        this._parameters.add(new Filters.ContainsFilter(field, values));
        return this;
    }

    public MultipleItemQuery<T> inFilter(@NonNull String field, @NonNull List<String> values){
        this._parameters.add(new Filters.Infilter(field, values));
        return this;
    }

    public MultipleItemQuery<T> greaterThanFilter(@NonNull String field, String value){
        this._parameters.add(new Filters.GreaterThanFilter(field, value));
        return this;
    }

    public MultipleItemQuery<T> greaterThanOrEqualFilter(@NonNull String field, String value){
        this._parameters.add(new Filters.GreaterThanOrEqualFilter(field, value));
        return this;
    }

    public MultipleItemQuery<T> lessThanFilter(@NonNull String field, String value){
        this._parameters.add(new Filters.LessThanFilter(field, value));
        return this;
    }

    public MultipleItemQuery<T> lessThanOrEqualFilter(@NonNull String field, String value){
        this._parameters.add(new Filters.LessThanOrEqualFilter(field, value));
        return this;
    }

    // parameters
    public MultipleItemQuery<T> elementsParameter(@NonNull List<String> elements){
        this._parameters.add(new Parameters.ElementsParameter(elements));
        return this;
    }

    public MultipleItemQuery<T> languageParameter(@NonNull String language){
        this._parameters.add(new Parameters.LanguageParameter(language));
        return this;
    }

    public MultipleItemQuery<T> depthParameter(int limit){
        this._parameters.add(new Parameters.DepthParameter(limit));
        return this;
    }

    public MultipleItemQuery<T> limitParameter(int limit){
        this._parameters.add(new Parameters.LimitParameter(limit));
        return this;
    }

    public MultipleItemQuery<T> skipParameter(int skip){
        this._parameters.add(new Parameters.SkipParameter(skip));
        return this;
    }

    public MultipleItemQuery<T> orderParameter(@NonNull String field, @NonNull Parameters.OrderType type){
        this._parameters.add(new Parameters.OrderParameter(field, type));
        return this;
    }

    // url builder
    private String getMultipleItemsUrl(){
        return getUrl(_itemsUrlAction, _parameters);
    }

    // observable
    public Observable<DeliveryItemListingResponse<T>> get(){
        String url = getMultipleItemsUrl();

        return Rx2AndroidNetworking.get(url)
                .setPriority(Priority.MEDIUM)
                .build()
                .getJSONObjectObservable()
                .map(new Function<JSONObject, DeliveryItemListingResponse<T>>() {
                    @Override
                    public DeliveryItemListingResponse<T> apply(JSONObject jsonObject) throws Exception {
                        return _responseMapService.<T>mapItemListingResponse(_tClass, jsonObject);
                    }
                });
    }
}
