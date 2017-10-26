package com.kentico.delivery.core.data;



import com.kentico.delivery.core.services.IDeliveryService;

public abstract class BaseCloudSource {

    protected IDeliveryService deliveryService;

    public BaseCloudSource( IDeliveryService deliveryService){
        this.deliveryService = deliveryService;
    }
}
