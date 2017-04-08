package com.greencode.petfinder.data.source;

import com.google.android.gms.common.api.GoogleApiClient;
import com.greencode.petfinder.data.cache.Cache;
import com.greencode.petfinder.data.entity.mappers.LocationMapper;
import com.greencode.petfinder.data.entity.locanbeans.simplelocation.SimpleLocation;

import javax.inject.Inject;

/**
 * @author Anton Kazakov
 * @date 07.04.17.
 */

public class LocationFactory {

    private Cache<SimpleLocation> locationCache;
    private LocationMapper locationMapper;
    private GoogleApiClient googleApiClient;

    @Inject
    public LocationFactory(Cache<SimpleLocation> locationCache, GoogleApiClient googleApiClient, LocationMapper locationMapper) {
        this.locationCache = locationCache;
        this.googleApiClient = googleApiClient;
        this.locationMapper = locationMapper;
    }

    public LocationSource create(){
        if (locationCache.isExpired()){
            return new RealLocationDataSource(googleApiClient, locationMapper);
        }else {
            return new LocationLocalDataSource();
        }
    }

}
