package kenticocloud.kenticoclouddancinggoat.data.models;

import android.support.annotation.Nullable;

import kenticocloud.kenticoclouddancinggoat.kentico_cloud.interfaces.item.item.IContentItem;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.models.ContentItem;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.models.item.RawModels;

/**
 * Created by RichardS on 15. 8. 2017.
 */

public final class Cafe extends ContentItem{
    private String city;
    private String street;
    private String country;
    private String state;
    private String zipCode;
    private String phone;
    private String email;
    private String photo;

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

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void mapProperties(){
        this.setCity(this.GetStringValue("city"));
        this.setCountry(this.GetStringValue("country"));
        this.setEmail(this.GetStringValue("email"));
        this.setPhone(this.GetStringValue("phone"));
        this.setState(this.GetStringValue("state"));
        this.setStreet(this.GetStringValue("street"));
        this.setZipCode(this.GetStringValue("zip_code"));
    }
}
