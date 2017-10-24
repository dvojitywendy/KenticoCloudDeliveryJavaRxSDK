package delivery.models.type;

import delivery.models.common.IDeliveryResponse;

public class DeliverySingleTypeResponse implements IDeliveryResponse {

    private ContentType type;

    public DeliverySingleTypeResponse(
            ContentType type
    ){
        this.type = type;
    }

    public ContentType getType() {
        return this.type;
    }
}
