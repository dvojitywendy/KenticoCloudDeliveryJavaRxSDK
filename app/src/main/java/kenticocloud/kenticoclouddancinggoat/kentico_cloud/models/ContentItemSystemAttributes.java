package kenticocloud.kenticoclouddancinggoat.kentico_cloud.models;

import android.support.annotation.NonNull;

import java.util.Date;

import kenticocloud.kenticoclouddancinggoat.kentico_cloud.interfaces.item.item.IContentItemSystemAttributes;

/**
 * Created by RichardS on 17. 8. 2017.
 */

public class ContentItemSystemAttributes implements IContentItemSystemAttributes{
    private String _id;
    private String _name;
    private String _codename;
    private String _type;
    private Date _lastModified;
    private String _language;
    private String[] _sitemap_locations;

    public ContentItemSystemAttributes(
            @NonNull String id,
            @NonNull String name,
            @NonNull String codename,
            @NonNull String type,
            @NonNull Date lastModified,
            @NonNull String language,
            String[] sitemapLocations
            ){
        _id = id;
        _name = name;
        _language = language;
        _type = type;
        _lastModified = lastModified;
        _sitemap_locations = sitemapLocations;
        _codename = codename;
    }

    public String[] getSitemapLocations() {
        return _sitemap_locations;
    }

    public String getLanguage() {
        return _language;
    }

    public Date getLastModified() {
        return _lastModified;
    }

    public String getType() {
        return _type;
    }

    public String getCodename() {
        return _codename;
    }

    public String getName() {
        return _name;
    }

    public String getId() {
        return _id;
    }
}
