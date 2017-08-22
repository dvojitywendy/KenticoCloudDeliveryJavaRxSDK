package kenticocloud.kenticoclouddancinggoat.data.source;

import android.support.annotation.NonNull;

import kenticocloud.kenticoclouddancinggoat.kentico_cloud.IDeliveryService;

/**
 * Created by RichardS on 22. 8. 2017.
 */

public abstract class BaseCloudSource {

    protected IDeliveryService _deliveryService;

    public BaseCloudSource(@NonNull IDeliveryService deliveryService){
        _deliveryService = deliveryService;
    }
}
