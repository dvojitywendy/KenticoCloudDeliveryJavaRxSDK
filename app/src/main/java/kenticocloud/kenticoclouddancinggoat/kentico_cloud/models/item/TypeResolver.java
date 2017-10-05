package kenticocloud.kenticoclouddancinggoat.kentico_cloud.models.item;

import android.support.annotation.NonNull;

import com.google.common.base.Function;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.interfaces.item.item.IContentItem;

/**
 * Created by RichardS on 18. 8. 2017.
 */
public class TypeResolver<T extends IContentItem> {

    private Function<Void, T> _resolver;
    private String _type;

    public TypeResolver(@NonNull String type, Function<Void, T> resolver){
        _type = type;
        _resolver = resolver;
    }

    public T createInstance(){
        return _resolver.apply(null);
    }

    public Function<Void, T> getResolver(){
        return _resolver;
    }

    public String getType(){ return _type; }
}
