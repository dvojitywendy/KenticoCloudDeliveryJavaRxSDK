package com.kentico.delivery.sample.androidapp.data.models;

import com.kentico.delivery.core.models.ContentItem;
import com.kentico.delivery.core.models.elements.AssetsElement;
import com.kentico.delivery.core.models.elements.NumberElement;
import com.kentico.delivery.core.models.elements.RichTextElement;
import com.kentico.delivery.core.models.elements.TextElement;
import com.kentico.delivery.core.models.elements.models.AssetModel;
import com.kentico.delivery.core.models.element.ElementMapping;

public final class Coffee extends ContentItem{

    public static final String TYPE = "coffee";

    @ElementMapping("product_name")
    public TextElement productName;

    @ElementMapping("price")
    public NumberElement price;

    @ElementMapping("image")
    public AssetsElement image;

    @ElementMapping("short_description")
    public RichTextElement shortDescription;

    @ElementMapping("long_description")
    public RichTextElement longDescription;

    @ElementMapping("farm")
    public TextElement farm;

    @ElementMapping("country")
    public TextElement country;

    @ElementMapping("variety")
    public TextElement variety;

    @ElementMapping("altitude")
    public TextElement altitude;

    public String getProductName() { return productName.getValue(); }

    public double getPrice() { return price.getValue(); }

    public String getImageUrl() {
        AssetModel[] assets = image.getValue();

        if (assets == null){
            return null;
        }

        if (assets.length == 0){
            return null;
        }

        return assets[0].url;
    }

    public String getShortDescription() {
        return shortDescription.getValue();
    }

    public String getLongDescription() {
        return longDescription.getValue();
    }

    public String getFarm() {
        return farm.getValue();
    }

    public String getCountry() {
        return country.getValue();
    }

    public String getVariety() {
        return variety.getValue();
    }

    public String getAltitude() {
        return altitude.getValue();
    }

}
