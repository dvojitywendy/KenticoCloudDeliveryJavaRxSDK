package com.kenticocloud.delivery.query.type;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import com.kenticocloud.delivery.config.DeliveryClientConfig;
import com.kenticocloud.delivery.interfaces.item.common.IQueryParameter;
import com.kenticocloud.delivery.query.BaseQuery;
import com.kenticocloud.delivery.services.map.ResponseMapService;

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
