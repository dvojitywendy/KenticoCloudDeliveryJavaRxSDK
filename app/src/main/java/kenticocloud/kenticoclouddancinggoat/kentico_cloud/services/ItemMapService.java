package kenticocloud.kenticoclouddancinggoat.kentico_cloud.services;

import android.support.annotation.NonNull;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import kenticocloud.kenticoclouddancinggoat.kentico_cloud.config.DeliveryClientConfig;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.interfaces.item.common.IField;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.interfaces.item.item.IContentItem;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.interfaces.item.item.IContentItemSystemAttributes;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.models.item.RawModels;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.models.item.TypeResolver;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.utils.JsonHelper;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.utils.MapHelper;

/**
 * Created by RichardS on 18. 8. 2017.
 */

public class ItemMapService {

    private DeliveryClientConfig _config;

    public ItemMapService(@NonNull DeliveryClientConfig config){
        _config = config;
    }

    public <T extends IContentItem> List<T> mapItems(List<RawModels.ContentItemRaw> rawItems) throws JSONException {
        List<T> mappedItems = new ArrayList<>();

        for(RawModels.ContentItemRaw rawItem : rawItems){
            mappedItems.add((T)mapItem(rawItem));
        }

        return mappedItems;
    }

    public <T extends IContentItem> T mapItem(RawModels.ContentItemRaw rawItem) throws JSONException {
        // create typed instance of item
        T mappedItem = getItemInstanceOfType(_config.getTypeResolvers(), rawItem.system.type);

        // get and parse fields
        List<IField> fields = JsonHelper.getFields(rawItem.elements);

        // system attributes
        IContentItemSystemAttributes system = MapHelper.mapSystemAttributes(rawItem.system);

        // create content item
        mappedItem.setContentItemSystemAttributes(system);
        mappedItem.setElements(fields);

        return mappedItem;
    }

    private <T extends IContentItem> T getItemInstanceOfType(@NonNull List<TypeResolver> typeResolvers, @NonNull String type){
        // get type resolver of given type
        for(TypeResolver typeResolver : typeResolvers){
            if (typeResolver.getType().equalsIgnoreCase(type)){
                // create new instance of proper type
                return (T)typeResolver.getResolver().apply(null);
            }
        }

        throw new InstantiationError("Cannot create new instance of '" + type + "'");
    }
}
