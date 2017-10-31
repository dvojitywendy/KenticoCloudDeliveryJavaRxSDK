/*
 * Copyright 2017 Kentico s.r.o. and Richard Sustek
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.kentico.delivery.core.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kentico.delivery.core.config.DeliveryConfig;
import com.kentico.delivery.core.interfaces.item.item.IContentItem;
import com.kentico.delivery.core.models.element.DeliveryContentTypeElementResponse;
import com.kentico.delivery.core.models.element.ElementCloudResponses;
import com.kentico.delivery.core.models.item.DeliveryItemListingResponse;
import com.kentico.delivery.core.models.item.DeliveryItemResponse;
import com.kentico.delivery.core.models.item.ItemCloudResponses;
import com.kentico.delivery.core.models.taxonomy.DeliveryTaxonomyListingResponse;
import com.kentico.delivery.core.models.taxonomy.DeliveryTaxonomyResponse;
import com.kentico.delivery.core.models.taxonomy.TaxonomyCloudResponses;
import com.kentico.delivery.core.models.type.DeliveryTypeListingResponse;
import com.kentico.delivery.core.models.type.DeliveryTypeResponse;
import com.kentico.delivery.core.models.type.TypeCloudResponses;
import com.kentico.delivery.core.services.map.ContentElementMapService;
import com.kentico.delivery.core.services.map.ItemMapService;
import com.kentico.delivery.core.services.map.PaginationMapService;
import com.kentico.delivery.core.services.map.TaxonomyMapService;
import com.kentico.delivery.core.services.map.TypeMapService;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

public final class ResponseMapService {

    private ObjectMapper objectMapper = new ObjectMapper();

    private PaginationMapService paginationMapService;
    private ItemMapService itemMapService;
    private TypeMapService typeMapService;
    private TaxonomyMapService taxonomyMapService;
    private ContentElementMapService contentElementMapService;

    public ResponseMapService( DeliveryConfig config){
        this.itemMapService = new ItemMapService(config, this.objectMapper);
        this.typeMapService = new TypeMapService(config, this.objectMapper);
        this.taxonomyMapService = new TaxonomyMapService(config, this.objectMapper);
        this.paginationMapService = new PaginationMapService(config, this.objectMapper);
        this.contentElementMapService = new ContentElementMapService(config, this.objectMapper);
    }

    public<TItem extends IContentItem> DeliveryItemResponse<TItem> mapItemResponse(JSONObject cloudResponse) throws JSONException, IOException, IllegalAccessException {
        ItemCloudResponses.DeliveryItemResponseRaw rawResponse = this.objectMapper.readValue(cloudResponse.toString(), ItemCloudResponses.DeliveryItemResponseRaw.class);

        TItem item = this.itemMapService.mapItem(rawResponse.item, rawResponse.modularContent);

        return new DeliveryItemResponse<>(item);
    }

    public<TItem extends IContentItem> DeliveryItemListingResponse<TItem> mapItemListingResponse(JSONObject cloudResponse) throws JSONException, IOException, IllegalAccessException {
        ItemCloudResponses.DeliveryItemListingResponseRaw rawResponse = this.objectMapper.readValue(cloudResponse.toString(), ItemCloudResponses.DeliveryItemListingResponseRaw.class);

        List<TItem> items = this.itemMapService.mapItems(rawResponse.items, rawResponse.modularContent);

        return new DeliveryItemListingResponse<>(items, this.paginationMapService.mapPagination(rawResponse.pagination));
    }

    public DeliveryTypeResponse mapDeliverySingleTypeResponse(JSONObject cloudResponse) throws IOException {
        TypeCloudResponses.DeliverySingleTypeResponseRaw rawResponse = this.objectMapper.readValue(cloudResponse.toString(), TypeCloudResponses.DeliverySingleTypeResponseRaw.class);

        return new DeliveryTypeResponse(this.typeMapService.mapType(rawResponse));
    }

    public DeliveryTypeListingResponse mapDeliveryMultipleTypesResponse(JSONObject cloudResponse) throws IOException {
        TypeCloudResponses.DeliveryMultipleTypeResponseRaw rawResponse = this.objectMapper.readValue(cloudResponse.toString(), TypeCloudResponses.DeliveryMultipleTypeResponseRaw.class);

        return new DeliveryTypeListingResponse(this.typeMapService.mapTypes(rawResponse.types), this.paginationMapService.mapPagination(rawResponse.pagination));
    }

    public DeliveryTaxonomyListingResponse mapDeliveryTaxonomyListingResponse(JSONObject cloudResponse) throws IOException {
        TaxonomyCloudResponses.TaxonomyMultipleResponseRaw rawResponse = this.objectMapper.readValue(cloudResponse.toString(), TaxonomyCloudResponses.TaxonomyMultipleResponseRaw.class);

        return new DeliveryTaxonomyListingResponse(this.taxonomyMapService.mapTaxonomies(rawResponse.taxonomies), this.paginationMapService.mapPagination(rawResponse.pagination));
    }

    public DeliveryTaxonomyResponse mapDeliveryTaxonomyResponse(JSONObject cloudResponse) throws IOException {
        TaxonomyCloudResponses.TaxonomySingleResponseRaw rawResponse = this.objectMapper.readValue(cloudResponse.toString(), TaxonomyCloudResponses.TaxonomySingleResponseRaw .class);

        return new DeliveryTaxonomyResponse(this.taxonomyMapService.mapTaxonomy(rawResponse.system, rawResponse.terms));
    }


    public DeliveryContentTypeElementResponse mapDeliveryContentTypeResponse(JSONObject cloudResponse) throws IOException {
        ElementCloudResponses.ContentTypeElementResponseRaw rawResponse = this.objectMapper.readValue(cloudResponse.toString(), ElementCloudResponses.ContentTypeElementResponseRaw.class);

        return new DeliveryContentTypeElementResponse(this.contentElementMapService.mapContentTypeElement(rawResponse));
    }
}

