package kenticocloud.kenticoclouddancinggoat.kentico_cloud;

import android.support.annotation.NonNull;

import kenticocloud.kenticoclouddancinggoat.kentico_cloud.interfaces.item.item.IContentItem;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.query.item.MultipleItemQuery;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.query.item.SingleItemQuery;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.query.type.MultipleTypeQuery;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.query.type.SingleTypeQuery;

public interface IDeliveryService{

     /**
     * Query to fetch multiple items
     * @param <TItem> Class representing the type you want to return. Use 'IContentItem' if multiple types can be returned
     *               or if you don't know what types will be returned beforehands.
     * @return Query to get the item
     */
     <TItem extends IContentItem> MultipleItemQuery<TItem> items();

     /**
     * Query to fetch single item
     * @param itemCodename Codename of the item
     * @param <TItem> Class representing the type you want to return. Use 'IContentItem' if multiple types can be returned
     *               or if you don't know what types will be returned beforehands.
     * @return Query to get the item
     */
     <TItem extends IContentItem> SingleItemQuery<TItem> item(@NonNull String itemCodename);

     /**
     * Query to fetch single type
     * @param typeCodename Type codename
     * @return Query to get the type
     */
     SingleTypeQuery type(@NonNull String typeCodename);

    /**
     * Query to fetch multiple types
     * @return Qury to get multiple types
     */
     MultipleTypeQuery types();
}
