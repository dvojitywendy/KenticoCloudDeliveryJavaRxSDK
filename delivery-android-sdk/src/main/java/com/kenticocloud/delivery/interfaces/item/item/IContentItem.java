package com.kenticocloud.delivery.interfaces.item.item;

import android.support.annotation.NonNull;

import java.util.List;

import com.kenticocloud.delivery.models.elements.ContentElement;

/**
 * Represents Kentico Cloud Content item
 */
public interface IContentItem {

     IContentItemSystemAttributes getSystem();

     void setContentItemSystemAttributes(@NonNull IContentItemSystemAttributes system);

     List<ContentElement<?>> getElements();

     void setElements(@NonNull List<ContentElement<?>> elements);

}
