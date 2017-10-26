package com.kenticocloud.delivery.query.type;



import com.kenticocloud.delivery.config.DeliveryClientConfig;
import com.kenticocloud.delivery.interfaces.item.common.IQueryParameter;
import com.kenticocloud.delivery.query.BaseQuery;
import com.kenticocloud.delivery.request.IRequestService;
import com.kenticocloud.delivery.services.map.ResponseMapService;

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
