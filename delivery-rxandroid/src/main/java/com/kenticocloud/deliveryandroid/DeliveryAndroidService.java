package com.kenticocloud.deliveryandroid;


import com.kenticocloud.delivery.config.DeliveryClientConfig;
import com.kenticocloud.delivery.request.IRequestService;
import com.kenticocloud.delivery.services.DeliveryService;
import com.kenticocloud.delivery.services.IDeliveryService;

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
