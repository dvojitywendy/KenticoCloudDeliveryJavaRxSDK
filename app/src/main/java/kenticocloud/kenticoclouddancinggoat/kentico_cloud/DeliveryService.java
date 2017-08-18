package kenticocloud.kenticoclouddancinggoat.kentico_cloud;

import android.support.annotation.NonNull;

import kenticocloud.kenticoclouddancinggoat.kentico_cloud.config.DeliveryClientConfig;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.query.item.MultipleItemQuery;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.query.item.SingleItemQuery;

public class DeliveryService implements IDeliveryService{

    private static DeliveryService INSTANCE;

    private DeliveryClientConfig _config;

    private DeliveryService(DeliveryClientConfig config) {
        _config = config;
    }

    public static IDeliveryService getInstance(DeliveryClientConfig config) {
        if (INSTANCE == null) {
            INSTANCE = new DeliveryService(config);
        }
        return INSTANCE;
    }

    /**
     * Gets query for multiple items
     */
    public MultipleItemQuery items(){
        return new MultipleItemQuery(_config);
    }

    /**
     * Gets query for single item
     */
    public SingleItemQuery item(@NonNull String itemCodename){
        return new SingleItemQuery(_config, itemCodename);
    }
}
