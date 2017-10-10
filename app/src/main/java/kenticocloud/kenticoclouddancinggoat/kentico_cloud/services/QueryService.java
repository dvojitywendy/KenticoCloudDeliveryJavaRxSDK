package kenticocloud.kenticoclouddancinggoat.kentico_cloud.services;

import android.support.annotation.NonNull;

import java.util.Iterator;
import java.util.List;

import kenticocloud.kenticoclouddancinggoat.kentico_cloud.config.DeliveryClientConfig;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.interfaces.item.common.IQueryParameter;

public abstract class QueryService {

    protected final String DELIVERY_API_URL = "https://deliver.kenticocloud.com";
    protected DeliveryClientConfig config;

    protected QueryService(DeliveryClientConfig config){
        this.config = config;
    }


    protected String getUrl(@NonNull String action, List<IQueryParameter> parameters){
        return addParametersToUrl(getBaseUrl() + action, parameters);
    }

    private String getDeliveryUrl(){
        return DELIVERY_API_URL;
    }

    private String getBaseUrl(){
        return this.getDeliveryUrl() + '/' + this.config.getProjectId();
    }

    private String addParametersToUrl(@NonNull String url, List<IQueryParameter> parameters){
        if (parameters == null){
            return url;
        }

        for(int i = 0; i < parameters.size(); i++) {
            IQueryParameter parameter = parameters.get(i);
            if (i == 0) {
                // first item
                url += "?" + parameter.getParam() + "=" + parameter.getParamValue();
            } else {
                // after first items
                url += "&" + parameter.getParam() + "=" + parameter.getParamValue();
            }
        }

        return url;
    }
}
