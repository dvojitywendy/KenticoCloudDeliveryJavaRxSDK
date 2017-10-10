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
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.models.common.Filters;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.models.common.OrderType;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.models.common.Parameters;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.models.exceptions.KenticoCloudException;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.models.item.DeliveryItemListingResponse;

public final class MultipleItemQuery<T extends IContentItem> extends BaseItemQuery<T> {

    private final String URL_PATH = "/items";

    public MultipleItemQuery(@NonNull DeliveryClientConfig config) {
        super(config);
    }

    // type
    public MultipleItemQuery<T> type(@NonNull String type){
        this.parameters.add(new Filters.EqualsFilter("system.type", type));
        return this;
    }

    // filters
    public MultipleItemQuery<T> equalsFilter(@NonNull String field, @NonNull String value){
        this.parameters.add(new Filters.EqualsFilter(field, value));
        return this;
    }

    public MultipleItemQuery<T> allFilter(@NonNull String field, @NonNull List<String> values){
        this.parameters.add(new Filters.AllFilter(field, values));
        return this;
    }

    public MultipleItemQuery<T> rangeFilter(@NonNull String field, int lowerValue, int higherValue){
        this.parameters.add(new Filters.RangeFilter(field, lowerValue, higherValue));
        return this;
    }

    public MultipleItemQuery<T> anyFilter(@NonNull String field, @NonNull List<String> values){
        this.parameters.add(new Filters.AnyFilter(field, values));
        return this;
    }

    public MultipleItemQuery<T> containsFilter(@NonNull String field, @NonNull List<String> values){
        this.parameters.add(new Filters.ContainsFilter(field, values));
        return this;
    }

    public MultipleItemQuery<T> inFilter(@NonNull String field, @NonNull List<String> values){
        this.parameters.add(new Filters.Infilter(field, values));
        return this;
    }

    public MultipleItemQuery<T> greaterThanFilter(@NonNull String field, String value){
        this.parameters.add(new Filters.GreaterThanFilter(field, value));
        return this;
    }

    public MultipleItemQuery<T> greaterThanOrEqualFilter(@NonNull String field, String value){
        this.parameters.add(new Filters.GreaterThanOrEqualFilter(field, value));
        return this;
    }

    public MultipleItemQuery<T> lessThanFilter(@NonNull String field, String value){
        this.parameters.add(new Filters.LessThanFilter(field, value));
        return this;
    }

    public MultipleItemQuery<T> lessThanOrEqualFilter(@NonNull String field, String value){
        this.parameters.add(new Filters.LessThanOrEqualFilter(field, value));
        return this;
    }

    // parameters
    public MultipleItemQuery<T> elementsParameter(@NonNull List<String> elements){
        this.parameters.add(new Parameters.ElementsParameter(elements));
        return this;
    }

    public MultipleItemQuery<T> languageParameter(@NonNull String language){
        this.parameters.add(new Parameters.LanguageParameter(language));
        return this;
    }

    public MultipleItemQuery<T> depthParameter(int limit){
        this.parameters.add(new Parameters.DepthParameter(limit));
        return this;
    }

    public MultipleItemQuery<T> limitParameter(int limit){
        this.parameters.add(new Parameters.LimitParameter(limit));
        return this;
    }

    public MultipleItemQuery<T> skipParameter(int skip){
        this.parameters.add(new Parameters.SkipParameter(skip));
        return this;
    }

    public MultipleItemQuery<T> orderParameter(@NonNull String field, @NonNull OrderType type){
        this.parameters.add(new Parameters.OrderParameter(field, type));
        return this;
    }

    // url builder
    private String getMultipleItemsUrl(){
        return getUrl(URL_PATH, parameters);
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
                    public DeliveryItemListingResponse<T> apply(JSONObject jsonObject) throws KenticoCloudException {
                        try {
                            return responseMapService.<T>mapItemListingResponse(jsonObject);
                        } catch (JSONException | IOException | IllegalAccessException ex) {
                            Log.e(SDKConfig.APP_TAG, ex.getMessage());
                            throw new KenticoCloudException("Could not get multiple items response with error: " + ex.getMessage(), ex);
                        }
                    }
                });
    }
}
