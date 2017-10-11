package kenticocloud.kenticoclouddancinggoat.data.models;

import kenticocloud.kenticoclouddancinggoat.kentico_cloud.models.ContentItem;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.models.elements.AssetsElement;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.models.elements.TextElement;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.models.elements.models.AssetModel;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.models.element.ElementMapping;

public final class Cafe extends ContentItem {

    public static final String TYPE = "cafe";

    @ElementMapping("city")
    public TextElement city;

    @ElementMapping("street")
    public TextElement street;

    @ElementMapping("country")
    public TextElement country;

    @ElementMapping("state")
    public TextElement state;

    @ElementMapping("zip_code")
    public TextElement zipCode;

    @ElementMapping("phone")
    public TextElement phone;

    @ElementMapping("email")
    public TextElement email;

    @ElementMapping("photo")
    public AssetsElement photo;

    public String getCity() {
        return city.getValue();
    }

    public String getStreet() {
        return street.getValue();
    }

    public String getCountry() {
        return country.getValue();
    }

    public String getState() {
        return state.getValue();
    }

    public String getZipCode() {
        return zipCode.getValue();
    }

    public String getPhone() {
        return phone.getValue();
    }

    public String getEmail() {
        return email.getValue();
    }

    public String getPhotoUrl() {

        AssetModel[] assets = photo.getValue();

        if (assets == null){
            return null;
        }

        if (assets.length == 0){
            return null;
        }

        return assets[0].url;
    }
}
