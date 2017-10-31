/*
 * Copyright 2017 Kentico s.r.o. and Richard Sustek
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.kentico.delivery.core.services.map;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kentico.delivery.core.config.DeliveryConfig;
import com.kentico.delivery.core.elements.AssetsElement;
import com.kentico.delivery.core.elements.ContentElement;
import com.kentico.delivery.core.elements.DateTimeElement;
import com.kentico.delivery.core.elements.ModularContentElement;
import com.kentico.delivery.core.elements.MultipleChoiceElement;
import com.kentico.delivery.core.elements.NumberElement;
import com.kentico.delivery.core.elements.RichTextElement;
import com.kentico.delivery.core.elements.TaxonomyElement;
import com.kentico.delivery.core.elements.TextElement;
import com.kentico.delivery.core.elements.UrlSlugElement;
import com.kentico.delivery.core.enums.FieldType;
import com.kentico.delivery.core.interfaces.item.item.IContentItem;
import com.kentico.delivery.core.models.exceptions.KenticoCloudException;
import com.kentico.delivery.core.models.item.ContentItemSystemAttributes;
import com.kentico.delivery.core.models.item.ElementMapping;
import com.kentico.delivery.core.models.item.ItemCloudResponses;
import com.kentico.delivery.core.models.item.TypeResolver;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class ItemMapService {

    private DeliveryConfig config;
    private ObjectMapper objectMapper;
    private List<IContentItem> processedModularItems = new ArrayList<>();

    public ItemMapService(DeliveryConfig config, ObjectMapper objectMapper){
        this.config = config;
        this.objectMapper = objectMapper;
    }

    public <TItem extends IContentItem> List<TItem> mapItems(List<ItemCloudResponses.ContentItemRaw> rawItems, JsonNode modularContent) throws KenticoCloudException, JsonProcessingException, IllegalAccessException {
        List<TItem> mappedItems = new ArrayList<>();

        for(ItemCloudResponses.ContentItemRaw rawItem : rawItems){
            mappedItems.add(this.<TItem>mapItem(rawItem, modularContent));
        }

        return mappedItems;
    }

    public <TItem extends IContentItem> TItem mapItem(ItemCloudResponses.ContentItemRaw rawItem, JsonNode modularContent) throws KenticoCloudException, JsonProcessingException, IllegalAccessException {
        TItem mappedItem;
        List<ContentElement<?>> elements = new ArrayList<>();

        // try getting the mapped item using the resolver if available
        try {
            mappedItem = tryGetInstanceFromTypeResolver(this.config.getTypeResolvers(), rawItem.system.type);
        } catch (Exception ex) {
            throw new KenticoCloudException("Could not create typed item", ex);
        }

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
            mappedItem.setContentItemSystemAttributes(this.mapSystemAttributes(rawItem.system));

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

                ItemCloudResponses.ElementRaw elementRaw;

                // get element
                elementRaw = this.objectMapper.treeToValue(elementNode, ItemCloudResponses.ElementRaw.class);

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
                    throw new KenticoCloudException("Could not map modular element field '" + fieldCodename + "' because the modular item '" + modularItemCodename + "' is not present in response. Make sure you set 'Depth' parameter if you need this item.", null);
                }

                ItemCloudResponses.ContentItemRaw contentItemRaw;

                try {
                    contentItemRaw = this.objectMapper.readValue(modularItemFromResponse.toString(), ItemCloudResponses.ContentItemRaw.class);
                } catch (IOException e) {
                    throw new KenticoCloudException("Could not parse item response for modular element '" + fieldCodename + "'", e);
                }

                modularItem = this.mapItem(contentItemRaw, modularContent);
            }

            if (modularItem == null){
                throw new KenticoCloudException("Modular item '" + modularItemCodename + "' could not be resolved for field '" + fieldCodename + "'", null);
            }

            fieldModularItems.add(modularItem);
        }

        return new ModularContentElement<>(this.objectMapper, name, fieldCodename, type, fieldValue, fieldModularItems);
    }

    @SuppressWarnings("unchecked")
    private <TItem extends IContentItem> TItem tryGetInstanceFromTypeResolver( List<TypeResolver<?>> typeResolvers,  String type) throws Exception {
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

    private ContentItemSystemAttributes mapSystemAttributes(ItemCloudResponses.ContentItemSystemAttributesRaw systemRaw){
        return new ContentItemSystemAttributes(
                systemRaw.id,
                systemRaw.name,
                systemRaw.codename,
                systemRaw.type,
                systemRaw.lastModified,
                systemRaw.language,
                systemRaw.sitemapLocations
        );
    }
}
