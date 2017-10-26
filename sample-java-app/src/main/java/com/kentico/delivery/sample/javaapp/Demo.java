package com.kentico.delivery.sample.javaapp;

import com.kentico.delivery.core.interfaces.item.item.IContentItem;
import com.kentico.delivery.core.models.item.DeliveryItemListingResponse;
import com.kentico.delivery.core.services.IDeliveryService;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class Demo {

    private final IDeliveryService deliveryService;

    public Demo(IDeliveryService deliveryService){
        this.deliveryService = deliveryService;
    }

    public void runTests(){
        this.deliveryService.items().get().subscribe(new Observer<DeliveryItemListingResponse<IContentItem>>() {
            @Override
            public void onSubscribe(Disposable disposable) {

            }

            @Override
            public void onNext(DeliveryItemListingResponse<IContentItem> iContentItemDeliveryItemListingResponse) {

            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

}
