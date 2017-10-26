package com.kenticocloud.delivery.models.elements.models;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Model representing multiple choice option response
 */
public class MultipleChoiceOption {

    MultipleChoiceOption(){}

    @JsonProperty("name")
    public String name;

    @JsonProperty("codename")
    public String codename;

}
