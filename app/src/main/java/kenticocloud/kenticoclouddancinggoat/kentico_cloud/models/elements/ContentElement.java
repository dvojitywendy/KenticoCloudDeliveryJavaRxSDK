package kenticocloud.kenticoclouddancinggoat.kentico_cloud.models.elements;

import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class ContentElement<T> {

    protected ObjectMapper objectMapper;

    private String name;
    private String codename;
    private String type;

    protected ContentElement(
            ObjectMapper objectMapper,
            String name,
            String codename,
            String type
    ){
        this.objectMapper = objectMapper;
        this.name = name;
        this.codename = codename;
        this.type = type;
    }

    public abstract T getValue();

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getCodename(){
        return this.codename;
    }

    public void setCodename(String codename){
        this.codename = codename;
    }

    public String getType(){
        return this.type;
    }

    public void setType(String type){
        this.type = type;
    }

}
