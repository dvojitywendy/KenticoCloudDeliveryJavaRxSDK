package kenticocloud.kenticoclouddancinggoat.kentico_cloud.models.elements.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by RichardS on 9. 10. 2017.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class AssetModel {

    AssetModel(){}

    @JsonProperty("name")
    public String name;

    @JsonProperty("type")
    public String type;

    @JsonProperty("description")
    public String description;

    @JsonProperty("url")
    public String url;

    @JsonProperty("size")
    public long size;
}
