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

import com.kentico.delivery.core.interfaces.item.common.IQueryConfig;
import com.kentico.delivery.core.models.common.QueryConfig;
import com.kentico.delivery.core.models.item.TypeResolver;

import java.util.List;

public final class DeliveryConfig implements IDeliveryConfig {

    private final DeliveryPaths deliveryPaths = new DeliveryPaths();
    private final DeliveryProperties deliveryProperties = new DeliveryProperties();

    private final String projectId;
    private final List<TypeResolver<?>> typeResolvers;
    private final String previewApiKey;

    private boolean throwExceptionForUnknownTypes = true;

    private IQueryConfig defaultQueryConfig = new QueryConfig();

    /**
     * Creates configuration
     * @param projectId Id of Kentico Cloud project
     * @param typeResolvers Resolvers used to map items from Kentico Cloud to strongly typed instances
     */
    public DeliveryConfig(String projectId, List<TypeResolver<?>> typeResolvers){
        this.projectId = projectId;
        this.typeResolvers = typeResolvers;
        this.previewApiKey = null;
    }

    /**
     * Creates configuration
     * @param projectId Id of Kentico Cloud project
     * @param typeResolvers Resolvers used to map items from Kentico Cloud to strongly typed instances
     * @param defaultQueryConfig Default query config used for all request unless overriden on query level
     */
    public DeliveryConfig(String projectId, List<TypeResolver<?>> typeResolvers, IQueryConfig defaultQueryConfig){
        this.projectId = projectId;
        this.typeResolvers = typeResolvers;
        this.defaultQueryConfig = defaultQueryConfig;
        this.previewApiKey = null;
    }

    /**
     * Creates configuration
     * @param projectId Id of Kentico Cloud project
     * @param typeResolvers Resolvers used to map items from Kentico Cloud to strongly typed instances
     * @param previewApiKey Preview API key
     */
    public DeliveryConfig(String projectId, List<TypeResolver<?>> typeResolvers, String previewApiKey){
        this.projectId = projectId;
        this.typeResolvers = typeResolvers;
        this.previewApiKey = previewApiKey;
    }

    /**
     * Creates configuration
     * @param projectId Id of Kentico Cloud project
     * @param typeResolvers Resolvers used to map items from Kentico Cloud to strongly typed instances
     * @param defaultQueryConfig Default query config used for all request unless overriden on query level
     * @param previewApiKey Preview ApiKey
     */
    public DeliveryConfig(String projectId, List<TypeResolver<?>> typeResolvers, IQueryConfig defaultQueryConfig, String previewApiKey){
        this.projectId = projectId;
        this.typeResolvers = typeResolvers;
        this.defaultQueryConfig = defaultQueryConfig;
        this.previewApiKey = previewApiKey;
    }

    @Override
    public String getProjectId(){
        return this.projectId;
    }

    @Override
    public List<TypeResolver<?>> getTypeResolvers() { return this.typeResolvers; }

    @Override
    public DeliveryPaths getDeliveryPaths(){
        return this.deliveryPaths;
    }

    @Override
    public String getDeliveryApiUrl(){
        return "https://deliver.kenticocloud.com";
    }

    @Override
    public String getDeliveryPreviewApiUrl() {
        return "https://preview-deliver.kenticocloud.com";
    }

    @Override
    public DeliveryProperties getDeliveryProperties() {
        return this.deliveryProperties;
    }

    @Override
    public IQueryConfig getDefaultQueryConfig() {
        return this.defaultQueryConfig;
    }

    @Override
    public String getPreviewApiKey() {
        return this.previewApiKey;
    }

    @Override
    public boolean getThrowExceptionForUnknownTypes() {
        return this.throwExceptionForUnknownTypes;
    }

    @Override
    public IDeliveryConfig setThrowExceptionForUnknownTypes(boolean throwException) {
        this.throwExceptionForUnknownTypes = throwException;
        return this;
    }
}
