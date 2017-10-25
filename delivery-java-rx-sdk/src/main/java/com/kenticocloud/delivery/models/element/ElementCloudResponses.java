package com.kenticocloud.delivery.models.element;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ElementCloudResponses {

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ContentTypeElementRaw {

        ContentTypeElementRaw() {
            // Mandatory constructor
        }

        @JsonProperty("name")
        public String name;

        @JsonProperty("codename")
        public String codename;

        @JsonProperty("language")
        public String language;

        @JsonProperty("type")
        public String type;

        @JsonProperty("taxonomy_group")
        public String taxonomyGroup;

        @JsonProperty("options")
        public List<ContentTypeElementOptionRaw> options;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ContentTypeElementOptionRaw {

        ContentTypeElementOptionRaw() {
            // Mandatory constructor
        }

        @JsonProperty("name")
        public String name;

        @JsonProperty("codename")
        public String codename;
    }
}
