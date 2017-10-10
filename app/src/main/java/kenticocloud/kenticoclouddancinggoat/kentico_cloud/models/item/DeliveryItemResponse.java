package kenticocloud.kenticoclouddancinggoat.kentico_cloud.models.item;

import kenticocloud.kenticoclouddancinggoat.kentico_cloud.interfaces.item.item.IContentItem;

public class DeliveryItemResponse<TItem extends IContentItem> {
    private TItem _item;

    public DeliveryItemResponse(TItem item) {
        _item = item;
    }

    public TItem getItem(){
        return _item;
    }
}
