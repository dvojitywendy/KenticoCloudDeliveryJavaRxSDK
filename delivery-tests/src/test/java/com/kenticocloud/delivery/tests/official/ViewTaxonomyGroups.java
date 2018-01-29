/*
 * Copyright 2017 Kentico s.r.o. and Richard Sustek
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.kenticocloud.delivery.tests.official;

import com.kenticocloud.delivery_core.config.DeliveryConfig;
import com.kenticocloud.delivery_core.models.item.TypeResolver;
import com.kenticocloud.delivery_core.models.taxonomy.DeliveryTaxonomyListingResponse;
import com.kenticocloud.delivery_core.models.taxonomy.Taxonomy;
import com.kenticocloud.delivery_core.services.IDeliveryService;
import com.kenticocloud.delivery_rx.DeliveryService;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class ViewTaxonomyGroups extends BaseOfficialTest{

    @Override
    public void Example() {

        // Prepare array to hold all your type resolvers
        List<TypeResolver<?>> typeResolvers = new ArrayList<>();

        // Initialize DeliveryService for Java projects
        IDeliveryService deliveryService = new DeliveryService(new DeliveryConfig("e391c776-9d1e-4e1a-8a5a-1c327c2586b6", typeResolvers));

        // Use simple request to get data
        List<Taxonomy> taxonomies = deliveryService.taxonomies()
                .limitParameter(3)
                .get()
                .getTaxonomies();

        // Test, not part of example
        assertThat(taxonomies.get(0), instanceOf(Taxonomy.class));

        // Use RxJava2 to get the data
        deliveryService.taxonomies()
                .limitParameter(3)
                .getObservable()
                .subscribe(new Observer<DeliveryTaxonomyListingResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(DeliveryTaxonomyListingResponse response) {
                        // Get taxonomies
                        List<Taxonomy> taxonomies = response.getTaxonomies();

                        // Print name of first taxonomy
                        System.out.println(taxonomies.get(0).getSystem().getName());

                        // This is NOT part of the example
                        assertThat(taxonomies.get(0), instanceOf(Taxonomy.class));
                    }

                    @Override
                    public void onError(Throwable e) {
                        // We should not get an error in official example
                        assertEquals(true, false);
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}


