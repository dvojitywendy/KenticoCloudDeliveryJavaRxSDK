package kenticocloud.kenticoclouddancinggoat.data.models;

import org.json.JSONException;
import org.w3c.dom.Text;

import java.text.ParseException;
import java.util.Date;

import kenticocloud.kenticoclouddancinggoat.kentico_cloud.models.ContentItem;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.models.elements.AssetsElement;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.models.elements.NumberElement;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.models.elements.RichTextElement;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.models.elements.TextElement;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.models.elements.models.AssetModel;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.models.item.ElementMapping;

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
