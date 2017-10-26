package com.kentico.delivery.core.query.item;

import com.kentico.delivery.core.config.DeliveryClientConfig;
import com.kentico.delivery.core.interfaces.item.item.IContentItem;
import com.kentico.delivery.core.query.BaseQuery;
import com.kentico.delivery.core.request.IRequestService;

abstract class BaseItemQuery<T extends IContentItem> extends BaseQuery {

    BaseItemQuery(DeliveryClientConfig config, IRequestService requestService){
        super(config, requestService);
    }
}
