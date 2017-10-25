package com.kenticocloud.rxjava2;


import com.kenticocloud.delivery.config.DeliveryClientConfig;
import com.kenticocloud.delivery.request.IRequestService;
import com.kenticocloud.delivery.services.DeliveryService;
import com.kenticocloud.delivery.services.IDeliveryService;

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
