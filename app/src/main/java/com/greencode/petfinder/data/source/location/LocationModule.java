package com.greencode.petfinder.data.source.location;

import android.content.Context;
import android.location.Geocoder;
import android.location.Location;
import android.support.annotation.NonNull;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.greencode.petfinder.data.cache.Cache;
import com.greencode.petfinder.data.cache.LocationCache;
import com.greencode.petfinder.data.mappers.LocationMapper;
import com.greencode.petfinder.data.entity.locanbeans.simplelocation.SimpleLocation;
import com.greencode.petfinder.data.mappers.Mapper;

import java.util.Locale;

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
                                                         @NonNull LocationMapper locationMapper,
                                                         @NonNull GoogleApiClient googleApiClient){
        return new LocationFactory(cache, googleApiClient, locationMapper);
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
    public static Mapper<Location, SimpleLocation> provideSimpleLocationCache(@NonNull Geocoder geocoder){
        return new LocationMapper(geocoder);
    }

    @NonNull
    @Provides
    @Singleton
    public static GoogleApiClient provideGoogleApiClient(@NonNull Context context) {
        return new GoogleApiClient.Builder(context).addApi(LocationServices.API).build();
    }

    @NonNull
    @Provides
    @Singleton
    public static Geocoder provideGeocoder(@NonNull Context context) {
        return new Geocoder(context, Locale.getDefault());
    }

}
