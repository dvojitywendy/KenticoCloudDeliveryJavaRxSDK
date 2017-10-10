package kenticocloud.kenticoclouddancinggoat.kentico_cloud;

import android.support.annotation.NonNull;

import kenticocloud.kenticoclouddancinggoat.kentico_cloud.interfaces.item.item.IContentItem;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.query.item.MultipleItemQuery;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.query.item.SingleItemQuery;

public interface IDeliveryService{

    /**
     * Use to fetch single item from Kentico Cloud
     * @param <TItem> Class representing the type you want to return. Use 'IContentItem' if multiple types can be returned
     *               or if you don't know what types will be returned beforehands.
     * @return Query to get the item
     */
    public <TItem extends IContentItem> MultipleItemQuery<TItem> items();

    /**
     * Use to fetch single item from Kentico Cloud
     * @param itemCodename Codename of the item
     * @param <TItem> Class representing the type you want to return. Use 'IContentItem' if multiple types can be returned
     *               or if you don't know what types will be returned beforehands.
     * @return Query to get the item
     */
    public <TItem extends IContentItem> SingleItemQuery<TItem> item(@NonNull String itemCodename);
}
