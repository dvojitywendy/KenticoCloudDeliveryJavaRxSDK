package kenticocloud.kenticoclouddancinggoat.kentico_cloud;

import android.support.annotation.NonNull;

import com.google.common.base.Function;

import javax.annotation.Nullable;

import kenticocloud.kenticoclouddancinggoat.data.models.Cafe;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.config.DeliveryClientConfig;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.interfaces.item.item.IContentItem;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.models.ContentItem;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.models.item.TypeResolver;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.query.item.MultipleItemQuery;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.query.item.SingleItemQuery;

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

    /**
     * Use to fetch single item from Kentico Cloud
     * @param <TItem> Class representing the type you want to return. Use 'IContentItem' if multiple types can be returned
     *               or if you don't know what types will be returned beforehands.
     * @return Query to get the item
     */
    public <TItem extends IContentItem> MultipleItemQuery<TItem> items(){
        return new MultipleItemQuery<>(this.config);
    }

    /**
     * Use to fetch single item from Kentico Cloud
     * @param itemCodename Codename of the item
     * @param <TItem> Class representing the type you want to return. Use 'IContentItem' if multiple types can be returned
     *               or if you don't know what types will be returned beforehands.
     * @return Query to get the item
     */
    public <TItem extends IContentItem> SingleItemQuery<TItem> item(@NonNull String itemCodename){
        return new SingleItemQuery<>(this.config, itemCodename);
    }
}
