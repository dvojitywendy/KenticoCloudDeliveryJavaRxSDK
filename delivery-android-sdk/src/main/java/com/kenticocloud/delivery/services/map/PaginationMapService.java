package com.kenticocloud.delivery.services.map;

import android.support.annotation.NonNull;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.kenticocloud.delivery.config.DeliveryClientConfig;
import com.kenticocloud.delivery.models.common.CommonCloudResponses;
import com.kenticocloud.delivery.models.common.Pagination;

public class PaginationMapService {

    private DeliveryClientConfig config;
    private ObjectMapper objectMapper;

    public PaginationMapService(@NonNull DeliveryClientConfig config, @NonNull ObjectMapper objectMapper){
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


