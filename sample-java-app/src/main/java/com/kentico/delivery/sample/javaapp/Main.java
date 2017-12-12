/*
 * Copyright 2017 Kentico s.r.o. and Richard Sustek
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.kentico.delivery.sample.javaapp;

import com.kentico.delivery.core.config.DeliveryConfig;
import com.kentico.delivery.core.interfaces.item.item.IContentItem;
import com.kentico.delivery.core.models.common.QueryConfig;
import com.kentico.delivery.core.models.exceptions.KenticoCloudResponseException;
import com.kentico.delivery.core.models.item.DeliveryItemListingResponse;
import com.kentico.delivery.core.query.item.MultipleItemQuery;
import com.kentico.delivery.java.DeliveryService;
import com.kentico.delivery.sample.javaapp.models.Cafe;

import io.reactivex.Flowable;
import io.reactivex.functions.Consumer;

public class Main {
    public static void main( final String[] args ){
        Flowable.just("Sample rxjava2 app start")
                .subscribe(new Consumer<String>() {
                    @Override public void accept(String s) {
                        System.out.println(s);
                    }
                });


        Demo demo = new Demo();
        demo.runTests();
    }
}


