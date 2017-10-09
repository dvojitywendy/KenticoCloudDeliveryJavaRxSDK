package kenticocloud.kenticoclouddancinggoat.kentico_cloud.models.elements;

public abstract class ContentElement<T> {
    private String _name;
    private String _codename;
    private String _type;

    protected ContentElement(
            String name,
            String codename,
            String type
    ){
        _name = name;
        _codename = codename;
        _type = type;
    }

    public abstract T getValue();

    public String getName(){
        return this._name;
    }

    public void setName(String name){
        this._name = name;
    }

    public String getCodename(){
        return this._codename;
    }

    public void setCodename(String codename){
        this._codename = codename;
    }

    public String getType(){
        return this._type;
    }

    public void setType(String type){
        this._type = type;
    }

}
