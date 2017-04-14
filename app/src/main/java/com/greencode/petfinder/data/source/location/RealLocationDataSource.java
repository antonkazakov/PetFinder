package com.greencode.petfinder.data.source.location;

import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.greencode.petfinder.data.mappers.LocationMapper;
import com.greencode.petfinder.data.entity.locanbeans.simplelocation.SimpleLocation;

import javax.inject.Inject;

import io.realm.Realm;
import rx.Emitter;
import rx.Observable;
import rx.functions.Action1;

/**
 * @author Anton Kazakov
 * @date 07.04.17.
 */

public class RealLocationDataSource implements LocationSource{

    private GoogleApiClient googleApiClient;
    private LocationMapper locationMapper;
    private LocationRequest locationRequest;

    @Inject
    public RealLocationDataSource(GoogleApiClient googleApiClient, LocationMapper locationMapper) {
        this.googleApiClient = googleApiClient;
        this.locationMapper = locationMapper;
        locationRequest = new LocationRequest();
        locationRequest.setFastestInterval(10000);
    }

    @Override
    @SuppressWarnings("deprecation")
    public Observable<SimpleLocation> getMyLocation() {
        return Observable.fromEmitter(new Action1<Emitter<Location>>() {
            @Override
            public void call(Emitter<Location> tEmitter) {
                LocationListener locationListener = tEmitter::onNext;
                GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener = connectionResult ->
                        tEmitter.onError(new LocationUnavailableException("ERROR"));

                GoogleApiClient.ConnectionCallbacks connectionCallbacks = new GoogleApiClient.ConnectionCallbacks() {
                    @Override
                    public void onConnected(@Nullable Bundle bundle) {
                        try {
                            LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, locationRequest, locationListener);
                        } catch (SecurityException e) {
                            tEmitter.onError(new LocationUnavailableException("ERROR"));
                        }
                    }

                    @Override
                    public void onConnectionSuspended(int i) {
                        tEmitter.onError(new LocationUnavailableException("ERROR"));
                    }
                };

                googleApiClient.registerConnectionCallbacks(connectionCallbacks);
                googleApiClient.registerConnectionFailedListener(onConnectionFailedListener);
                googleApiClient.connect();
            }
        }, Emitter.BackpressureMode.NONE)
                .take(1)
                .flatMap(location -> {
                    SimpleLocation simpleLocation = locationMapper.transform(location);
                    Realm realm = Realm.getDefaultInstance();
                    realm.beginTransaction();
                    realm.where(SimpleLocation.class).findAll().deleteAllFromRealm();
                    realm.insert(simpleLocation);
                    realm.commitTransaction();
                    realm.close();
                    return Observable.just(simpleLocation);
                })
                .onErrorResumeNext(throwable -> {
                    Log.e("FUCK", "getMyLocation: ",throwable );
                    Realm realm = Realm.getDefaultInstance();
                    Observable<SimpleLocation> simpleLocation = null;
                    if (realm.where(SimpleLocation.class).count() == 0){
                        throw new LocationUnavailableException("CACHE IS EMPTY AND LOCATION IS UNAVAILABLE");
                    }
                    simpleLocation = realm.copyFromRealm(realm.where(SimpleLocation.class).findFirst())
                            .asObservable();
                    realm.close();
                    return simpleLocation;
                });
    }

}
