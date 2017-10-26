package com.kentico.delivery.core.query.type;

import com.kentico.delivery.core.config.DeliveryClientConfig;
import com.kentico.delivery.core.interfaces.item.common.IQueryParameter;
import com.kentico.delivery.core.query.BaseQuery;
import com.kentico.delivery.core.request.IRequestService;
import com.kentico.delivery.core.services.map.ResponseMapService;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseTypeQuery extends BaseQuery {

    protected List<IQueryParameter> parameters = new ArrayList<>();

    protected DeliveryClientConfig config;
    protected ResponseMapService responseMapService;

    protected BaseTypeQuery( DeliveryClientConfig config, IRequestService requestService){
        super(config, requestService);

        this.config = config;
        this.responseMapService = new ResponseMapService(config);
    }

}
