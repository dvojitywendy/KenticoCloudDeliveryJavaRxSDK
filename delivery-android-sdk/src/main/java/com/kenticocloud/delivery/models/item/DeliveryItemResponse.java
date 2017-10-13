package com.kenticocloud.delivery.models.item;

import com.kenticocloud.delivery.interfaces.item.item.IContentItem;
import com.kenticocloud.delivery.models.common.IDeliveryResponse;

public class DeliveryItemResponse<TItem extends IContentItem> implements IDeliveryResponse {

    private TItem item;

    public DeliveryItemResponse(TItem item) {
        this.item = item;
    }

    public TItem getItem(){
        return this.item;
    }
}
