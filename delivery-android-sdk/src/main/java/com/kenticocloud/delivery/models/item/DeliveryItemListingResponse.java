package com.kenticocloud.delivery.models.item;

import java.util.List;

import com.kenticocloud.delivery.interfaces.item.item.IContentItem;
import com.kenticocloud.delivery.models.common.IDeliveryResponse;
import com.kenticocloud.delivery.models.common.Pagination;


public class DeliveryItemListingResponse<TItem extends IContentItem> implements IDeliveryResponse {

    private List<TItem> items;
    private Pagination pagination;

    public DeliveryItemListingResponse(List<TItem> items, Pagination pagination) {
        this.items = items;
        this.pagination = pagination;
    }

    public List<TItem> getItems(){
        return this.items;
    }

    public Pagination getPagination() {
        return this.pagination;
    }
}
