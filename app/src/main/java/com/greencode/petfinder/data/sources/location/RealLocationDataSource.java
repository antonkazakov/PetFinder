package com.greencode.petfinder.data.sources.location;

import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.greencode.petfinder.data.api.ApiService;
import com.greencode.petfinder.data.entity.locanbeans.simplelocation.SimpleLocation;
import com.greencode.petfinder.data.exception.LocationUnavailableException;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import io.realm.Realm;
import rx.Emitter;
import rx.Observable;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * @author Anton Kazakov
 * @date 07.04.17.
 */

public class RealLocationDataSource implements LocationSource {

    private GoogleApiClient googleApiClient;
    private LocationRequest locationRequest;
    private ApiService apiService;

    @Inject
    public RealLocationDataSource(ApiService apiService, GoogleApiClient googleApiClient) {
        this.apiService = apiService;
        this.googleApiClient = googleApiClient;
        locationRequest = new LocationRequest();
        locationRequest.setNumUpdates(1);
        locationRequest.setFastestInterval(10000L);
    }

    @Override
    @SuppressWarnings("deprecation")
    public Observable<SimpleLocation> getMyLocation() {
        return Observable.fromEmitter(new Action1<Emitter<Location>>() {
            @Override
            public void call(Emitter<Location> tEmitter) {
                LocationListener locationListener = tEmitter::onNext;
                GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener = connectionResult -> tEmitter.onError(new LocationUnavailableException("ERROR"));

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
                .observeOn(Schedulers.io())
                .take(1)
                .flatMap(location -> {
                    Map<String, String> paramMap = new HashMap<>();
                    paramMap.put("key", "AIzaSyATtXbChuIeFV2h47jlmQ9r95swvDlqFYo");
                    paramMap.put("latlng", String.valueOf(location.getLatitude()) +
                                            "," +
                                            String.valueOf(location.getLongitude()));
                    return apiService.convertToAddress("https://maps.googleapis.com/maps/api/geocode/xml", paramMap);
                })
                .flatMap(geocodeResponse -> {
                    SimpleLocation simpleLocation = new SimpleLocation();
                    simpleLocation.setPlaceId(geocodeResponse.getResult().get(0).getPlaceId());
                    simpleLocation.setAddress(geocodeResponse.getResult().get(0).getFormattedAddress());
                    Realm realm = Realm.getDefaultInstance();
                    realm.beginTransaction();
                    realm.where(SimpleLocation.class).findAll().deleteAllFromRealm();
                    realm.insert(simpleLocation);
                    realm.commitTransaction();
                    realm.close();
                    return Observable.just(simpleLocation);
                });

    }

}
