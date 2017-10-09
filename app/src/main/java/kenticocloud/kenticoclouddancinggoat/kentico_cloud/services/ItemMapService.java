package kenticocloud.kenticoclouddancinggoat.kentico_cloud.services;

import android.support.annotation.NonNull;
import android.util.Log;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.commons.beanutils.ConstructorUtils;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.UndeclaredThrowableException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import kenticocloud.kenticoclouddancinggoat.kentico_cloud.config.DeliveryClientConfig;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.config.SDKConfig;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.enums.FieldType;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.interfaces.item.item.IContentItem;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.models.elements.AssetsElement;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.models.elements.ContentElement;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.models.elements.DateTimeElement;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.models.elements.ModularContentElement;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.models.elements.MultipleChoiceElement;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.models.elements.NumberElement;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.models.elements.RichTextElement;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.models.elements.TaxonomyElement;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.models.elements.TextElement;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.models.elements.UrlSlugElement;
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
    private List<IContentItem> _processedModularItems = new ArrayList<>();

    public ItemMapService(@NonNull DeliveryClientConfig config){
        _config = config;
    }

    public <T extends IContentItem> List<T> mapItems(Class<T> tClass, List<CloudResponses.ContentItemRaw> rawItems, JsonNode modularContent) throws KenticoCloudException {
        List<T> mappedItems = new ArrayList<>();

        for(CloudResponses.ContentItemRaw rawItem : rawItems){
            mappedItems.add(mapItem(tClass, rawItem, modularContent));
        }

        return mappedItems;
    }

    public <T extends IContentItem> T mapItem(Class<T> tClass, CloudResponses.ContentItemRaw rawItem, JsonNode modularContent) throws KenticoCloudException {
        T mappedItem = null;
        List<ContentElement<?>> elements = new ArrayList<>();

        try {
            // try getting the mapped item using the resolver if available
            mappedItem = tryGetInstanceFromTypeResolver(_config.getTypeResolvers(), rawItem.system.type);

            // Error if mapped item could not instantiated
            if (mappedItem == null){
                throw new KenticoCloudException("Cannot create a strongly typed instance of '" + rawItem.system.type + "'", null);
            }

            // try adding the item to prevent infinite recursion in case of modular items)
            boolean itemFound = false;
            for (IContentItem processedItem : this._processedModularItems) {
                if (processedItem.getSystem().getCodename().equalsIgnoreCase(rawItem.system.codename)){
                    itemFound = true;
                    break;
                }
            }

            if (!itemFound){
                this._processedModularItems.add(mappedItem);
            }

            // system attributes
            mappedItem.setContentItemSystemAttributes(MapHelper.mapSystemAttributes(rawItem.system));

            // get properties
            Field[] fields = mappedItem.getClass().getDeclaredFields();

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

                if (elementNode == null) {
                    throw new KenticoCloudException("Could not map property '" + field.getName() + "' with element mapping to '" + elementCodename + "' for type '" + rawItem.system.type + "'", null);
                }

                CloudResponses.ElementRaw elementRaw = null;

                // get element POJO
                elementRaw = _objectMapper.treeToValue(elementNode, CloudResponses.ElementRaw.class);

                // proceed as the property was annotated with {@link ElementMapping)
                if (elementRaw.value != null) {

                    ContentElement<?> element = mapElement(elementRaw.name, elementCodename, elementRaw.type, elementRaw.value, modularContent);
                    field.set(mappedItem, element);
                    elements.add(element);
                }
            }

            // elements
            mappedItem.setElements(elements);

            return mappedItem;
        }
        catch (JsonProcessingException ex) {
            Log.e(SDKConfig.AppTag, Log.getStackTraceString(ex));
            throw new KenticoCloudException("Invalid element mapping. This error indicates an issue with SDK", ex);
        }
        catch (Exception ex){
            Log.e(SDKConfig.AppTag, Log.getStackTraceString(ex));
            throw new KenticoCloudException(ex.getMessage(), ex);
        }
    }

    private ContentElement mapElement(String name, String codename, String type, JsonNode value, JsonNode modularContent){
        if (Objects.equals(type, FieldType.modular_content.toString())){
            return mapModularContentElement(name, codename, type, value, modularContent);
        }

        if (Objects.equals(type, FieldType.text.toString())){
            return new TextElement(_objectMapper, name, codename, type, value);
        }

        if (Objects.equals(type, FieldType.date_time.toString())){
            return new DateTimeElement(_objectMapper,name, codename, type, value);
        }

        if (Objects.equals(type, FieldType.rich_text.toString())){
            return new RichTextElement(_objectMapper,name, codename, type, value);
        }

        if (Objects.equals(type, FieldType.url_slug.toString())){
            return new UrlSlugElement(_objectMapper,name, codename, type, value);
        }

        if (Objects.equals(type, FieldType.asset.toString())){
            return new AssetsElement(_objectMapper,name, codename, type, value);
        }

        if (Objects.equals(type, FieldType.number.toString())){
            return new NumberElement(_objectMapper,name, codename, type, value);
        }

        if (Objects.equals(type, FieldType.taxonomy.toString())){
            return new TaxonomyElement(_objectMapper,name, codename, type, value);
        }

        if (Objects.equals(type, FieldType.multiple_choice.toString())){
            return new MultipleChoiceElement(_objectMapper,name, codename, type, value);
        }

        throw new KenticoCloudException("Field type '" + type + "' is not supported", null);
    }

    private ModularContentElement mapModularContentElement(String name, String fieldCodename, String type, JsonNode fieldValue, JsonNode modularContent) {
        ArrayList<IContentItem>  fieldModularItems = new ArrayList<>();
        Iterator<JsonNode> iterator = fieldValue.elements();

        while(iterator.hasNext()){
            String modularItemCodename = iterator.next().textValue();

            IContentItem modularItem = null;

            // take item from process items as to avoid infinite recursion
            for (IContentItem processedItem : this._processedModularItems){
                if (processedItem.getSystem().getCodename().equalsIgnoreCase(modularItemCodename)){
                    // item found in processed item, use it
                    modularItem = processedItem;
                    break;
                }
            }

            if (modularItem == null){
                // try getting the item from modular content response
                JsonNode modularItemFromResponse = modularContent.get(modularItemCodename);

                if (modularItemFromResponse == null){
                    Log.w(SDKConfig.AppTag, "Could not map modular element field '" + fieldCodename + "' because the modular item '" + modularItemCodename + "' is not present in response. Make sure you set 'Depth' parameter if you need this item.");
                    break;
                }

                CloudResponses.ContentItemRaw contentItemRaw = null;

                try {
                    contentItemRaw = _objectMapper.readValue(modularItemFromResponse.toString(), CloudResponses.ContentItemRaw.class);
                } catch (IOException e) {
                    throw new KenticoCloudException("Could not parse item response for modular element '" + fieldCodename + "'", e);
                }

                modularItem = this.mapItem(IContentItem.class, contentItemRaw, modularContent);
            }

            if (modularItem == null){
                Log.w(SDKConfig.AppTag, "Modular item '" + modularItemCodename + "' could not be resolved for field '" + fieldCodename + "'", null);
                break;
            }

            fieldModularItems.add(modularItem);
        }

        return new ModularContentElement<IContentItem>(_objectMapper, name, fieldCodename, type, fieldValue, fieldModularItems);
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
