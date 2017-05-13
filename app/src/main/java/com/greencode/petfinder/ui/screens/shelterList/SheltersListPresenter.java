package com.greencode.petfinder.ui.screens.shelterList;

import android.util.Log;

import com.greencode.petfinder.data.repository.LocationRepository;
import com.greencode.petfinder.data.repository.SheltersRepository;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

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
                .take(1)
                .flatMap(simpleLocation -> {
                    Map<String, String> map = new HashMap<>();
                    map.put("key", "77cffd89b0d4cca16a350862872c2261");
                    map.put("location", simpleLocation.getAddress());
                    map.put("limit", "40");
                    return sheltersRepository.getShelters(map);
                })
                .zipWith(locationRepository.getMyLocation(), (shelters, simpleLocation) -> {
                return shelterListMapper.transformAll(shelters);
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
                .take(1)
                .flatMap(simpleLocation -> {
                    Map<String, String> map = new HashMap<>();
                    map.put("key", "77cffd89b0d4cca16a350862872c2261");
                    map.put("location", simpleLocation.getAddress());
                    map.put("limit", String.valueOf("40"));
                    return sheltersRepository.getSheltersFromCloud(map);
                })
                .zipWith(locationRepository.getMyLocation(),(shelters, simpleLocation) -> {
                   return shelterListMapper.transformAll(shelters);
                })
                .subscribe(shelterListViewModels -> {
                    Log.i("FUCK", "getMoreShelters1: ");
                    view.onSheltersRefreshed(shelterListViewModels);
                }, throwable -> {
                    Log.e("FUCK", "getMoreShelters: ", throwable);
                    view.showError(throwable.getLocalizedMessage());
                });
    }

    @Override
    public void getMoreShelters() {
        locationRepository.getMyLocation()
                .flatMap(simpleLocation -> {
                    Map<String, String> map = new HashMap<>();
                    map.put("key", "77cffd89b0d4cca16a350862872c2261");
                    map.put("limit", String.valueOf(20));
                    map.put("offset", String.valueOf(20));
                    return sheltersRepository.getSheltersFromCloud(map);
                })
                .zipWith(locationRepository.getMyLocation(),(shelters, simpleLocation) -> {
                    return null;
                })
                .subscribe(shelterListViewModels -> {
                }, throwable -> {
                });
    }

}
