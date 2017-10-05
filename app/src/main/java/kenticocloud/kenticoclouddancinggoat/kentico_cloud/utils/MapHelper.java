package kenticocloud.kenticoclouddancinggoat.kentico_cloud.utils;

import kenticocloud.kenticoclouddancinggoat.kentico_cloud.interfaces.item.item.IContentItemSystemAttributes;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.models.ContentItemSystemAttributes;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.models.item.CloudResponses;

/**
 * Created by RichardS on 17. 8. 2017.
 */

public class MapHelper {

    public static IContentItemSystemAttributes mapSystemAttributes(CloudResponses.ContentItemSystemAttributesRaw systemRaw){
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
