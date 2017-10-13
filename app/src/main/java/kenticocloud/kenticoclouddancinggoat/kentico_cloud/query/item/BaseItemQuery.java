package kenticocloud.kenticoclouddancinggoat.kentico_cloud.query.item;

import android.support.annotation.NonNull;

import kenticocloud.kenticoclouddancinggoat.kentico_cloud.config.DeliveryClientConfig;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.interfaces.item.item.IContentItem;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.query.BaseQuery;

abstract class BaseItemQuery<T extends IContentItem> extends BaseQuery {

    BaseItemQuery(@NonNull DeliveryClientConfig config){
        super(config);
    }
}
