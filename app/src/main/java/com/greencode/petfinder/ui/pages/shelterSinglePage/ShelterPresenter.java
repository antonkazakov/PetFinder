package com.greencode.petfinder.ui.pages.shelterSinglePage;

import com.greencode.petfinder.data.entity.locanbeans.shelter.Shelter;
import com.greencode.petfinder.data.repository.SheltersRepository;
import com.greencode.petfinder.ui.pages.petListPage.PetListMapper;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

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
        map.put("count", "20");
    }

    @Override
    public void start() {

    }

}
