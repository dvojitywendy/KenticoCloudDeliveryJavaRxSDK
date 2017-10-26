package com.kentico.delivery.core.query.type;

import com.kentico.delivery.core.config.DeliveryClientConfig;
import com.kentico.delivery.core.models.exceptions.KenticoCloudException;
import com.kentico.delivery.core.models.type.DeliverySingleTypeResponse;

import org.json.JSONObject;

import java.io.IOException;

import com.kentico.delivery.core.request.IRequestService;
import io.reactivex.Observable;
import io.reactivex.functions.Function;

public class SingleTypeQuery extends BaseTypeQuery {

    private static final String URL_PATH = "/types/";
    private final String typeCodename;

    public SingleTypeQuery(DeliveryClientConfig config, IRequestService requestService, String typeCodename) {
        super(config, requestService);
        this.typeCodename = typeCodename;
    }

    @Override
    public String getQueryUrl(){
        String action = URL_PATH + this.typeCodename;

        return this.queryService.getUrl(action, parameters);
    }

    // observable
    public Observable<DeliverySingleTypeResponse> get() {
        return this.queryService.<JSONObject>getRequest(this.getQueryUrl())
                .map(new Function<JSONObject, DeliverySingleTypeResponse>() {
                    @Override
                    public DeliverySingleTypeResponse apply(JSONObject jsonObject) throws KenticoCloudException {
                        try {
                            return responseMapService.mapDeliverySingleTypeResponse(jsonObject);
                        } catch (IOException ex) {
                            //Log.e(SDKConfig.APP_TAG, ex.getMessage());
                            throw new KenticoCloudException("Could not get type response with error: " + ex.getMessage(), ex);
                        }
                    }
                });
    }
}
