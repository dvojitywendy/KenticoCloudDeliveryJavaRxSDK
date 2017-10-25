package com.kenticocloud.delivery.interfaces.item.item;



import com.kenticocloud.delivery.models.elements.ContentElement;

import java.util.List;

/**
 * Represents Kentico Cloud Content item
 */
public interface IContentItem {

     IContentItemSystemAttributes getSystem();

     void setContentItemSystemAttributes( IContentItemSystemAttributes system);

     List<ContentElement<?>> getElements();

     void setElements( List<ContentElement<?>> elements);

}
