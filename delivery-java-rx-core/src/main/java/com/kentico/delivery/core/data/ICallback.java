package com.kentico.delivery.core.data;

public interface ICallback {

    void onDataNotAvailable();

    void onError(Throwable e);
}
