package com.kenticocloud.delivery.services.map;

import android.support.annotation.NonNull;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import com.kenticocloud.delivery.config.DeliveryClientConfig;
import com.kenticocloud.delivery.interfaces.item.item.IContentItem;
import com.kenticocloud.delivery.models.item.DeliveryItemListingResponse;
import com.kenticocloud.delivery.models.item.DeliveryItemResponse;
import com.kenticocloud.delivery.models.item.ItemCloudResponses;
import com.kenticocloud.delivery.models.type.DeliveryTypeListingResponse;
import com.kenticocloud.delivery.models.type.DeliverySingleTypeResponse;
import com.kenticocloud.delivery.models.type.TypeCloudResponses;

public final class ResponseMapService {

    private PaginationMapService paginationMapService;
    private ItemMapService itemMapService;
    private TypeMapService typeMapService;
    private ObjectMapper objectMapper = new ObjectMapper();

    public ResponseMapService(@NonNull DeliveryClientConfig config){
        this.itemMapService = new ItemMapService(config, this.objectMapper);
        this.typeMapService = new TypeMapService(config, this.objectMapper);
        this.paginationMapService = new PaginationMapService(config, this.objectMapper);
    }

    public<TItem extends IContentItem> DeliveryItemResponse<TItem> mapItemResponse(JSONObject cloudResponse) throws JSONException, IOException, IllegalAccessException {
        ItemCloudResponses.DeliveryItemResponseRaw rawResponse = this.objectMapper.readValue(cloudResponse.toString(), ItemCloudResponses.DeliveryItemResponseRaw.class);

        TItem item = this.itemMapService.mapItem(rawResponse.item, rawResponse.modularContent);

        return new DeliveryItemResponse<>(item);
    }

    public<TItem extends IContentItem> DeliveryItemListingResponse<TItem> mapItemListingResponse(JSONObject cloudResponse) throws JSONException, IOException, IllegalAccessException {
        ItemCloudResponses.DeliveryItemListingResponseRaw rawResponse = this.objectMapper.readValue(cloudResponse.toString(), ItemCloudResponses.DeliveryItemListingResponseRaw.class);

        List<TItem> items = this.itemMapService.mapItems(rawResponse.items, rawResponse.modularContent);

        return new DeliveryItemListingResponse<TItem>(items, this.paginationMapService.mapPagination(rawResponse.pagination));
    }

    public DeliverySingleTypeResponse mapDeliverySingleTypeResponse(JSONObject cloudResponse) throws IOException {
        TypeCloudResponses.DeliverySingleTypeResponseRaw rawResponse = this.objectMapper.readValue(cloudResponse.toString(), TypeCloudResponses.DeliverySingleTypeResponseRaw.class);

        return new DeliverySingleTypeResponse(this.typeMapService.mapType(rawResponse));
    }

    public DeliveryTypeListingResponse mapDeliveryMultipleTypesResponse(JSONObject cloudResponse) throws IOException {
        TypeCloudResponses.DeliveryMultipleTypeResponseRaw rawResponse = this.objectMapper.readValue(cloudResponse.toString(), TypeCloudResponses.DeliveryMultipleTypeResponseRaw.class);

        return new DeliveryTypeListingResponse(this.typeMapService.mapTypes(rawResponse.types), this.paginationMapService.mapPagination(rawResponse.pagination));
    }
}

