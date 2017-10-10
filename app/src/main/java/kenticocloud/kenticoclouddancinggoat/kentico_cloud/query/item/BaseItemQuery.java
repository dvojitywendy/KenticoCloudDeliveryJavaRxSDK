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

public abstract class BaseItemQuery<T extends IContentItem> extends QueryService {

    protected List<IQueryParameter> parameters = new ArrayList<>();

    protected DeliveryClientConfig config;
    protected ResponseMapService responseMapService;

    protected BaseItemQuery(@NonNull DeliveryClientConfig config){
        super(config);

        this.config = config;
        this.responseMapService = new ResponseMapService(new ItemMapService(config));
    }

}
