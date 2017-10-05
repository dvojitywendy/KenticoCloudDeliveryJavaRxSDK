package kenticocloud.kenticoclouddancinggoat.kentico_cloud.models.elements;

public class TextElement extends ContentElement {
    private String _value;

    public TextElement(
            String name,
            String codename,
            String type,
            String value
    ){
        super(name, codename, type);

        _value = value;
    }

    public String getValue(){
        return this._value;
    }

    public void setValue(String value){
        _value = value;
    }
}