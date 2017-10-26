package com.kentico.delivery.android;

import com.androidnetworking.common.Priority;
import com.kentico.delivery.core.request.IRequestService;
import com.rx2androidnetworking.Rx2AndroidNetworking;

import org.json.JSONObject;

import io.reactivex.Observable;

public class AndroidRequestService implements IRequestService {

    @Override
    public Observable<JSONObject> getRequest(String url) {
        return Rx2AndroidNetworking.get(url)
                .setPriority(Priority.MEDIUM)
                .build()
                .getJSONObjectObservable();
    }
}
