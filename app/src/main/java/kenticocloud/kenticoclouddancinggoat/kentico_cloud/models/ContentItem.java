package kenticocloud.kenticoclouddancinggoat.kentico_cloud.models;

import android.support.annotation.NonNull;

import java.util.List;

import kenticocloud.kenticoclouddancinggoat.kentico_cloud.interfaces.item.item.IContentItem;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.interfaces.item.item.IContentItemSystemAttributes;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.models.elements.ContentElement;

/**
 * Base class for all types representing Kentico Cloud items
 */
public abstract class ContentItem implements IContentItem {

    private IContentItemSystemAttributes system;
    private List<ContentElement<?>> elements;

    @Override
    public IContentItemSystemAttributes getSystem() {
        return this.system;
    }

    @Override
    public void setContentItemSystemAttributes(@NonNull IContentItemSystemAttributes system) {
        this.system = system;
    }

    @Override
    public List<ContentElement<?>> getElements() {
        return this.elements;
    }

    @Override
    public void setElements(@NonNull List<ContentElement<?>> elements) {
        this.elements = elements;
    }

}