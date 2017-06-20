package com.greencode.petfinder.ui.pages.shelterSinglePage;

import com.greencode.petfinder.data.entity.locanbeans.pet.Pet;
import com.greencode.petfinder.data.entity.locanbeans.shelter.Shelter;
import com.greencode.petfinder.domain.GetPetsInShelterInteractor;
import com.greencode.petfinder.domain.GetShelterInteractor;
import com.greencode.petfinder.ui.pages.petListPage.PetListMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import rx.Observer;

/**
 * @author Anton Kazakov
 * @date 29.04.17.
 */

public class ShelterPresenter implements ShelterPageContract.Presenter {

    private GetShelterInteractor getShelterInteractor;
    private GetPetsInShelterInteractor getPetsInShelterInteractor;
    private ShelterPageContract.View view;
    private PetListMapper petListMapper;

    @Inject
    public ShelterPresenter(GetShelterInteractor getShelterInteractor,
                            GetPetsInShelterInteractor getPetsInShelterInteractor,
                            ShelterPageContract.View view,
                            PetListMapper petListMapper) {
        this.getShelterInteractor = getShelterInteractor;
        this.getPetsInShelterInteractor = getPetsInShelterInteractor;
        this.view = view;
        this.petListMapper = petListMapper;
    }

    @Override
    public void getShelter(String id) {
        Map<String, String> map = new HashMap<>();
        map.put("id", id);
        getShelterInteractor.execute(map, new Observer<Shelter>() {
            @Override
            public void onCompleted() {
                //stub
            }

            @Override
            public void onError(Throwable e) {
                view.showError(e.getLocalizedMessage());
            }

            @Override
            public void onNext(Shelter shelter) {
                view.onShelterLoaded(shelter);
            }
        });
    }

    @Override
    public void getSheltersPet(String id) {
        Map<String, String> map = new HashMap<>();
        map.put("id", id);
        map.put("count", "20");
        getPetsInShelterInteractor.execute(map, new Observer<List<Pet>>() {
            @Override
            public void onCompleted() {
                //stub
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(List<Pet> pets) {

            }
        });
    }

    @Override
    public void start() {

    }

}
