package com.kentico.delivery.core.models.element;

import java.util.List;

public class ContentTypeElement {

    private String name;
    private String codename;
    private String type;
    private String taxonomyGroup;
    private List<ContentTypeElementOption> options;

    public ContentTypeElement(
             String name,
             String codename,
             String type,
             String taxonomyGroup,
             List<ContentTypeElementOption> options
    ) {
        this.name = name;
        this.codename = codename;
        this.type = type;
        this.taxonomyGroup = taxonomyGroup;
        this.options = options;
    }

    public String getName() {
        return name;
    }

    public String getCodename() {
        return codename;
    }

    public String getType() {
        return type;
    }

    public String getTaxonomyGroup() {
        return taxonomyGroup;
    }

    public List<ContentTypeElementOption> getOptions() {
        return options;
    }
}
