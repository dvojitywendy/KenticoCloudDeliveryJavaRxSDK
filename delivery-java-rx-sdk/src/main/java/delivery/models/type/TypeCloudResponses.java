package delivery.models.type;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import delivery.deserializers.CloudDateDeserializer;
import delivery.models.common.CommonCloudResponses;

import java.util.Date;
import java.util.List;

public class TypeCloudResponses {

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class DeliverySingleTypeResponseRaw {

        DeliverySingleTypeResponseRaw(){
            // Mandatory constructor
        }

        @JsonProperty("system")
        public ContentTypeSystemAttributesRaw system;

        @JsonProperty("elements")
        public JsonNode elements;

    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class DeliveryMultipleTypeResponseRaw {

        DeliveryMultipleTypeResponseRaw(){
            // Mandatory constructor
        }

        @JsonProperty("types")
        public List<ContentTypeResponseRaw> types;

        @JsonProperty("pagination")
        public CommonCloudResponses.PaginationRaw pagination;

    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ContentTypeResponseRaw {

        ContentTypeResponseRaw(){
            // Mandatory constructor
        }

        @JsonProperty("system")
        public ContentTypeSystemAttributesRaw system;

        @JsonProperty("elements")
        public JsonNode elements;

    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ContentTypeSystemAttributesRaw {

        ContentTypeSystemAttributesRaw(){
            // Mandatory constructor
        }

        @JsonProperty("id")
        public String id;

        @JsonProperty("name")
        public String name;

        @JsonProperty("codename")
        public String codename;

        @JsonProperty("last_modified")
        @JsonDeserialize(using = CloudDateDeserializer.class)
        public Date lastModified;

    }
}
