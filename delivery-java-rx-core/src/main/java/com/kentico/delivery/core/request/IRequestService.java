package com.kentico.delivery.core.request;

import org.json.JSONObject;

import io.reactivex.Observable;

public interface IRequestService {

    Observable<JSONObject> getRequest(String url);

}
