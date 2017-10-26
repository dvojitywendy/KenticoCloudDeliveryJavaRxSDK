package com.kentico.delivery.android;


import com.kentico.delivery.core.config.DeliveryClientConfig;
import com.kentico.delivery.core.request.IRequestService;
import com.kentico.delivery.core.services.DeliveryService;
import com.kentico.delivery.core.services.IDeliveryService;

public class DeliveryAndroidService extends DeliveryService implements IDeliveryService {

    private static IDeliveryService INSTANCE;
    private static IRequestService REQUEST_SERVICE;

    /**
     * Gets static instance of Delivery client for Android use
     * @param config Delivery client configuration
     * @return Delivery client instance
     */
    public static IDeliveryService getInstance(DeliveryClientConfig config) {
        if (INSTANCE == null) {
            INSTANCE = new DeliveryAndroidService(config);
        }
        if (REQUEST_SERVICE == null) {
            REQUEST_SERVICE = new AndroidRequestService();
        }
        return INSTANCE;
    }

    private DeliveryAndroidService(DeliveryClientConfig config) {
        super(config);
    }

    @Override
    public IRequestService getRequestService() {
        return REQUEST_SERVICE;
    }
}
