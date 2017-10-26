package com.kentico.delivery.core.services;

import org.json.JSONObject;

import com.kentico.delivery.core.interfaces.item.common.IQueryParameter;
import io.reactivex.Observable;

import java.util.List;

public interface IQueryService {

    String getUrl( String action, List<IQueryParameter> parameters);

    Observable<JSONObject> getRequest(String url);
}
