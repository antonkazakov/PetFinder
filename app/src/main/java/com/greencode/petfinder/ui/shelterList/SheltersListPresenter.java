package com.greencode.petfinder.ui.shelterList;

import android.util.Log;

import com.greencode.petfinder.data.source.location.LocationRepository;
import com.greencode.petfinder.data.source.shelters.SheltersRepository;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author Anton Kazakov
 * @date 02.04.17.
 */

public class SheltersListPresenter implements SheltersContract.Presenter{

    private ShelterListMapper shelterListMapper;
    private LocationRepository locationRepository;
    private SheltersRepository sheltersRepository;
    private SheltersContract.View view;

    @Inject
    public SheltersListPresenter(ShelterListMapper shelterListMapper,
                                 LocationRepository locationRepository,
                                 SheltersRepository sheltersRepository,
                                 SheltersContract.View view) {
        this.shelterListMapper = shelterListMapper;
        this.locationRepository = locationRepository;
        this.sheltersRepository = sheltersRepository;
        this.view = view;
    }

    @Override
    public void start() {

    }

    @Override
    public void getNearShelters() {
        locationRepository.getMyLocation()
                .flatMap(simpleLocation -> {
                    Map<String, String> map = new HashMap<>();
                    map.put("key", "77cffd89b0d4cca16a350862872c2261");
                    map.put("location", simpleLocation.getZipCode());
                    map.put("limit", String.valueOf("40"));
                    return sheltersRepository.getShelters(map);
                })
                .zipWith(locationRepository.getMyLocation(),(shelters, simpleLocation) -> {
                    return shelterListMapper.transformAll(shelters,
                            simpleLocation.getLatitude(),
                            simpleLocation.getLongitude());
                })
                .subscribe(shelterListViewModels -> {
                    view.onSheltersRefreshed(shelterListViewModels);
                }, throwable -> {
                    view.showError(throwable.getLocalizedMessage());
                });
    }

    @Override
    public void getNearSheltersWithForceLocationUpdate() {
        locationRepository.getMyRealLocation()
                .flatMap(simpleLocation -> {
                    Map<String, String> map = new HashMap<>();
                    map.put("key", "77cffd89b0d4cca16a350862872c2261");
                    map.put("location", simpleLocation.getZipCode());
                    map.put("limit", String.valueOf("40"));
                    return sheltersRepository.getSheltersFromCloud(map);
                })
                .zipWith(locationRepository.getMyLocation(),(shelters, simpleLocation) -> {
                    return shelterListMapper.transformAll(shelters,
                            simpleLocation.getLatitude(),
                            simpleLocation.getLongitude());
                })
                .subscribe(shelterListViewModels -> {
                    view.onSheltersRefreshed(shelterListViewModels);
                }, throwable -> {
                    view.showError(throwable.getLocalizedMessage());
                });
    }

    @Override
    public void getMoreShelters() {
        locationRepository.getMyLocation()
                .flatMap(simpleLocation -> {
                    Map<String, String> map = new HashMap<>();
                    map.put("key", "77cffd89b0d4cca16a350862872c2261");
                    map.put("zip", simpleLocation.getZipCode());
                    map.put("limit", String.valueOf(20));
                    map.put("offset", String.valueOf(20));
                    return sheltersRepository.getSheltersFromCloud(map);
                })
                .zipWith(locationRepository.getMyLocation(),(shelters, simpleLocation) -> {
                    return shelterListMapper.transformAll(shelters,
                            simpleLocation.getLatitude(),
                            simpleLocation.getLongitude());
                })
                .subscribe(shelterListViewModels -> {
                    view.onSeltersLoadedMore(shelterListViewModels);
                }, throwable -> {
                    view.showError(throwable.getLocalizedMessage());
                });
    }

}
