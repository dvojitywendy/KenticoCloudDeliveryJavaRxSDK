package kenticocloud.kenticoclouddancinggoat.kentico_cloud.models.item;

import kenticocloud.kenticoclouddancinggoat.kentico_cloud.interfaces.item.item.IContentItem;

/**
 * Created by RichardS on 17. 8. 2017.
 */

public class DeliveryItemResponse<TItem extends IContentItem> {
    private TItem _item;


    public DeliveryItemResponse(TItem item) {
        _item = item;
    }
}
