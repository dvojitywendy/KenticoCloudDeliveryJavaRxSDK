package kenticocloud.kenticoclouddancinggoat.kentico_cloud.models.item;

import android.support.annotation.NonNull;

import com.google.common.base.Function;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.interfaces.item.item.IContentItem;

/**
 * Created by RichardS on 18. 8. 2017.
 */
public class TypeResolver {

    private Function<Void, IContentItem> _resolver;
    private String _type;

    public TypeResolver(@NonNull String type, Function<Void, IContentItem> resolver){
        _type = type;
        _resolver = resolver;
    }

    public Function<Void, IContentItem> getResolver(){
        return _resolver;
    }

    public String getType(){ return _type; }
}
