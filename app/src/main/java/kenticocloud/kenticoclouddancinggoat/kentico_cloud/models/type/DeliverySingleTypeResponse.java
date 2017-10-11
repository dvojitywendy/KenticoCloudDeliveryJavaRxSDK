package kenticocloud.kenticoclouddancinggoat.kentico_cloud.models.type;

import kenticocloud.kenticoclouddancinggoat.kentico_cloud.models.common.IDeliveryResponse;

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
