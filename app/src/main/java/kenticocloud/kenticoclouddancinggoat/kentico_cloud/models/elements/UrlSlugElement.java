package kenticocloud.kenticoclouddancinggoat.kentico_cloud.models.elements;

import com.fasterxml.jackson.databind.JsonNode;

public class UrlSlugElement extends ContentElement<String> {
    private String _value;

    public UrlSlugElement(
            String name,
            String codename,
            String type,
            JsonNode value
    ){
        super(name, codename, type);

        _value = value.textValue();
    }

    @Override
    public String getValue(){
        return this._value;
    }
}