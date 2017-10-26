package com.kentico.delivery.core.models.elements.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Model representing Asset response
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
