package kenticocloud.kenticoclouddancinggoat.kentico_cloud.models.elements;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

import kenticocloud.kenticoclouddancinggoat.kentico_cloud.interfaces.item.item.IContentItem;

public class ModularContentElement<TItem extends IContentItem> extends ContentElement<ArrayList<TItem>> {
    private ArrayList<TItem> _value;

    public ModularContentElement(
            ObjectMapper objectMapper,
            String name,
            String codename,
            String type,
            JsonNode value,
            ArrayList<TItem> items
    ){
        super(objectMapper, name, codename, type);

        _value = items;
    }

    @Override
    public ArrayList<TItem> getValue(){
        return this._value;
    }
}