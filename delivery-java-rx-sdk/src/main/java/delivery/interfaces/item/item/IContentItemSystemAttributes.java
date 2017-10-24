package delivery.interfaces.item.item;

import java.util.Date;

/**
 * System attributes of Kentico Cloud item
 */

public interface IContentItemSystemAttributes {

     String[] getSitemapLocations();

     String getLanguage();

     Date getLastModified();

     String getType();

     String getCodename();

     String getName();

     String getId();
}
