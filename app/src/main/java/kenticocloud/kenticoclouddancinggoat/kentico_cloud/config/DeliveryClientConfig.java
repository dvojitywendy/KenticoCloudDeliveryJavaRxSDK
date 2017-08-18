package kenticocloud.kenticoclouddancinggoat.kentico_cloud.config;

import android.support.annotation.NonNull;

import java.util.List;

import kenticocloud.kenticoclouddancinggoat.kentico_cloud.models.item.TypeResolver;

/**
 * Created by RichardS on 17. 8. 2017.
 */

public final class DeliveryClientConfig {

    private String _projectId;
    private List<TypeResolver> _typeResolvers;

    public DeliveryClientConfig(@NonNull String projectId, List<TypeResolver> typeResolvers){
        _projectId = projectId;
        _typeResolvers = typeResolvers;
    }

    public String getProjectId(){
        return _projectId;
    }

    public List<TypeResolver> getTypeResolvers() { return _typeResolvers; }
}
