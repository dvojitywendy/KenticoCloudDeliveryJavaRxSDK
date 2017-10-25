package com.kenticocloud.delivery.query.item;



import com.kenticocloud.delivery.config.DeliveryClientConfig;
import com.kenticocloud.delivery.interfaces.item.item.IContentItem;
import com.kenticocloud.delivery.query.BaseQuery;
import com.kenticocloud.delivery.request.IRequestService;

abstract class BaseItemQuery<T extends IContentItem> extends BaseQuery {

    BaseItemQuery(DeliveryClientConfig config, IRequestService requestService){
        super(config, requestService);
    }
}
