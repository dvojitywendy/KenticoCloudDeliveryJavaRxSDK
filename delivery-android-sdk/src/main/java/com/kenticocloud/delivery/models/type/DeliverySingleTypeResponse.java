package com.kenticocloud.delivery.models.type;

import com.kenticocloud.delivery.models.common.IDeliveryResponse;

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
