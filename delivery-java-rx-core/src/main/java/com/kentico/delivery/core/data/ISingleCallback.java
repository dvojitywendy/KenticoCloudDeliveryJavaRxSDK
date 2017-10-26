package com.kentico.delivery.core.data;

public interface ISingleCallback <T> extends ICallback{

    void onItemLoaded(T item);

}