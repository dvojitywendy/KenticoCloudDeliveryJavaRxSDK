package kenticocloud.kenticoclouddancinggoat.data.models;

import org.json.JSONException;

import kenticocloud.kenticoclouddancinggoat.kentico_cloud.models.ContentItem;

/**
 * Created by RichardS on 15. 8. 2017.
 */

public final class Cafe extends ContentItem{

    public static final String TYPE = "cafe";

    private String city;
    private String street;
    private String country;
    private String state;
    private String zipCode;
    private String phone;
    private String email;
    private String photoUrl;

    public void setCity(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public void mapProperties() throws JSONException {
        this.setCity(this.getStringValue("city"));
        this.setCountry(this.getStringValue("country"));
        this.setEmail(this.getStringValue("email"));
        this.setPhone(this.getStringValue("phone"));
        this.setState(this.getStringValue("state"));
        this.setStreet(this.getStringValue("street"));
        this.setZipCode(this.getStringValue("zip_code"));
        this.setPhotoUrl(this.getAssetUrl("photo"));
    }
}
