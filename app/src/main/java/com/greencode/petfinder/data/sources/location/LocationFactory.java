package com.greencode.petfinder.data.sources.location;

import android.util.Log;

import com.google.android.gms.common.api.GoogleApiClient;
import com.greencode.petfinder.data.api.ApiService;
import com.greencode.petfinder.data.cache.Cache;
import com.greencode.petfinder.data.entity.locanbeans.simplelocation.SimpleLocation;

import javax.inject.Inject;

/**
 * @author Anton Kazakov
 * @date 07.04.17.
 */

public class LocationFactory implements AbstractLocationFactory<LocationLocalDataSource, RealLocationDataSource> {

    private final String TAG = this.getClass().getSimpleName();
    
    private Cache<SimpleLocation> locationCache;
    private GoogleApiClient googleApiClient;
    private ApiService apiService;

    private static LocationLocalDataSource locationLocalDataSource;
    private static RealLocationDataSource realLocationDataSource;

    @Inject
    public LocationFactory(Cache<SimpleLocation> locationCache, GoogleApiClient googleApiClient, ApiService apiService) {
        this.locationCache = locationCache;
        this.googleApiClient = googleApiClient;
        this.apiService = apiService;
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
        Log.i(TAG, "createLocalDataSource: ");
        return locationLocalDataSource;
    }

    @Override
    public RealLocationDataSource createCloudDataSource() {
        if (realLocationDataSource == null){
            realLocationDataSource = new RealLocationDataSource(apiService, googleApiClient);
        }
        Log.i(TAG, "createCloudDataSource: ");
        return realLocationDataSource;
    }
}
