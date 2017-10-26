package com.kentico.delivery.sample.javaapp;

import com.kentico.delivery.core.config.DeliveryClientConfig;
import com.kentico.delivery.core.interfaces.item.item.IContentItem;
import com.kentico.delivery.core.models.item.DeliveryItemListingResponse;
import com.kentico.delivery.core.services.IDeliveryService;
import com.kentico.delivery.rxjava2.DeliveryRxJava2;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class Demo {

    private final IDeliveryService deliveryService;

    public Demo(){
        this.deliveryService = this.getDeliveryService();
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

    private IDeliveryService getDeliveryService(){
        return DeliveryRxJava2.getInstance(new DeliveryClientConfig(AppConfig.KENTICO_CLOUD_PROJECT_ID, AppConfig.getTypeResolvers()));
    }

}
