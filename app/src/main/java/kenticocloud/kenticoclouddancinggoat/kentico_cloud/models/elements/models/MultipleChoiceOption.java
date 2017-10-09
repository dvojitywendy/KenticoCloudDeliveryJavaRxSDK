package kenticocloud.kenticoclouddancinggoat.kentico_cloud.models.elements.models;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by RichardS on 9. 10. 2017.
 */

public class MultipleChoiceOption {

    MultipleChoiceOption(){}

    @JsonProperty("name")
    public String name;

    @JsonProperty("codename")
    public String codename;

}
