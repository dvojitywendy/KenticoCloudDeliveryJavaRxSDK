package kenticocloud.kenticoclouddancinggoat.kentico_cloud.models.item;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Date;
import java.util.List;

/**
 * Created by RichardS on 17. 8. 2017.
 */

public class RawModels{

    public class DeliveryItemResponseRaw {
        public ContentItemRaw item;
    }

    public class DeliveryItemListingResponseRaw {
        public List<ContentItemRaw> items;
    }

    public class ContentItemRaw{
        public ContentItemSystemAttributesRaw system;
        public Object elements;
    }

    public class ContentItemSystemAttributesRaw{
        public String id;
        public String name;
        public String codename;
        public String language;
        public String type;
        public String[] sitemap_locations;
        public Date last_modified;
    }
}

