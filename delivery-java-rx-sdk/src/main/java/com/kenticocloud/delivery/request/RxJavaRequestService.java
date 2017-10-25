package com.kenticocloud.delivery.request;


import org.json.JSONObject;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

public class RxJavaRequestService implements IRequestService {
    @Override
    public Observable<JSONObject> getRequest(String url) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();


        return null;
    }
}
