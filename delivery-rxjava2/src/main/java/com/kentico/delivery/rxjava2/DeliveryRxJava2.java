package com.kentico.delivery.rxjava2;


import com.kentico.delivery.core.config.DeliveryClientConfig;
import com.kentico.delivery.core.request.IRequestService;
import com.kentico.delivery.core.services.DeliveryService;
import com.kentico.delivery.core.services.IDeliveryService;

public class DeliveryRxJava2 extends DeliveryService implements IDeliveryService {

    private static IDeliveryService INSTANCE;
    private static IRequestService REQUEST_SERVICE;

    /**
     * Gets static instance of Delivery client for Android use
     * @param config Delivery client configuration
     * @return Delivery client instance
     */
    public static IDeliveryService getInstance(DeliveryClientConfig config) {
        if (INSTANCE == null) {
            INSTANCE = new DeliveryRxJava2(config);
        }
        if (REQUEST_SERVICE == null) {
            REQUEST_SERVICE = new RxJava2RequestService();
        }
        return INSTANCE;
    }

    private DeliveryRxJava2(DeliveryClientConfig config) {
        super(config);
    }

    @Override
    public IRequestService getRequestService() {
        return REQUEST_SERVICE;
    }
}
