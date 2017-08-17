package kenticocloud.kenticoclouddancinggoat.kentico_cloud.query.item;

import java.util.ArrayList;
import java.util.List;

import kenticocloud.kenticoclouddancinggoat.kentico_cloud.config.DeliveryClientConfig;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.interfaces.item.common.IQueryParameter;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.services.QueryService;

/**
 * Created by RichardS on 17. 8. 2017.
 */

public abstract class BaseItemQuery extends QueryService {

    protected DeliveryClientConfig _config;
    protected List<IQueryParameter> _parameters = new ArrayList<>();

    protected BaseItemQuery(DeliveryClientConfig config){
        super(config);

        _config = config;
    }

}
