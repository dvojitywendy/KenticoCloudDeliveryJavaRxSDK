package com.kenticocloud.delivery.models.elements;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kenticocloud.delivery.interfaces.item.item.IContentItem;

import java.util.ArrayList;

public class ModularContentElement<TItem extends IContentItem> extends ContentElement<ArrayList<TItem>> {
    private ArrayList<TItem> value;

    public ModularContentElement(
            ObjectMapper objectMapper,
            String name,
            String codename,
            String type,
            JsonNode value,
            ArrayList<TItem> items
    ){
        super(objectMapper, name, codename, type);

        this.value = items;
    }

    @Override
    public ArrayList<TItem> getValue(){
        return this.value;
    }
}