package kenticocloud.kenticoclouddancinggoat.kentico_cloud.query.item;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import kenticocloud.kenticoclouddancinggoat.kentico_cloud.config.DeliveryClientConfig;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.interfaces.item.common.IQueryParameter;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.services.ItemMapService;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.services.QueryService;

/**
 * Created by RichardS on 17. 8. 2017.
 */

public abstract class BaseItemQuery extends QueryService {

    protected DeliveryClientConfig _config;
    protected List<IQueryParameter> _parameters = new ArrayList<>();
    protected ItemMapService _itemMapService;

    protected BaseItemQuery(@NonNull DeliveryClientConfig config){
        super(config);

        _config = config;
        _itemMapService = new ItemMapService(config);
    }

}
