package delivery.models;



import delivery.interfaces.item.item.IContentItemSystemAttributes;

import java.util.Date;

/**
 * Content item system attributes
 */
public class ContentItemSystemAttributes implements IContentItemSystemAttributes{
    private String id;
    private String name;
    private String codename;
    private String type;
    private Date lastModified;
    private String language;
    private String[] sitemap_locations;

    public ContentItemSystemAttributes(
             String id,
             String name,
             String codename,
             String type,
             Date lastModified,
             String language,
            String[] sitemapLocations
            ){
        this.id = id;
        this.name = name;
        this.language = language;
        this.type = type;
        this.lastModified = lastModified;
        this.sitemap_locations = sitemapLocations;
        this.codename = codename;
    }

    public String[] getSitemapLocations() {
        return sitemap_locations;
    }

    public String getLanguage() {
        return language;
    }

    public Date getLastModified() {
        return lastModified;
    }

    public String getType() {
        return type;
    }

    public String getCodename() {
        return codename;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }
}
