package com.kentico.delivery.core.models.type;

import com.kentico.delivery.core.models.common.IDeliveryResponse;
import com.kentico.delivery.core.models.common.Pagination;

import java.util.List;


public class DeliveryTypeListingResponse implements IDeliveryResponse {

    private List<ContentType> types;
    private Pagination pagination;

    public DeliveryTypeListingResponse(
            List<ContentType> types,
            Pagination pagination
    )
    {
        this.types = types;
        this.pagination = pagination;
    }

    public List<ContentType> getTypes() {
        return this.types;
    }

    public Pagination getPagination() {
        return this.pagination;
    }
}
