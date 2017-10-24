package delivery.query.type;



import delivery.config.DeliveryClientConfig;
import delivery.interfaces.item.common.IQueryParameter;
import delivery.query.BaseQuery;
import delivery.services.map.ResponseMapService;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseTypeQuery extends BaseQuery {

    protected List<IQueryParameter> parameters = new ArrayList<>();

    protected DeliveryClientConfig config;
    protected ResponseMapService responseMapService;

    protected BaseTypeQuery( DeliveryClientConfig config){
        super(config);

        this.config = config;
        this.responseMapService = new ResponseMapService(config);
    }

}
