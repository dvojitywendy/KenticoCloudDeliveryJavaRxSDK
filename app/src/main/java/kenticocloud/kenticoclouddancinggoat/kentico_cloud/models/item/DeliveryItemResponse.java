package kenticocloud.kenticoclouddancinggoat.kentico_cloud.models.item;

import kenticocloud.kenticoclouddancinggoat.kentico_cloud.interfaces.item.item.IContentItem;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.models.common.IDeliveryResponse;

public class DeliveryItemResponse<TItem extends IContentItem> implements IDeliveryResponse {

    private TItem _item;

    public DeliveryItemResponse(TItem item) {
        _item = item;
    }

    public TItem getItem(){
        return _item;
    }
}
