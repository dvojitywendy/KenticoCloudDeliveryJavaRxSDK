package delivery.models.item;

import delivery.interfaces.item.item.IContentItem;
import delivery.models.common.IDeliveryResponse;

public class DeliveryItemResponse<TItem extends IContentItem> implements IDeliveryResponse {

    private TItem item;

    public DeliveryItemResponse(TItem item) {
        this.item = item;
    }

    public TItem getItem(){
        return this.item;
    }
}
