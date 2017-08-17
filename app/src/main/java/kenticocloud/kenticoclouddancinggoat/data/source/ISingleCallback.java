package kenticocloud.kenticoclouddancinggoat.data.source;

import java.util.List;

import kenticocloud.kenticoclouddancinggoat.kentico_cloud.interfaces.item.item.IContentItem;

/**
 * Created by RichardS on 17. 8. 2017.
 */

public interface ISingleCallback <T> extends ICallback{

    void onItemLoaded(T item);

}