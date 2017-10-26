package com.kentico.delivery.core.services.map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kentico.delivery.core.config.DeliveryClientConfig;
import com.kentico.delivery.core.models.common.CommonCloudResponses;
import com.kentico.delivery.core.models.common.Pagination;

public class PaginationMapService {

    private DeliveryClientConfig config;
    private ObjectMapper objectMapper;

    public PaginationMapService( DeliveryClientConfig config,  ObjectMapper objectMapper){
        this.config = config;
        this.objectMapper = objectMapper;
    }

    public Pagination mapPagination(CommonCloudResponses.PaginationRaw paginationRaw){
        return new Pagination(
                paginationRaw.skip,
                paginationRaw.limit,
                paginationRaw.count,
                paginationRaw.nextPage
        );
    }

}


