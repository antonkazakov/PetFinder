package com.greencode.petfinder.data.mappers;

import android.location.Address;
import android.location.Geocoder;
import android.location.Location;

import com.greencode.petfinder.data.entity.locanbeans.simplelocation.SimpleLocation;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

/**
 * @author Anton Kazakov
 * @date 01.04.17.
 */

public class LocationMapper implements Mapper<Location, SimpleLocation>{

    private Geocoder geocoder;

    @Inject
    public LocationMapper(Geocoder geocoder) {
        this.geocoder = geocoder;
    }

    @Override
    public SimpleLocation transform(Location location) {
        List<Address> addresses = null;
        // TODO: 07.04.17 CHECK
        try {
            addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // TODO: 07.04.17 null check
        String zipCode = addresses.get(0).getPostalCode();
        SimpleLocation simpleLocation = new SimpleLocation();
        simpleLocation.setLatitude(location.getLatitude());
        simpleLocation.setLongitude(location.getLongitude());
        simpleLocation.setCreated(System.currentTimeMillis());
        simpleLocation.setZipCode(zipCode);
        return simpleLocation;
    }

    @Override
    public List<SimpleLocation> transform(List<Location> locations) {
        return null;
    }
}
