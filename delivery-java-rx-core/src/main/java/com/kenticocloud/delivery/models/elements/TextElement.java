package com.kenticocloud.delivery.models.elements;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TextElement extends ContentElement<String> {
    private String value;

    public TextElement(
            ObjectMapper objectMapper,
            String name,
            String codename,
            String type,
            JsonNode value
    ){
        super(objectMapper, name, codename, type);

        this.value = value.textValue();
    }

    @Override
    public String getValue(){
        return this.value;
    }
}