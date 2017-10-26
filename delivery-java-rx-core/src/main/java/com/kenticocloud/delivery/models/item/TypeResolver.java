package com.kenticocloud.delivery.models.item;

import com.kenticocloud.delivery.interfaces.item.item.IContentItem;

import io.reactivex.functions.Function;

public class TypeResolver<TItem extends IContentItem> {

    private Function<Void, TItem> resolver;
    private String type;

    /**
     * Type resolvers are used to create strongy typed objects for given Kentico content type identified by its codename
     * @param type Codename of Kentico Content type
     * @param resolver Function that returns instance of IContentItem class representing content type
     */
    public TypeResolver( String type, Function<Void, TItem> resolver){
        this.type = type;
        this.resolver = resolver;
    }

    public TItem createInstance() throws Exception {
        return this.resolver.apply(null);
    }

    public Function<Void, TItem> getResolver(){
        return this.resolver;
    }

    public String getType(){ return this.type; }
}
