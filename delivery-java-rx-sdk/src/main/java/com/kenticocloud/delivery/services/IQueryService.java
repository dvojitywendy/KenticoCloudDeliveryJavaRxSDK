package com.kenticocloud.delivery.services;



import org.json.JSONObject;

import com.kenticocloud.delivery.interfaces.item.common.IQueryParameter;
import io.reactivex.Observable;

import java.util.List;

public interface IQueryService {

    String getUrl( String action, List<IQueryParameter> parameters);

    Observable<JSONObject> getRequest(String url);
}
