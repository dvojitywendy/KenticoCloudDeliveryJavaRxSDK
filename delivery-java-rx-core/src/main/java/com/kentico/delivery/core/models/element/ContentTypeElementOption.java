package com.kentico.delivery.core.models.element;

public class ContentTypeElementOption {

    private String name;
    private String codename;

    public ContentTypeElementOption(
            String name,
            String codename
    ){
        this.name = name;
        this.codename = codename;
    }


    public String getName() {
        return name;
    }

    public String getCodename() {
        return codename;
    }
}
