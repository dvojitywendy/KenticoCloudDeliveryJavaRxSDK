package kenticocloud.kenticoclouddancinggoat.kentico_cloud.interfaces.item.item;

import java.util.Date;

/**
 * Created by RichardS on 17. 8. 2017.
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
