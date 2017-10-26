package com.kentico.delivery.core.models.type;

import com.kentico.delivery.core.models.element.ContentTypeElement;

import java.util.List;

public class ContentType {

    private ContentTypeSystemAttributes system;
    private List<ContentTypeElement> elements;

    public ContentType(
            ContentTypeSystemAttributes system,
            List<ContentTypeElement> elements
    ){
        this.system = system;
        this.elements = elements;
    }


    public ContentTypeSystemAttributes getSystem() {
        return this.system;
    }

    public List<ContentTypeElement> getElements() {
        return this.elements;
    }
}
