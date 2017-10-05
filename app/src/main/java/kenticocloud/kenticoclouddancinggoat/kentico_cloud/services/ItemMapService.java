package kenticocloud.kenticoclouddancinggoat.kentico_cloud.services;

import android.support.annotation.NonNull;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.commons.beanutils.ConstructorUtils;
import org.json.JSONException;

//import java.beans.PropertyDescriptor;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.UndeclaredThrowableException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import kenticocloud.kenticoclouddancinggoat.kentico_cloud.config.DeliveryClientConfig;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.interfaces.item.item.IContentItem;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.interfaces.item.item.IContentItemSystemAttributes;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.models.elements.ContentElement;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.models.elements.TextElement;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.models.exceptions.KenticoCloudException;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.models.item.CloudResponses;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.models.item.ElementMapping;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.models.item.TypeResolver;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.utils.MapHelper;

/**
 * Created by RichardS on 18. 8. 2017.
 */

public class ItemMapService {

    private DeliveryClientConfig _config;
    private ObjectMapper _objectMapper = new ObjectMapper();

    public ItemMapService(@NonNull DeliveryClientConfig config){
        _config = config;
    }

    public <T extends IContentItem> List<T> mapItems(Class<T> tClass, List<CloudResponses.ContentItemRaw> rawItems) throws KenticoCloudException {
        List<T> mappedItems = new ArrayList<>();

        for(CloudResponses.ContentItemRaw rawItem : rawItems){
            mappedItems.add(mapItem(tClass, rawItem));
        }

        return mappedItems;
    }

    public <T extends IContentItem> T mapItem(Class<T> tClass, CloudResponses.ContentItemRaw rawItem) throws KenticoCloudException {
        T mappedItem = null;

        try {
            // try getting the mapped item using the resolver if available
            mappedItem = tryGetInstanceFromTypeResolver(_config.getTypeResolvers(), rawItem.system.type);


            // try getting the mapped item using class parameter if type resolver was not found
            if (mappedItem == null){
                mappedItem = tClass.cast(ConstructorUtils.invokeConstructor(tClass, null));
            }

            // Error if mapped item could not instantiated
            if (mappedItem == null){
                throw new KenticoCloudException("Cannot create a strongly typed instance of '" + rawItem.system.type + "'", null);
            }

            // get properties
            Field[] fields = tClass.getDeclaredFields();

            // map elements into all linked properties
            for (Field field : fields) {
                ElementMapping elementMapping = field.getAnnotation(ElementMapping.class);

                // property does not have element mapping, skip it
                if (elementMapping == null) {
                    continue;
                }

                // see if the element exists in Kentico Cloud Json response
                String elementCodename = elementMapping.value();
                JsonNode elementNode = rawItem.elements.get(elementCodename);

                if (elementNode == null){
                    throw new KenticoCloudException("Could not map property '" + field.getName() + "' with element mapping to '" + elementCodename + "' for type '" + rawItem.system.type + "'", null);
                }

                CloudResponses.ElementRaw elementRaw = null;

                try {
                    // get element POJO
                    elementRaw = _objectMapper.treeToValue(elementNode, CloudResponses.ElementRaw.class);
                } catch (JsonProcessingException e) {
                    throw new KenticoCloudException("Invalid element mapping. This error indicates an issue with SDK", e);
                }

                // proceed as the property was annotated with {@link ElementMapping)
                if (elementRaw.value != null) {

                    field.set(mappedItem, mapElement(elementRaw.name, elementCodename, elementRaw.type, elementRaw.value));
                }
            }
        } catch (NoSuchMethodException |
                IllegalAccessException |
                InvocationTargetException |
                InstantiationException e) {
            handleReflectionException(e);
        }

        //Iterator<JsonNode> iterator = rawItem.elements.elements();
       // while (iterator.hasNext()) {
         //   JsonNode node = iterator.next();

        //    String codename = node.asText();
      //  }

        // get and parse fields
        //List<IField> fields = JsonHelper.getFields(asef);

        // system attributes
        IContentItemSystemAttributes system = MapHelper.mapSystemAttributes(rawItem.system);

        // create content item
        //mappedItem.setContentItemSystemAttributes(system);
       // mappedItem.setElements(fields);

        return mappedItem;
    }

    private static void handleReflectionException(Exception ex) {
        if (ex instanceof NoSuchMethodException) {
            throw new IllegalStateException("Method not found: " + ex.getMessage());
        }
        if (ex instanceof IllegalAccessException) {
            throw new IllegalStateException("Could not access method: " + ex.getMessage());
        }
        if (ex instanceof RuntimeException) {
            throw (RuntimeException) ex;
        }
        throw new UndeclaredThrowableException(ex);
    }

    private ContentElement mapElement(String name, String codename, String type, Object value){
        if (Objects.equals(type, "text")){
            return new TextElement(name, codename, type, value.toString());

        }
        return null;
    }

    private <T extends IContentItem> T tryGetInstanceFromTypeResolver(@NonNull List<TypeResolver> typeResolvers, @NonNull String type){
        // get type resolver of given type
        for(TypeResolver typeResolver : typeResolvers){
            // type resolver matched requested type
            if (typeResolver.getType().equalsIgnoreCase(type)){
                // create new instance of proper type
                return (T)typeResolver.createInstance();
            }
        }
        return null;
    }
}
