package kenticocloud.kenticoclouddancinggoat.kentico_cloud.query.type;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import kenticocloud.kenticoclouddancinggoat.kentico_cloud.config.DeliveryClientConfig;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.interfaces.item.common.IQueryParameter;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.query.BaseQuery;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.services.map.ItemMapService;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.services.QueryService;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.services.map.ResponseMapService;

public abstract class BaseTypeQuery extends BaseQuery {

    protected List<IQueryParameter> parameters = new ArrayList<>();

    protected DeliveryClientConfig config;
    protected ResponseMapService responseMapService;

    protected BaseTypeQuery(@NonNull DeliveryClientConfig config){
        super(config);

        this.config = config;
        this.responseMapService = new ResponseMapService(config);
    }

}
