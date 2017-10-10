package kenticocloud.kenticoclouddancinggoat.data.source;

import android.support.annotation.NonNull;

import kenticocloud.kenticoclouddancinggoat.kentico_cloud.IDeliveryService;

public abstract class BaseCloudSource {

    protected IDeliveryService deliveryService;

    public BaseCloudSource(@NonNull IDeliveryService deliveryService){
        this.deliveryService = deliveryService;
    }
}
