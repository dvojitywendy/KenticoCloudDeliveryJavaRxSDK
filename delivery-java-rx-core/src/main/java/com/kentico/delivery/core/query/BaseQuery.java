package com.kentico.delivery.core.query;

import com.kentico.delivery.core.config.DeliveryClientConfig;
import com.kentico.delivery.core.interfaces.item.common.IQueryParameter;
import com.kentico.delivery.core.models.common.IDeliveryResponse;
import com.kentico.delivery.core.request.IRequestService;
import com.kentico.delivery.core.services.IQueryService;
import com.kentico.delivery.core.services.QueryService;
import com.kentico.delivery.core.services.map.ResponseMapService;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

public abstract class BaseQuery {

    protected List<IQueryParameter> parameters = new ArrayList<>();

    protected DeliveryClientConfig config;
    protected ResponseMapService responseMapService;
    protected IQueryService queryService;

    protected BaseQuery(DeliveryClientConfig config, IRequestService requestService){
        this.config = config;
        this.responseMapService = new ResponseMapService(config);
        this.queryService = new QueryService(config, requestService);
    }

    /**
     * Gets url of query action
     * @return Url used to hit Kentico Cloud
     */
    public abstract String getQueryUrl();

    /**
     * Gets observable to fetch IDeliveryResposne from Kentico Cloud
     * @return Observable to get Delivery response
     */
    public abstract Observable<? extends IDeliveryResponse> get();
}
