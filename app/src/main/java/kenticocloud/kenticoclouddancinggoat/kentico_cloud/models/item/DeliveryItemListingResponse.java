package kenticocloud.kenticoclouddancinggoat.kentico_cloud.models.item;

import java.util.List;

import kenticocloud.kenticoclouddancinggoat.kentico_cloud.interfaces.item.item.IContentItem;


public class DeliveryItemListingResponse<TItem extends IContentItem> {

    private List<TItem> _items;

    public DeliveryItemListingResponse(List<TItem> items) {
        _items = items;
    }

    public List<TItem> getItems(){
        return _items;
    }
}
