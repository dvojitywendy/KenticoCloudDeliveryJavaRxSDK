package delivery.models.item;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import delivery.deserializers.CloudDateDeserializer;
import delivery.models.common.CommonCloudResponses;

import java.util.Date;
import java.util.List;

public class ItemCloudResponses {

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class DeliveryItemListingResponseRaw {

        DeliveryItemListingResponseRaw(){
            // Mandatory constructor
        }

        @JsonProperty("items")
        public List<ContentItemRaw> items;

        @JsonProperty("modular_content")
        public JsonNode modularContent;

        @JsonProperty("pagination")
        public CommonCloudResponses.PaginationRaw pagination;

    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class DeliveryItemResponseRaw {

        DeliveryItemResponseRaw(){
            // Mandatory constructor
        }

        @JsonProperty("item")
        public ContentItemRaw item;

        @JsonProperty("modular_content")
        public JsonNode modularContent;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ContentItemRaw {

        ContentItemRaw(){
            // Mandatory constructor
        }

        @JsonProperty("system")
        public ContentItemSystemAttributesRaw system;

        @JsonProperty("elements")
        public JsonNode elements;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ElementRaw {

        ElementRaw(){
            // Mandatory constructor
        }

        @JsonProperty("name")
        public String name;

        @JsonProperty("type")
        public String type;

        @JsonProperty("value")
        public JsonNode value;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ContentItemSystemAttributesRaw {

        ContentItemSystemAttributesRaw(){
            // Mandatory constructor
        }

        @JsonProperty("id")
        public String id;

        @JsonProperty("name")
        public String name;

        @JsonProperty("codename")
        public String codename;

        @JsonProperty("language")
        public String language;

        @JsonProperty("type")
        public String type;

        @JsonProperty("sitemap_locations")
        public String[] sitemapLocations;

        @JsonProperty("last_modified")
        @JsonDeserialize(using = CloudDateDeserializer.class)
        public Date lastModified;
    }
}

