package com.kentico.delivery.core.models.type;

import com.kentico.delivery.core.models.common.IDeliveryResponse;

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
