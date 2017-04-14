package com.greencode.petfinder.data.source.location;

import com.google.android.gms.common.api.GoogleApiClient;
import com.greencode.petfinder.data.cache.Cache;
import com.greencode.petfinder.data.mappers.LocationMapper;
import com.greencode.petfinder.data.entity.locanbeans.simplelocation.SimpleLocation;

import javax.inject.Inject;

/**
 * @author Anton Kazakov
 * @date 07.04.17.
 */

public class LocationFactory implements AbstractLocationFactory<LocationLocalDataSource, RealLocationDataSource>{

    private Cache<SimpleLocation> locationCache;
    private LocationMapper locationMapper;
    private GoogleApiClient googleApiClient;

    private static LocationLocalDataSource locationLocalDataSource;
    private static RealLocationDataSource realLocationDataSource;

    @Inject
    public LocationFactory(Cache<SimpleLocation> locationCache, GoogleApiClient googleApiClient, LocationMapper locationMapper) {
        this.locationCache = locationCache;
        this.googleApiClient = googleApiClient;
        this.locationMapper = locationMapper;
    }

    @Override
    public LocationSource createDependingOnCache() {
        if (locationCache.isExpired()){
            return createCloudDataSource();
        }else {
            return createLocalDataSource();
        }
    }

    @Override
    public LocationLocalDataSource createLocalDataSource() {
        if (locationLocalDataSource == null){
            locationLocalDataSource = new LocationLocalDataSource();
        }
        return locationLocalDataSource;
    }

    @Override
    public RealLocationDataSource createCloudDataSource() {
        if (realLocationDataSource == null){
            realLocationDataSource = new RealLocationDataSource(googleApiClient, locationMapper);
        }
        return realLocationDataSource;
    }
}
