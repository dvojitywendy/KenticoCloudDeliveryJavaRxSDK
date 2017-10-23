package com.kenticocloud.delivery.models.elements;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.kenticocloud.delivery.models.elements.models.TaxonomyTerms;
import com.kenticocloud.delivery.models.exceptions.KenticoCloudException;

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
            _value = this.objectMapper.treeToValue(value, TaxonomyTerms[].class);
        } catch (JsonProcessingException e) {
            throw new KenticoCloudException("Could not map Assets element for '" + codename + "'", e);
        }
    }

    @Override
    public TaxonomyTerms[] getValue(){
        return this._value;
    }
}