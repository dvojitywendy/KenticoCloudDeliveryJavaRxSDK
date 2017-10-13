package kenticocloud.kenticoclouddancinggoat.kentico_cloud.services.map;

import android.support.annotation.NonNull;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

import kenticocloud.kenticoclouddancinggoat.kentico_cloud.config.DeliveryClientConfig;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.models.common.CommonCloudResponses;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.models.common.Pagination;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.models.element.ContentTypeElement;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.models.element.ContentTypeElementOption;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.models.element.ElementCloudResponses;

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


