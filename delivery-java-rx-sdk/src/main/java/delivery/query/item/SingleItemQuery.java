package delivery.query.item;

import com.androidnetworking.common.Priority;
import delivery.config.DeliveryClientConfig;
import delivery.config.SDKConfig;
import delivery.interfaces.item.item.IContentItem;
import delivery.models.common.Parameters;
import delivery.models.exceptions.KenticoCloudException;
import delivery.models.item.DeliveryItemResponse;
import com.rx2androidnetworking.Rx2AndroidNetworking;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

public class SingleItemQuery<TItem extends IContentItem> extends BaseItemQuery<TItem> {

    private static final String URL_PATH = "/items/";
    private final String itemCodename;

    public SingleItemQuery( DeliveryClientConfig config,  String itemCodename) {
        super(config);
        this.itemCodename = itemCodename;
    }

    @Override
    public String getQueryUrl(){
        String action = URL_PATH + this.itemCodename;

        return this.queryService.getUrl(action, parameters);
    }

    // parameters
    public SingleItemQuery<TItem> elementsParameter( List<String> elements){
        this.parameters.add(new Parameters.ElementsParameter(elements));
        return this;
    }

    public SingleItemQuery<TItem> languageParameter( String language){
        this.parameters.add(new Parameters.LanguageParameter(language));
        return this;
    }

    public SingleItemQuery<TItem> depthParameter(int limit){
        this.parameters.add(new Parameters.DepthParameter(limit));
        return this;
    }

    // observable
    public Observable<DeliveryItemResponse<TItem>> get() {
        return Rx2AndroidNetworking.get(getQueryUrl())
                .setPriority(Priority.MEDIUM)
                .build()
                .getJSONObjectObservable()
                .map(new Function<JSONObject, DeliveryItemResponse<TItem>>() {
                    @Override
                    public DeliveryItemResponse<TItem> apply(JSONObject jsonObject) throws KenticoCloudException {
                        try {
                            return responseMapService.<TItem>mapItemResponse(jsonObject);
                        } catch (JSONException | IOException | IllegalAccessException ex) {
                            //Log.e(SDKConfig.APP_TAG, ex.getMessage());
                            throw new KenticoCloudException("Could not get item response with error: " + ex.getMessage(), ex);
                        }
                    }
                });
    }
}
