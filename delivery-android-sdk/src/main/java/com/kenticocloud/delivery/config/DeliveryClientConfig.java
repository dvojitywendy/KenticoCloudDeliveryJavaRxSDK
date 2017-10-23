package com.kenticocloud.delivery.config;

import android.support.annotation.NonNull;

import java.util.List;

import com.kenticocloud.delivery.models.item.TypeResolver;

public final class DeliveryClientConfig {

    private String projectId;
    private List<TypeResolver<?>> typeResolvers;

    /**
     * Creates configuration
     * @param projectId Id of Kentico Cloud project
     * @param typeResolvers Resolvers used to map items from Kentico Cloud to strongly typed instances
     */
    public DeliveryClientConfig(@NonNull String projectId, List<TypeResolver<?>> typeResolvers){
        this.projectId = projectId;
        this.typeResolvers = typeResolvers;
    }

    public String getProjectId(){
        return projectId;
    }

    public List<TypeResolver<?>> getTypeResolvers() { return typeResolvers; }
}
