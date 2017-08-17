package kenticocloud.kenticoclouddancinggoat.kentico_cloud.config;

/**
 * Created by RichardS on 17. 8. 2017.
 */

public final class DeliveryClientConfig {

    private String _projectId;

    public DeliveryClientConfig(String projectId){
        _projectId = projectId;
    }

    public String getProjectId(){
        return _projectId;
    }
}
