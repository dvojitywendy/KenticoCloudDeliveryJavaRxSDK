/*
 * Copyright 2017 Kentico s.r.o. and Richard Sustek
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

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
