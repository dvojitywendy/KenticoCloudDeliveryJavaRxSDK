package delivery.interfaces.item.item;



import delivery.models.elements.ContentElement;

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
