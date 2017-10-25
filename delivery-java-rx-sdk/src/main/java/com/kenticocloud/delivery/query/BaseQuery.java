package com.kenticocloud.delivery.query;



import com.kenticocloud.delivery.config.DeliveryClientConfig;
import com.kenticocloud.delivery.interfaces.item.common.IQueryParameter;
import com.kenticocloud.delivery.models.common.IDeliveryResponse;
import com.kenticocloud.delivery.request.IRequestService;
import com.kenticocloud.delivery.services.IQueryService;
import com.kenticocloud.delivery.services.QueryService;
import com.kenticocloud.delivery.services.map.ResponseMapService;

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
