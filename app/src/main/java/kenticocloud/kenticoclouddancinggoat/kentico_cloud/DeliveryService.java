package kenticocloud.kenticoclouddancinggoat.kentico_cloud;

import android.support.annotation.NonNull;

import kenticocloud.kenticoclouddancinggoat.kentico_cloud.config.DeliveryClientConfig;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.interfaces.item.item.IContentItem;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.query.item.MultipleItemQuery;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.query.item.SingleItemQuery;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.query.type.MultipleTypeQuery;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.query.type.SingleTypeQuery;

public class DeliveryService implements IDeliveryService{

    private static DeliveryService INSTANCE;

    private DeliveryClientConfig config;

    private DeliveryService(DeliveryClientConfig config) {
        this.config = config;
    }

    /**
     * Gets static instance of Delivery client
     * @param config Delivery client configuration
     * @return Delivery client instance
     */
    public static IDeliveryService getInstance(DeliveryClientConfig config) {
        if (INSTANCE == null) {
            INSTANCE = new DeliveryService(config);
        }
        return INSTANCE;
    }

    public <TItem extends IContentItem> MultipleItemQuery<TItem> items(){
        return new MultipleItemQuery<>(this.config);
    }

    public <TItem extends IContentItem> SingleItemQuery<TItem> item(@NonNull String itemCodename){
        return new SingleItemQuery<>(this.config, itemCodename);
    }

    @Override
    public SingleTypeQuery type(@NonNull String typeCodename) {
        return new SingleTypeQuery(this.config, typeCodename);
    }

    @Override
    public MultipleTypeQuery types() {
        return new MultipleTypeQuery(this.config);
    }
}
