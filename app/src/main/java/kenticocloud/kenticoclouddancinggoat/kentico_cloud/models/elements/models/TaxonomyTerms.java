package kenticocloud.kenticoclouddancinggoat.kentico_cloud.models.elements.models;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Model representing taxonomy terms response
 */
public class TaxonomyTerms {

    TaxonomyTerms(){}

    @JsonProperty("name")
    public String name;

    @JsonProperty("codename")
    public String codename;

    @JsonProperty("terms")
    public TaxonomyTerms[] terms;

}
