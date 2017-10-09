package kenticocloud.kenticoclouddancinggoat.kentico_cloud.services;

import android.support.annotation.NonNull;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import kenticocloud.kenticoclouddancinggoat.kentico_cloud.interfaces.item.item.IContentItem;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.models.item.CloudResponses;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.models.item.DeliveryItemListingResponse;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.models.item.DeliveryItemResponse;

/**
 * Created by RichardS on 4. 10. 2017.
 */

public class ResponseMapService {

    private ItemMapService _itemMapService;
    private ObjectMapper _objectMapper = new ObjectMapper();

    public ResponseMapService(@NonNull ItemMapService itemMapService){
        _itemMapService = itemMapService;
    }

    public<T extends IContentItem> DeliveryItemResponse<T> mapItemResponse(Class<T> tClass, JSONObject cloudResponse) throws JSONException, IOException {
        CloudResponses.DeliveryItemResponseRaw rawResponse = _objectMapper.readValue(cloudResponse.toString(), CloudResponses.DeliveryItemResponseRaw.class);

        return new DeliveryItemResponse<T>(_itemMapService.mapItem(tClass, rawResponse.item, rawResponse.modularContent));
    }

    public<T extends IContentItem> DeliveryItemListingResponse<T> mapItemListingResponse(Class<T> tClass, JSONObject cloudResponse) throws JSONException, IOException {
        CloudResponses.DeliveryItemListingResponseRaw rawResponse = _objectMapper.readValue(cloudResponse.toString(), CloudResponses.DeliveryItemListingResponseRaw.class);

        return new DeliveryItemListingResponse<T>(_itemMapService.mapItems(tClass, rawResponse.items, rawResponse.modularContent));
    }
}

