package kenticocloud.kenticoclouddancinggoat.kentico_cloud.models.elements;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import kenticocloud.kenticoclouddancinggoat.kentico_cloud.models.elements.models.AssetModel;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.models.exceptions.KenticoCloudException;

public class AssetsElement extends ContentElement<AssetModel[]> {

    private AssetModel[] _value;

    public AssetsElement(
            ObjectMapper objectMapper,
            String name,
            String codename,
            String type,
            JsonNode value
    ){
        super(objectMapper, name, codename, type);

        try {
            _value = _objectMapper.treeToValue(value, AssetModel[].class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new KenticoCloudException("Could not map Assets element for '" + codename + "'", e);
        }
    }

    @Override
    public AssetModel[] getValue(){
        return this._value;
    }
}