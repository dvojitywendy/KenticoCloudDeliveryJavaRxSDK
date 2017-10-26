package com.kentico.delivery.core.models.item;

import com.kentico.delivery.core.interfaces.item.item.IContentItem;
import com.kentico.delivery.core.models.common.IDeliveryResponse;
import com.kentico.delivery.core.models.common.Pagination;

import java.util.List;


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
