package com.greencode.petfinder.data.repository;

import android.content.Context;
import android.support.annotation.NonNull;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.greencode.petfinder.data.api.ApiService;
import com.greencode.petfinder.data.cache.Cache;
import com.greencode.petfinder.data.cache.LocationCache;
import com.greencode.petfinder.data.entity.locanbeans.simplelocation.SimpleLocation;
import com.greencode.petfinder.data.sources.location.LocationFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author Anton Kazakov
 * @date 01.04.17.
 */
@Module
public class LocationModule {

    @NonNull
    @Provides
    @Singleton
    public static LocationRepository provideLocationRepository(@NonNull LocationFactory locationFactory){
        return new LocationRepository(locationFactory);
    }

    @NonNull
    @Provides
    @Singleton
    public static LocationFactory provideLocationFactory(@NonNull Cache<SimpleLocation> cache,
                                                         @NonNull GoogleApiClient googleApiClient,
                                                         ApiService apiService){
        return new LocationFactory(cache, googleApiClient, apiService);
    }

    @NonNull
    @Singleton
    @Provides
    public Cache<SimpleLocation> provideLocationCache(){
        return new LocationCache();
    }


    @NonNull
    @Provides
    @Singleton
    public static GoogleApiClient provideGoogleApiClient(@NonNull Context context) {
        return new GoogleApiClient.Builder(context).addApi(LocationServices.API).build();
    }


}
