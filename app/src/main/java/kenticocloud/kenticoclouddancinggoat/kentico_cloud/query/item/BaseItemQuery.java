package kenticocloud.kenticoclouddancinggoat.kentico_cloud.query.item;

import android.support.annotation.NonNull;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

import kenticocloud.kenticoclouddancinggoat.kentico_cloud.config.DeliveryClientConfig;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.interfaces.item.common.IQueryParameter;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.interfaces.item.item.IContentItem;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.services.ItemMapService;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.services.QueryService;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.services.ResponseMapService;

/**
 * Created by RichardS on 17. 8. 2017.
 */

public abstract class BaseItemQuery<T extends IContentItem> extends QueryService {

    protected List<IQueryParameter> _parameters = new ArrayList<>();

    protected DeliveryClientConfig _config;
    protected ResponseMapService _responseMapService;
    protected Class<T> _tClass;

    protected BaseItemQuery(@NonNull DeliveryClientConfig config, Class<T> tClass){
        super(config);

        _tClass = tClass;
        _config = config;
        _responseMapService = new ResponseMapService(new ItemMapService(config));
    }

}
