package delivery.services.map;



import com.fasterxml.jackson.databind.ObjectMapper;
import delivery.config.DeliveryClientConfig;
import delivery.models.common.CommonCloudResponses;
import delivery.models.common.Pagination;

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


