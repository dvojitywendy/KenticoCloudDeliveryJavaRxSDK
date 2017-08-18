package kenticocloud.kenticoclouddancinggoat.kentico_cloud.models.item;

import java.util.List;

import kenticocloud.kenticoclouddancinggoat.kentico_cloud.interfaces.item.item.IContentItem;

/**
 * Created by RichardS on 17. 8. 2017.
 */

public class DeliveryItemListingResponse {

    private List<IContentItem> _items;

    public DeliveryItemListingResponse(List<IContentItem> items) {
        _items = items;
    }

    public List<IContentItem> getItems(){
        return _items;
    }
}
