package delivery.query;



import delivery.config.DeliveryClientConfig;
import delivery.interfaces.item.common.IQueryParameter;
import delivery.models.common.IDeliveryResponse;
import delivery.services.IQueryService;
import delivery.services.map.ResponseMapService;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

public abstract class BaseQuery {

    protected List<IQueryParameter> parameters = new ArrayList<>();

    protected DeliveryClientConfig config;
    protected ResponseMapService responseMapService;
    protected IQueryService queryService;

    protected BaseQuery( DeliveryClientConfig config){
        this.config = config;
        this.responseMapService = new ResponseMapService(config);
        this.queryService = new IQueryService(config);
    }

    /**
     * Gets url of query action
     * @return Url used to hit Kentico Cloud
     */
    public abstract String getQueryUrl();

    /**
     * Gets observable to fetch IDeliveryResposne from Kentico Cloud
     * @return Observable to get Delivery response
     */
    public abstract Observable<? extends IDeliveryResponse> get();
}
