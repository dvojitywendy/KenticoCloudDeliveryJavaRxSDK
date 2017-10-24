package delivery.services;



import delivery.interfaces.item.item.IContentItem;
import delivery.query.item.MultipleItemQuery;
import delivery.query.item.SingleItemQuery;
import delivery.query.type.MultipleTypeQuery;
import delivery.query.type.SingleTypeQuery;

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
     <TItem extends IContentItem> SingleItemQuery<TItem> item( String itemCodename);

     /**
     * Query to fetch single type
     * @param typeCodename Type codename
     * @return Query to get the type
     */
     SingleTypeQuery type( String typeCodename);

    /**
     * Query to fetch multiple types
     * @return Qury to get multiple types
     */
     MultipleTypeQuery types();
}
