package com.kenticocloud.delivery.models.elements;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.kenticocloud.delivery.models.elements.models.AssetModel;
import com.kenticocloud.delivery.models.exceptions.KenticoCloudException;

public class AssetsElement extends ContentElement<AssetModel[]> {

    private AssetModel[] value;

    public AssetsElement(
            ObjectMapper objectMapper,
            String name,
            String codename,
            String type,
            JsonNode value
    ){
        super(objectMapper, name, codename, type);

        try {
            this.value = objectMapper.treeToValue(value, AssetModel[].class);
        } catch (JsonProcessingException e) {
            throw new KenticoCloudException("Could not map Assets element for '" + codename + "'", e);
        }
    }

    @Override
    public AssetModel[] getValue(){
        return this.value;
    }
}