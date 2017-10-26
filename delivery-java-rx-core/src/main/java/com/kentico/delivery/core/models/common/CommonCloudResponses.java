package com.kentico.delivery.core.models.common;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

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
