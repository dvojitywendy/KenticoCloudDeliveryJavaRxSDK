package kenticocloud.kenticoclouddancinggoat.kentico_cloud.services.map;

import android.support.annotation.NonNull;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import kenticocloud.kenticoclouddancinggoat.kentico_cloud.config.DeliveryClientConfig;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.interfaces.item.item.IContentItem;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.models.item.CloudResponses;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.models.item.DeliveryItemListingResponse;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.models.item.DeliveryItemResponse;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.services.map.ItemMapService;

public class ResponseMapService {

    private ItemMapService itemMapService;
    private ObjectMapper objectMapper = new ObjectMapper();

    public ResponseMapService(@NonNull DeliveryClientConfig config){
        this.itemMapService = new ItemMapService(config, this.objectMapper);
    }

    public<TItem extends IContentItem> DeliveryItemResponse<TItem> mapItemResponse(JSONObject cloudResponse) throws JSONException, IOException, IllegalAccessException {
        CloudResponses.DeliveryItemResponseRaw rawResponse = this.objectMapper.readValue(cloudResponse.toString(), CloudResponses.DeliveryItemResponseRaw.class);

        TItem item = this.itemMapService.mapItem(rawResponse.item, rawResponse.modularContent);

        return new DeliveryItemResponse<>(item);
    }

    public<TItem extends IContentItem> DeliveryItemListingResponse<TItem> mapItemListingResponse(JSONObject cloudResponse) throws JSONException, IOException, IllegalAccessException {
        CloudResponses.DeliveryItemListingResponseRaw rawResponse = this.objectMapper.readValue(cloudResponse.toString(), CloudResponses.DeliveryItemListingResponseRaw.class);

        List<TItem> items = this.itemMapService.mapItems(rawResponse.items, rawResponse.modularContent);

        return new DeliveryItemListingResponse<TItem>(items);
    }
}

