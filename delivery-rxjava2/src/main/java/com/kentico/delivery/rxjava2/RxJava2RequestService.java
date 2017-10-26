package com.kentico.delivery.rxjava2;

import com.kentico.delivery.core.request.IRequestService;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class RxJava2RequestService implements IRequestService {

    /*
    TODO: verify that its safe to define okhttpclient statically, reason by doing so is so that its not initialized with every request
     */
    private static final OkHttpClient client = new OkHttpClient();

    @Override
    public Observable<JSONObject> getRequest(final String url){

        return Observable.defer(new Callable<ObservableSource<JSONObject>>() {
            @Override public Observable<JSONObject> call() {
                try {
                    Response response = client.newCall(new Request.Builder().url(url).build()).execute();

                    if (!response.isSuccessful()){
                        throw new NullPointerException("Response from server was not successful: " + response.message());
                    }

                    ResponseBody body = response.body();

                    if (body == null){
                        throw new NullPointerException("Response from server is empty");
                    }

                    return Observable.just(new JSONObject(body.string()));
                } catch (IOException e) {
                    return Observable.error(e);
                } catch (JSONException e) {
                    return Observable.error(e);
                }
            }
        });
    }
}
