package com.kentico.delivery.core.services.map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kentico.delivery.core.config.DeliveryClientConfig;
import com.kentico.delivery.core.models.element.ContentTypeElement;
import com.kentico.delivery.core.models.element.ContentTypeElementOption;
import com.kentico.delivery.core.models.element.ElementCloudResponses;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ContentElementMapService {

    private DeliveryClientConfig config;
    private ObjectMapper objectMapper;

    public ContentElementMapService( DeliveryClientConfig config,  ObjectMapper objectMapper){
        this.config = config;
        this.objectMapper = objectMapper;
    }

    public List<ContentTypeElement> mapContentTypeElements(JsonNode elementsNode) throws JsonProcessingException {
        List<ContentTypeElement> elements = new ArrayList<>();
        Iterator<Map.Entry<String, JsonNode>> iterator = elementsNode.fields();

        for (Iterator<Map.Entry<String, JsonNode>> it = iterator; it.hasNext(); ) {

            String elementCodename = it.next().getKey();
            JsonNode elementNode = it.next().getValue();

            elements.add(this.mapContentTypeElement(elementCodename, elementNode));
        }

        return elements;
    }

    public ContentTypeElement mapContentTypeElement(String elementCodename, JsonNode elementNode) throws JsonProcessingException {
        String name = elementNode.get("name") == null ? null : elementNode.get("name").asText();
        String type = elementNode.get("type") == null ? null : elementNode.get("type").asText();
        String taxonomyGroup = elementNode.get("taxonomy_group") == null ? null : elementNode.get("taxonomy_group").asText();
        ElementCloudResponses.ContentTypeElementOptionRaw[] options = elementNode.get("options") == null ? null : this.objectMapper.treeToValue(elementNode.get("options"), ElementCloudResponses.ContentTypeElementOptionRaw[].class);

        return new ContentTypeElement(
                name,
                elementCodename,
                type,
                taxonomyGroup,
                options == null ? null : this.mapOptions(options));
    }

    private List<ContentTypeElementOption> mapOptions(ElementCloudResponses.ContentTypeElementOptionRaw[] optionsRaw){
        List<ContentTypeElementOption> options = new ArrayList<>();

        for(ElementCloudResponses.ContentTypeElementOptionRaw optionRaw : optionsRaw){
            options.add(new ContentTypeElementOption(optionRaw.name, optionRaw.codename));
        }

        return options;
    }
}


