package kenticocloud.kenticoclouddancinggoat.kentico_cloud.models.item;

import kenticocloud.kenticoclouddancinggoat.kentico_cloud.interfaces.item.item.IContentItem;

/**
 * Created by RichardS on 17. 8. 2017.
 */

public class DeliveryItemResponse {
    private IContentItem _item;

    public DeliveryItemResponse(IContentItem item) {
        _item = item;
    }

    public IContentItem getItem(){
        return _item;
    }
}
