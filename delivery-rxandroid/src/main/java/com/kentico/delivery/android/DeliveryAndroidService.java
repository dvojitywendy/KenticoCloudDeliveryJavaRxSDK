/*
 * Copyright 2017 Kentico s.r.o. and Richard Sustek
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.kentico.delivery.android;


import com.kentico.delivery.core.adapters.IHttpAdapter;
import com.kentico.delivery.core.config.DeliveryClientConfig;
import com.kentico.delivery.core.adapters.IRxAdapter;
import com.kentico.delivery.core.services.DeliveryService;
import com.kentico.delivery.core.services.IDeliveryService;

public class DeliveryAndroidService extends DeliveryService implements IDeliveryService {

    private static IDeliveryService INSTANCE;

    private static IRxAdapter rxAdapter;
    private static IHttpAdapter httpAdapter;

    /**
     * Gets static instance of Delivery client for Android use
     * @param config Delivery client configuration
     * @return Delivery client instance
     */
    public static IDeliveryService getInstance(DeliveryClientConfig config) {
        if (INSTANCE == null) {
            INSTANCE = new DeliveryAndroidService(config);
        }
        if (rxAdapter == null) {
            rxAdapter = new AndroidRxAdapter();
        }
        if (httpAdapter == null) {
            httpAdapter = new AndroidHttpAdapter();
        }
        return INSTANCE;
    }

    private DeliveryAndroidService(DeliveryClientConfig config) {
        super(config);
    }

    @Override
    public IRxAdapter getRxAdapter() {
        return rxAdapter;
    }

    @Override
    public IHttpAdapter getHttpAdapter() {
        return httpAdapter;
    }

}
