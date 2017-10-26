package com.kentico.delivery.core.services;

import com.kentico.delivery.core.config.DeliveryClientConfig;
import com.kentico.delivery.core.interfaces.item.item.IContentItem;
import com.kentico.delivery.core.query.item.MultipleItemQuery;
import com.kentico.delivery.core.query.item.SingleItemQuery;
import com.kentico.delivery.core.query.type.MultipleTypeQuery;
import com.kentico.delivery.core.query.type.SingleTypeQuery;
import com.kentico.delivery.core.request.IRequestService;

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
