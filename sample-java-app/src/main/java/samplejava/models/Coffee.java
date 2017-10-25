package samplejava.models;

import com.kenticocloud.delivery.models.ContentItem;
import com.kenticocloud.delivery.models.element.ElementMapping;
import com.kenticocloud.delivery.models.elements.AssetsElement;
import com.kenticocloud.delivery.models.elements.NumberElement;
import com.kenticocloud.delivery.models.elements.RichTextElement;
import com.kenticocloud.delivery.models.elements.TextElement;
import com.kenticocloud.delivery.models.elements.models.AssetModel;

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
