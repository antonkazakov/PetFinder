package com.greencode.petfinder.data.repository;

import com.greencode.petfinder.data.entity.locanbeans.simplelocation.SimpleLocation;
import com.greencode.petfinder.data.sources.location.LocationFactory;
import com.greencode.petfinder.data.sources.location.LocationSource;

import javax.inject.Inject;

import rx.Observable;

/**
 * @author Anton Kazakov
 * @date 01.04.17.
 */

public class LocationRepository implements LocationSource{

    private LocationFactory locationFactory;

    @Inject
    public LocationRepository(LocationFactory locationFactory) {
        this.locationFactory = locationFactory;
    }

    @Override
    public Observable<SimpleLocation> getMyLocation() {
        return locationFactory.createDependingOnCache().getMyLocation();
    }

    public Observable<SimpleLocation> getMyRealLocation(){
        return locationFactory.createCloudDataSource().getMyLocation();
    }

}
