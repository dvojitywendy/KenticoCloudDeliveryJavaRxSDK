package kenticocloud.kenticoclouddancinggoat.kentico_cloud.utils;

import kenticocloud.kenticoclouddancinggoat.kentico_cloud.interfaces.item.item.IContentItemSystemAttributes;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.models.ContentItemSystemAttributes;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.models.item.RawModels;

/**
 * Created by RichardS on 17. 8. 2017.
 */

public class MapHelper {

    public static IContentItemSystemAttributes mapSystemAttributes(RawModels.ContentItemSystemAttributesRaw systemRaw){
        return new ContentItemSystemAttributes(
                systemRaw.id,
                systemRaw.name,
                systemRaw.codename,
                systemRaw.type,
                systemRaw.last_modified,
                systemRaw.language,
                systemRaw.sitemap_locations
        );
    }
}
