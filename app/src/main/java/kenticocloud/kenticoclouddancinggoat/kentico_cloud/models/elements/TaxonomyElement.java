package kenticocloud.kenticoclouddancinggoat.kentico_cloud.models.elements;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import kenticocloud.kenticoclouddancinggoat.kentico_cloud.models.elements.models.AssetModel;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.models.elements.models.TaxonomyTerms;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.models.exceptions.KenticoCloudException;

public class TaxonomyElement extends ContentElement<TaxonomyTerms[]> {
    private TaxonomyTerms[] _value;

    public TaxonomyElement(
            ObjectMapper objectMapper,
            String name,
            String codename,
            String type,
            JsonNode value
    ){
        super(objectMapper, name, codename, type);

        try {
            _value = _objectMapper.treeToValue(value, TaxonomyTerms[].class);
        } catch (JsonProcessingException e) {
            throw new KenticoCloudException("Could not map Assets element for '" + codename + "'", e);
        }
    }

    @Override
    public TaxonomyTerms[] getValue(){
        return this._value;
    }
}