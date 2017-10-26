package com.kentico.delivery.core.models.item;

import com.kentico.delivery.core.interfaces.item.item.IContentItem;
import com.kentico.delivery.core.models.common.IDeliveryResponse;

public class DeliveryItemResponse<TItem extends IContentItem> implements IDeliveryResponse {

    private TItem item;

    public DeliveryItemResponse(TItem item) {
        this.item = item;
    }

    public TItem getItem(){
        return this.item;
    }
}
