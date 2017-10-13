package kenticocloud.kenticoclouddancinggoat.kentico_cloud.query;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.config.DeliveryClientConfig;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.interfaces.item.common.IQueryParameter;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.models.common.IDeliveryResponse;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.models.item.DeliveryItemListingResponse;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.services.QueryService;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.services.map.ResponseMapService;

public abstract class BaseQuery {

    protected List<IQueryParameter> parameters = new ArrayList<>();

    protected DeliveryClientConfig config;
    protected ResponseMapService responseMapService;
    protected QueryService queryService;

    protected BaseQuery(@NonNull DeliveryClientConfig config){
        this.config = config;
        this.responseMapService = new ResponseMapService(config);
        this.queryService = new QueryService(config);
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
