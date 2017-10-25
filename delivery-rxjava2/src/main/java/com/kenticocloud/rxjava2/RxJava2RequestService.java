package com.kenticocloud.rxjava2;

import com.kenticocloud.delivery.request.IRequestService;

import org.json.JSONObject;

import io.reactivex.Observable;

public class RxJava2RequestService implements IRequestService {

    @Override
    public Observable<JSONObject> getRequest(String url) {
    return null;
    }
}
