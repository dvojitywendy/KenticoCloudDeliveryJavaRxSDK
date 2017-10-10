package kenticocloud.kenticoclouddancinggoat.kentico_cloud.services;

import android.support.annotation.NonNull;
import android.util.Log;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.lang.reflect.Field;
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

public class ItemMapService {

    private DeliveryClientConfig config;
    private ObjectMapper objectMapper = new ObjectMapper();
    private List<IContentItem> processedModularItems = new ArrayList<>();

    public ItemMapService(@NonNull DeliveryClientConfig config){
        this.config = config;
    }

    public <TItem extends IContentItem> List<TItem> mapItems(List<CloudResponses.ContentItemRaw> rawItems, JsonNode modularContent) throws KenticoCloudException, JsonProcessingException, IllegalAccessException {
        List<TItem> mappedItems = new ArrayList<>();

        for(CloudResponses.ContentItemRaw rawItem : rawItems){
            mappedItems.add(this.<TItem>mapItem(rawItem, modularContent));
        }

        return mappedItems;
    }

    public <TItem extends IContentItem> TItem mapItem(CloudResponses.ContentItemRaw rawItem, JsonNode modularContent) throws KenticoCloudException, JsonProcessingException, IllegalAccessException {
        TItem mappedItem = null;
        List<ContentElement<?>> elements = new ArrayList<>();

            // try getting the mapped item using the resolver if available
            mappedItem = tryGetInstanceFromTypeResolver(this.config.getTypeResolvers(), rawItem.system.type);

            // Error if mapped item could not instantiated
            if (mappedItem == null){
                throw new KenticoCloudException("Cannot create a strongly typed instance of '" + rawItem.system.type + "'", null);
            }

            // try adding the item to prevent infinite recursion in case of modular items)
            boolean itemFound = false;
            for (IContentItem processedItem : this.processedModularItems) {
                if (processedItem.getSystem().getCodename().equalsIgnoreCase(rawItem.system.codename)){
                    itemFound = true;
                    break;
                }
            }

            if (!itemFound){
                this.processedModularItems.add(mappedItem);
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
                elementRaw = this.objectMapper.treeToValue(elementNode, CloudResponses.ElementRaw.class);

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

    private ContentElement mapElement(String name, String codename, String type, JsonNode value, JsonNode modularContent) throws JsonProcessingException, IllegalAccessException {
        if (Objects.equals(type, FieldType.modular_content.toString())){
            return mapModularContentElement(name, codename, type, value, modularContent);
        }

        if (Objects.equals(type, FieldType.text.toString())){
            return new TextElement(this.objectMapper, name, codename, type, value);
        }

        if (Objects.equals(type, FieldType.date_time.toString())){
            return new DateTimeElement(this.objectMapper,name, codename, type, value);
        }

        if (Objects.equals(type, FieldType.rich_text.toString())){
            return new RichTextElement(this.objectMapper,name, codename, type, value);
        }

        if (Objects.equals(type, FieldType.url_slug.toString())){
            return new UrlSlugElement(this.objectMapper,name, codename, type, value);
        }

        if (Objects.equals(type, FieldType.asset.toString())){
            return new AssetsElement(this.objectMapper,name, codename, type, value);
        }

        if (Objects.equals(type, FieldType.number.toString())){
            return new NumberElement(this.objectMapper,name, codename, type, value);
        }

        if (Objects.equals(type, FieldType.taxonomy.toString())){
            return new TaxonomyElement(this.objectMapper,name, codename, type, value);
        }

        if (Objects.equals(type, FieldType.multiple_choice.toString())){
            return new MultipleChoiceElement(this.objectMapper,name, codename, type, value);
        }

        throw new KenticoCloudException("Field type '" + type + "' is not supported", null);
    }

    private ModularContentElement mapModularContentElement(String name, String fieldCodename, String type, JsonNode fieldValue, JsonNode modularContent) throws JsonProcessingException, IllegalAccessException {
        ArrayList<IContentItem>  fieldModularItems = new ArrayList<>();
        Iterator<JsonNode> iterator = fieldValue.elements();

        while(iterator.hasNext()){
            String modularItemCodename = iterator.next().textValue();

            IContentItem modularItem = null;

            // take item from process items as to avoid infinite recursion
            for (IContentItem processedItem : this.processedModularItems){
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
                    Log.w(SDKConfig.APP_TAG, "Could not map modular element field '" + fieldCodename + "' because the modular item '" + modularItemCodename + "' is not present in response. Make sure you set 'Depth' parameter if you need this item.");
                    break;
                }

                CloudResponses.ContentItemRaw contentItemRaw = null;

                try {
                    contentItemRaw = this.objectMapper.readValue(modularItemFromResponse.toString(), CloudResponses.ContentItemRaw.class);
                } catch (IOException e) {
                    throw new KenticoCloudException("Could not parse item response for modular element '" + fieldCodename + "'", e);
                }

                modularItem = this.mapItem(contentItemRaw, modularContent);
            }

            if (modularItem == null){
                Log.w(SDKConfig.APP_TAG, "Modular item '" + modularItemCodename + "' could not be resolved for field '" + fieldCodename + "'", null);
                break;
            }

            fieldModularItems.add(modularItem);
        }

        return new ModularContentElement<IContentItem>(this.objectMapper, name, fieldCodename, type, fieldValue, fieldModularItems);
    }

    private <TItem extends IContentItem> TItem tryGetInstanceFromTypeResolver(@NonNull List<TypeResolver<?>> typeResolvers, @NonNull String type){
        // get type resolver of given type
        for(TypeResolver typeResolver : typeResolvers){
            // type resolver matched requested type
            if (typeResolver.getType().equalsIgnoreCase(type)){
                // create new instance of proper type
                return (TItem) typeResolver.createInstance();
            }
        }
        return null;
    }
}
