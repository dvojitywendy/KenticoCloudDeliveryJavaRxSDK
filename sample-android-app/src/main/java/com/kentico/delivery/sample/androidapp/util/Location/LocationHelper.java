package com.kentico.delivery.sample.androidapp.util.Location;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;

import java.io.IOException;
import java.util.List;


public class LocationHelper {

    public static LocationInfo getLocationFromAddress(Context context, String street, String city, String country) throws IOException {
        Geocoder geocoder = new Geocoder(context);
        // join address, city and country to get full address
        String address = street + ", " + city + ", " + country;

        List<Address> addresses = geocoder.getFromLocationName(address, 1);
        if(addresses.size() > 0) {
            double latitude= addresses.get(0).getLatitude();
            double longitude= addresses.get(0).getLongitude();

            return new LocationInfo(latitude, longitude);
        }

        return null;
    }
}
