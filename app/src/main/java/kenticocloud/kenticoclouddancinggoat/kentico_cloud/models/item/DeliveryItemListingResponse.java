package kenticocloud.kenticoclouddancinggoat.kentico_cloud.models.item;

import java.util.List;

import kenticocloud.kenticoclouddancinggoat.kentico_cloud.interfaces.item.item.IContentItem;

/**
 * Created by RichardS on 17. 8. 2017.
 */

public class DeliveryItemListingResponse<T extends IContentItem> {

    private List<T> _items;

    public DeliveryItemListingResponse(List<T> items) {
        _items = items;
    }

    public List<T> getItems(){
        return _items;
    }
}
