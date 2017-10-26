package com.kenticocloud.delivery.data;

public interface ISingleCallback <T> extends ICallback{

    void onItemLoaded(T item);

}