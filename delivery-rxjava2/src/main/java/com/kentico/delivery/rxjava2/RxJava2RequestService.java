package com.kentico.delivery.rxjava2;

import com.kentico.delivery.core.request.IRequestService;

import org.json.JSONObject;

import io.reactivex.Observable;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class RxJava2RequestService implements IRequestService {

    @Override
    public Observable<JSONObject> getRequest(String url){
        throw new NotImplementedException();
    }
}
