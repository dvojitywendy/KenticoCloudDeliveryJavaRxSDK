package com.kenticocloud.delivery.data;



import com.kenticocloud.delivery.services.IDeliveryService;

public abstract class BaseCloudSource {

    protected IDeliveryService deliveryService;

    public BaseCloudSource( IDeliveryService deliveryService){
        this.deliveryService = deliveryService;
    }
}
