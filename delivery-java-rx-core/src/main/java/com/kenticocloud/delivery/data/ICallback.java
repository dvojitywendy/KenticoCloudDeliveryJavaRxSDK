package com.kenticocloud.delivery.data;

public interface ICallback {

    void onDataNotAvailable();

    void onError(Throwable e);
}
