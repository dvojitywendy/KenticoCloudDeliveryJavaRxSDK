package com.kenticocloud.delivery.data;

import android.support.annotation.NonNull;

import com.kenticocloud.delivery.services.IDeliveryService;

public abstract class BaseCloudSource {

    protected IDeliveryService deliveryService;

    public BaseCloudSource(@NonNull IDeliveryService deliveryService){
        this.deliveryService = deliveryService;
    }
}
