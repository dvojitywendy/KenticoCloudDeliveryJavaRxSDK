package kenticocloud.kenticoclouddancinggoat.kentico_cloud.models.elements;

import com.fasterxml.jackson.databind.JsonNode;

public class NumberElement extends ContentElement<Double> {
    private Double _value;

    public NumberElement(
            String name,
            String codename,
            String type,
            JsonNode value
    ){
        super(name, codename, type);

        _value = value.doubleValue();
    }

    @Override
    public Double getValue(){
        return this._value;
    }
}