package com.kenticocloud.delivery.query.item;

import android.support.annotation.NonNull;

import com.kenticocloud.delivery.config.DeliveryClientConfig;
import com.kenticocloud.delivery.interfaces.item.item.IContentItem;
import com.kenticocloud.delivery.query.BaseQuery;

abstract class BaseItemQuery<T extends IContentItem> extends BaseQuery {

    BaseItemQuery(@NonNull DeliveryClientConfig config){
        super(config);
    }
}
