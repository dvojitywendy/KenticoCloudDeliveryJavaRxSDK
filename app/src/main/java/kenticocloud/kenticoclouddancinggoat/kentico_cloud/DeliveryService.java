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
    public <T extends IContentItem> MultipleItemQuery items(Class<T> tclass){
        return new MultipleItemQuery<T>(_config, tclass);
    }

    /**
     * Gets query for single item
     */
    public <T extends IContentItem> SingleItemQuery item(@NonNull String itemCodename, Class<T> tclass){
        return new SingleItemQuery(_config, itemCodename, tclass);
    }
}
