package kenticocloud.kenticoclouddancinggoat.data.models;

import org.json.JSONException;

import java.text.ParseException;
import java.util.Date;

import kenticocloud.kenticoclouddancinggoat.kentico_cloud.models.ContentItem;

/**
 * Created by RichardS on 15. 8. 2017.
 */

public final class Coffee extends ContentItem{

    public static final String TYPE = "coffee";

    private String _productName;
    private double _price;
    private String _imageUrl;
    private String _shortDescription;
    private String _longDescription;
    private String _farm;
    private String _country;
    private String _variety;
    private String _altitude;

    public void setProductName(String productName) {
        this._productName = productName;
    }

    public String getProductName() {
        return _productName;
    }

    public void setPrice(double price) {
        this._price = price;
    }

    public double getPrice() {
        return _price;
    }

    public void setImageUrl(String imageUrl) {
        this._imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return _imageUrl;
    }

    public void setShortDescription(String description) {
        this._shortDescription = description;
    }

    public String getShortDescription() {
        return _shortDescription;
    }

    public void setLongDescription(String description) {
        this._longDescription = description;
    }

    public String getLongDescription() {
        return _longDescription;
    }

    public void setFarm(String farm) {
        this._farm = farm;
    }

    public String getFarm() {
        return _farm;
    }

    public void setCountry(String country) {
        this._country = country;
    }

    public String getCountry() {
        return _country;
    }

    public void setVariety(String variety) {
        this._variety = variety;
    }

    public String getVariety() {
        return _variety;
    }


    public void setAltitude(String altitude) {
        this._altitude = altitude;
    }

    public String getAltitude() {
        return _altitude;
    }

    @Override
    public void mapProperties() throws ParseException, JSONException {
        this.setProductName(this.getStringValue("product_name"));
        this.setPrice(this.getDoubleValue("price"));
        this.setImageUrl(this.getAssetUrl("image"));
        this.setShortDescription(this.getStringValue("short_description"));
        this.setLongDescription(this.getStringValue("long_description"));
        this.setFarm(this.getStringValue("farm"));
        this.setCountry(this.getStringValue("country"));
        this.setVariety(this.getStringValue("variety"));
        this.setAltitude(this.getStringValue("altitude"));
    }
}
