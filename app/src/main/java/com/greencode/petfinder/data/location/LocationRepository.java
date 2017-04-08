package com.greencode.petfinder.data.location;

import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationServices;
import com.greencode.petfinder.data.entity.locanbeans.LocationMapper;
import com.greencode.petfinder.data.entity.locanbeans.simplelocation.SimpleLocation;
import com.greencode.petfinder.data.source.LocationFactory;
import com.greencode.petfinder.data.source.LocationSource;

import javax.inject.Inject;

import io.realm.Realm;
import rx.Emitter;
import rx.Observable;
import rx.Single;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;

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
        return locationFactory.create().getMyLocation();
    }

}
