package delivery.query.item;

import delivery.config.DeliveryClientConfig;
import delivery.interfaces.item.item.IContentItem;
import delivery.models.common.Filters;
import delivery.models.common.OrderType;
import delivery.models.common.Parameters;
import delivery.models.exceptions.KenticoCloudException;
import delivery.models.item.DeliveryItemListingResponse;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

public final class MultipleItemQuery<TItem extends IContentItem> extends BaseItemQuery<TItem> {

    /*
    TODO: Move to global config (SDK config?)
     */
    private static final String URL_PATH = "/items";

    public MultipleItemQuery( DeliveryClientConfig config) {
        super(config);
    }

    @Override
    public String getQueryUrl(){
        return this.queryService.getUrl(URL_PATH, parameters);
    }

    // type
    public MultipleItemQuery<TItem> type( String type){
        this.parameters.add(new Filters.EqualsFilter("system.type", type));
        return this;
    }

    // filters
    public MultipleItemQuery<TItem> equalsFilter( String field,  String value){
        this.parameters.add(new Filters.EqualsFilter(field, value));
        return this;
    }

    public MultipleItemQuery<TItem> allFilter( String field,  List<String> values){
        this.parameters.add(new Filters.AllFilter(field, values));
        return this;
    }

    public MultipleItemQuery<TItem> rangeFilter( String field, int lowerValue, int higherValue){
        this.parameters.add(new Filters.RangeFilter(field, lowerValue, higherValue));
        return this;
    }

    public MultipleItemQuery<TItem> anyFilter( String field,  List<String> values){
        this.parameters.add(new Filters.AnyFilter(field, values));
        return this;
    }

    public MultipleItemQuery<TItem> containsFilter( String field,  List<String> values){
        this.parameters.add(new Filters.ContainsFilter(field, values));
        return this;
    }

    public MultipleItemQuery<TItem> inFilter( String field,  List<String> values){
        this.parameters.add(new Filters.Infilter(field, values));
        return this;
    }

    public MultipleItemQuery<TItem> greaterThanFilter( String field, String value){
        this.parameters.add(new Filters.GreaterThanFilter(field, value));
        return this;
    }

    public MultipleItemQuery<TItem> greaterThanOrEqualFilter( String field, String value){
        this.parameters.add(new Filters.GreaterThanOrEqualFilter(field, value));
        return this;
    }

    public MultipleItemQuery<TItem> lessThanFilter( String field, String value){
        this.parameters.add(new Filters.LessThanFilter(field, value));
        return this;
    }

    public MultipleItemQuery<TItem> lessThanOrEqualFilter( String field, String value){
        this.parameters.add(new Filters.LessThanOrEqualFilter(field, value));
        return this;
    }

    // parameters
    public MultipleItemQuery<TItem> elementsParameter( List<String> elements){
        this.parameters.add(new Parameters.ElementsParameter(elements));
        return this;
    }

    public MultipleItemQuery<TItem> languageParameter( String language){
        this.parameters.add(new Parameters.LanguageParameter(language));
        return this;
    }

    public MultipleItemQuery<TItem> depthParameter(int limit){
        this.parameters.add(new Parameters.DepthParameter(limit));
        return this;
    }

    public MultipleItemQuery<TItem> limitParameter(int limit){
        this.parameters.add(new Parameters.LimitParameter(limit));
        return this;
    }

    public MultipleItemQuery<TItem> skipParameter(int skip){
        this.parameters.add(new Parameters.SkipParameter(skip));
        return this;
    }

    public MultipleItemQuery<TItem> orderParameter( String field,  OrderType type){
        this.parameters.add(new Parameters.OrderParameter(field, type));
        return this;
    }

    // observable
    public Observable<DeliveryItemListingResponse<TItem>> get(){
        return Rx2AndroidNetworking.get(getQueryUrl())
                .setPriority(Priority.MEDIUM)
                .build()
                .getJSONObjectObservable()
                .map(new Function<JSONObject, DeliveryItemListingResponse<TItem>>() {
                    @Override
                    public DeliveryItemListingResponse<TItem> apply(JSONObject jsonObject) throws KenticoCloudException {
                        try {
                            return responseMapService.<TItem>mapItemListingResponse(jsonObject);
                        } catch (JSONException | IOException | IllegalAccessException ex) {
                            //Log.e(SDKConfig.APP_TAG, ex.getMessage());
                            throw new KenticoCloudException("Could not get multiple items response with error: " + ex.getMessage(), ex);
                        }
                    }
                });
    }
}
