package kenticocloud.kenticoclouddancinggoat.kentico_cloud.services;

import android.support.annotation.NonNull;

import java.util.Iterator;
import java.util.List;

import kenticocloud.kenticoclouddancinggoat.kentico_cloud.config.DeliveryClientConfig;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.interfaces.item.common.IQueryParameter;

public final class QueryService {

    private final String DELIVERY_API_URL = "https://deliver.kenticocloud.com";
    private DeliveryClientConfig config;

    public QueryService(DeliveryClientConfig config){
        this.config = config;
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

        StringBuilder urlBuilder = new StringBuilder(url);
        for(int i = 0; i < parameters.size(); i++) {
            IQueryParameter parameter = parameters.get(i);
            if (i == 0) {
                // first item
                urlBuilder.append("?").append(parameter.getParam()).append("=").append(parameter.getParamValue());
            } else {
                // after first items
                urlBuilder.append("&").append(parameter.getParam()).append("=").append(parameter.getParamValue());
            }
        }
        url = urlBuilder.toString();

        return url;
    }

    public String getUrl(@NonNull String action, List<IQueryParameter> parameters){
        return addParametersToUrl(getBaseUrl() + action, parameters);
    }
}
