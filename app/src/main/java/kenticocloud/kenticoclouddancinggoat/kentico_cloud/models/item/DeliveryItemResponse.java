package kenticocloud.kenticoclouddancinggoat.kentico_cloud.models.item;

import kenticocloud.kenticoclouddancinggoat.kentico_cloud.interfaces.item.item.IContentItem;

/**
 * Created by RichardS on 17. 8. 2017.
 */

public class DeliveryItemResponse<T extends IContentItem> {
    private T _item;

    public DeliveryItemResponse(T item) {
        _item = item;
    }

    public T getItem(){
        return _item;
    }
}
