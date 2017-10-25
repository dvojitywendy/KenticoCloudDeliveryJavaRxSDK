package com.kenticocloud.delivery.services;


import org.json.JSONObject;

import java.util.List;

import com.kenticocloud.delivery.config.DeliveryClientConfig;
import com.kenticocloud.delivery.interfaces.item.common.IQueryParameter;
import com.kenticocloud.delivery.request.IRequestService;
import io.reactivex.Observable;

public final class QueryService implements IQueryService{

    private final String DELIVERY_API_URL = "https://deliver.kenticocloud.com";
    private DeliveryClientConfig config;
    private IRequestService requestService;

    public QueryService(DeliveryClientConfig config, IRequestService requestService){
        this.config = config;
        this.requestService = requestService;
    }

    private String getDeliveryUrl(){
        return DELIVERY_API_URL;
    }

    private String getBaseUrl(){
        return this.getDeliveryUrl() + '/' + this.config.getProjectId();
    }

    private String addParametersToUrl( String url, List<IQueryParameter> parameters){
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

    @Override
    public String getUrl( String action, List<IQueryParameter> parameters){
        return addParametersToUrl(getBaseUrl() + action, parameters);
    }

    @Override
    public Observable<JSONObject> getRequest(String url) {
        return this.requestService.getRequest(url);
    }


}
