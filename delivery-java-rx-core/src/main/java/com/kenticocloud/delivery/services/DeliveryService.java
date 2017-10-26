package com.kenticocloud.delivery.services;



import com.kenticocloud.delivery.config.DeliveryClientConfig;
import com.kenticocloud.delivery.interfaces.item.item.IContentItem;
import com.kenticocloud.delivery.query.item.MultipleItemQuery;
import com.kenticocloud.delivery.query.item.SingleItemQuery;
import com.kenticocloud.delivery.query.type.MultipleTypeQuery;
import com.kenticocloud.delivery.query.type.SingleTypeQuery;
import com.kenticocloud.delivery.request.IRequestService;

public abstract class DeliveryService implements IDeliveryService {

    protected DeliveryClientConfig config;
    protected IRequestService requestService;

    protected DeliveryService(DeliveryClientConfig config) {
        this.config = config;
    }

    abstract public IRequestService getRequestService();

    public <TItem extends IContentItem> MultipleItemQuery<TItem> items(){
        return new MultipleItemQuery<>(this.config, this.getRequestService());
    }

    public <TItem extends IContentItem> SingleItemQuery<TItem> item(String itemCodename){
        return new SingleItemQuery<>(this.config, this.getRequestService(), itemCodename);
    }

    @Override
    public SingleTypeQuery type(String typeCodename) {
        return new SingleTypeQuery(this.config, this.getRequestService(), typeCodename);
    }

    @Override
    public MultipleTypeQuery types() {
        return new MultipleTypeQuery(this.config, this.getRequestService());
    }
}
