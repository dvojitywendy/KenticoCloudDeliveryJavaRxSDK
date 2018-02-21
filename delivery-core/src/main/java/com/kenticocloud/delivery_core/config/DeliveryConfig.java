/*
 * Copyright 2018 Kentico s.r.o. and Richard Sustek
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.kenticocloud.delivery_core.config;

import com.kenticocloud.delivery_core.interfaces.item.common.IQueryConfig;
import com.kenticocloud.delivery_core.models.common.QueryConfig;
import com.kenticocloud.delivery_core.models.item.TypeResolver;

import java.util.List;

public final class DeliveryConfig implements IDeliveryConfig{

    private final DeliveryPaths deliveryPaths = new DeliveryPaths();
    private final DeliveryProperties deliveryProperties = new DeliveryProperties();

    private final String projectId;
    private List<TypeResolver<?>> typeResolvers;
    private String previewApiKey;
    private String securedApiKey;
    private boolean throwExceptionForUnknownTypes = true;
    private IQueryConfig defaultQueryConfig = new QueryConfig();

    /**
     * Creates configuration builder object
     * @param projectId Id of Kentico Cloud project
     */
    public DeliveryConfig(String projectId){
        this.projectId = projectId;
    }

    /**
     * Creates configuration builder object
     * @param projectId Id of Kentico Cloud project
     */
    public static DeliveryConfig newConfig(String projectId){
        return new DeliveryConfig(projectId);
    }

    public DeliveryConfig setTypeResolvers(List<TypeResolver<?>> resolvers){
        this.typeResolvers = resolvers;
        return this;
    }

    public DeliveryConfig setPreviewApiKey(String previewApiKey){
        this.previewApiKey = previewApiKey;
        return this;
    }

    public DeliveryConfig setSecuredApiKey(String securedApiKey){
        this.securedApiKey = securedApiKey;
        return this;
    }

    public DeliveryConfig setThrowExceptionForUnknownTypes(boolean throwException){
        this.throwExceptionForUnknownTypes = throwException;
        return this;
    }

    public DeliveryConfig setDefaultQueryConfig(IQueryConfig queryConfig){
        this.defaultQueryConfig = queryConfig;
        return this;
    }

    public IDeliveryConfig BuildConfig(){
        return this;
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
    public String getSecuredApiKey() {
        return this.securedApiKey;
    }

    @Override
    public boolean getThrowExceptionForUnknownTypes() {
        return this.throwExceptionForUnknownTypes;
    }
}
