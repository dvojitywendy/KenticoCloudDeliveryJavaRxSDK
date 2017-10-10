package kenticocloud.kenticoclouddancinggoat.data.source;


import java.util.List;

import kenticocloud.kenticoclouddancinggoat.kentico_cloud.interfaces.item.item.IContentItem;

public interface IMultipleCallback<T> extends ICallback{

    void onItemsLoaded(List<T> items);

}