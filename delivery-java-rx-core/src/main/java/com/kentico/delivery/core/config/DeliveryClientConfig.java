/*
 * Copyright 2017 Kentico s.r.o. and Richard Sustek
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.kentico.delivery.core.config;

import com.kentico.delivery.core.models.item.TypeResolver;

import java.util.List;

public final class DeliveryClientConfig {

    public final String DELIVERY_API_URL = "https://deliver.kenticocloud.com";

    private String projectId;
    private List<TypeResolver<?>> typeResolvers;

    /**
     * Creates configuration
     * @param projectId Id of Kentico Cloud project
     * @param typeResolvers Resolvers used to map items from Kentico Cloud to strongly typed instances
     */
    public DeliveryClientConfig(String projectId, List<TypeResolver<?>> typeResolvers){
        this.projectId = projectId;
        this.typeResolvers = typeResolvers;
    }

    public String getProjectId(){
        return projectId;
    }

    public List<TypeResolver<?>> getTypeResolvers() { return typeResolvers; }
}
