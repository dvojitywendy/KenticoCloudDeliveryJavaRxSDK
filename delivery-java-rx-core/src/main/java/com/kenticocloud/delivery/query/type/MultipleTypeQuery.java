package com.kenticocloud.delivery.query.type;

import com.kenticocloud.delivery.config.DeliveryClientConfig;
import com.kenticocloud.delivery.models.common.Parameters;
import com.kenticocloud.delivery.models.exceptions.KenticoCloudException;
import com.kenticocloud.delivery.models.type.DeliveryTypeListingResponse;

import org.json.JSONObject;

import java.io.IOException;

import com.kenticocloud.delivery.request.IRequestService;
import io.reactivex.Observable;
import io.reactivex.functions.Function;

public class MultipleTypeQuery extends BaseTypeQuery {

    private static final String URL_PATH = "/types";

    public MultipleTypeQuery( DeliveryClientConfig config, IRequestService requestService) {
        super(config, requestService);
    }

    @Override
    public String getQueryUrl(){
        return this.queryService.getUrl(URL_PATH, parameters);
    }

    public MultipleTypeQuery limitParameter(int limit){
        this.parameters.add(new Parameters.LimitParameter(limit));
        return this;
    }

    public MultipleTypeQuery skipParameter(int skip){
        this.parameters.add(new Parameters.SkipParameter(skip));
        return this;
    }

    // observable
    public Observable<DeliveryTypeListingResponse> get() {
        return this.queryService.<JSONObject>getRequest(this.getQueryUrl())
                .map(new Function<JSONObject, DeliveryTypeListingResponse>() {
                    @Override
                    public DeliveryTypeListingResponse apply(JSONObject jsonObject) throws KenticoCloudException {
                        try {
                            return responseMapService.mapDeliveryMultipleTypesResponse(jsonObject);
                        } catch (IOException ex) {
                            //Log.e(SDKConfig.APP_TAG, ex.getMessage());
                            throw new KenticoCloudException("Could not get types response with error: " + ex.getMessage(), ex);
                        }
                    }
                });
    }
}
