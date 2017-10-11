package kenticocloud.kenticoclouddancinggoat.kentico_cloud.models.common;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

import kenticocloud.kenticoclouddancinggoat.kentico_cloud.models.element.ElementCloudResponses;

public class CommonCloudResponses {

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class PaginationRaw {

        PaginationRaw() {
            // Mandatory constructor
        }

        @JsonProperty("skip")
        public int skip;

        @JsonProperty("limit")
        public int limit;

        @JsonProperty("count")
        public int count;

        @JsonProperty("nextPage")
        public String nextPage;
    }
}
