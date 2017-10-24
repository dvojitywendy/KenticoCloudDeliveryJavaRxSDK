package delivery.query.item;



import delivery.config.DeliveryClientConfig;
import delivery.interfaces.item.item.IContentItem;
import delivery.query.BaseQuery;

abstract class BaseItemQuery<T extends IContentItem> extends BaseQuery {

    BaseItemQuery( DeliveryClientConfig config){
        super(config);
    }
}
