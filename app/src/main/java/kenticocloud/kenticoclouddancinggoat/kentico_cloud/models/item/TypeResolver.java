package kenticocloud.kenticoclouddancinggoat.kentico_cloud.models.item;

import android.support.annotation.NonNull;

import com.google.common.base.Function;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.interfaces.item.item.IContentItem;

public class TypeResolver<TItem extends IContentItem> {

    private Function<Void, TItem> _resolver;
    private String _type;

    public TypeResolver(@NonNull String type, Function<Void, TItem> resolver){
        _type = type;
        _resolver = resolver;
    }

    public TItem createInstance(){
        return _resolver.apply(null);
    }

    public Function<Void, TItem> getResolver(){
        return _resolver;
    }

    public String getType(){ return _type; }
}
