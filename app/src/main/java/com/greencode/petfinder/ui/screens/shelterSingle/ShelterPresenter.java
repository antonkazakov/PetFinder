package com.greencode.petfinder.ui.screens.shelterSingle;

import android.content.Intent;
import android.util.Log;

import com.greencode.petfinder.data.entity.locanbeans.shelter.Shelter;
import com.greencode.petfinder.data.repository.SheltersRepository;
import com.greencode.petfinder.ui.screens.petList.PetListMapper;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import rx.Observable;
import rx.functions.Action1;

/**
 * @author Anton Kazakov
 * @date 29.04.17.
 */

public class ShelterPresenter implements ShelterPageContract.Presenter {

    private SheltersRepository sheltersRepository;
    private ShelterPageContract.View view;
    private PetListMapper petListMapper;


    @Inject
    public ShelterPresenter(SheltersRepository sheltersRepository, ShelterPageContract.View view, PetListMapper petListMapper) {
        this.sheltersRepository = sheltersRepository;
        this.view = view;
        this.petListMapper = petListMapper;
    }

    @Override
    public void getShelter(String id) {
        Map<String, String> map = new HashMap<>();
        map.put("id", id);
        map.put("key", "77cffd89b0d4cca16a350862872c2261");
        sheltersRepository.getShelter(map)
                .subscribe(new Action1<Shelter>() {
                    @Override
                    public void call(Shelter shelter) {
                        view.onShelterLoaded(shelter);
                    }
                }, throwable -> {
                    view.showError(throwable.getLocalizedMessage());
                });
    }

    @Override
    public void getSheltersPet(String id) {
        Map<String, String> map = new HashMap<>();
        map.put("id", id);
        map.put("key", "77cffd89b0d4cca16a350862872c2261");
        sheltersRepository.getPetsInShelter(map)
                .flatMap(pets -> Observable.just(petListMapper.transformSinglePet(pets)))
                .subscribe(testFuckItems -> {
                    Log.i("sfsdf", "getSheltersPet: " + testFuckItems.size());
                    view.onPetsFromShelterLoaded(testFuckItems);
                }, throwable -> {
                    Log.e("TEST", "call: ", throwable);
                });
    }

    @Override
    public void start() {

    }

}
