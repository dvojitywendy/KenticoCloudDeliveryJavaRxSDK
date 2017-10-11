package kenticocloud.kenticoclouddancinggoat.kentico_cloud.models.item;

import kenticocloud.kenticoclouddancinggoat.kentico_cloud.interfaces.item.item.IContentItem;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.models.common.IDeliveryResponse;

public class DeliveryItemResponse<TItem extends IContentItem> implements IDeliveryResponse {

    private TItem item;

    public DeliveryItemResponse(TItem item) {
        this.item = item;
    }

    public TItem getItem(){
        return this.item;
    }
}
