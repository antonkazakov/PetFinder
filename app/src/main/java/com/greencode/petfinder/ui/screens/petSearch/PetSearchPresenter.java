package com.greencode.petfinder.ui.screens.petSearch;

import com.greencode.petfinder.data.repository.PetRepository;

import java.util.Map;

/**
 * @author Anton Kazakov
 * @date 13.06.17.
 */

public class PetSearchPresenter implements PetSearchContract.Presenter {

    private PetRepository petRepository;
    private PetSearchContract.View view;

    public PetSearchPresenter(PetRepository petRepository, PetSearchContract.View view) {
        this.petRepository = petRepository;
        this.view = view;
    }

    @Override
    public void start() {

    }

    @Override
    public void searchPets(Map<String, String> filterMap) {
        petRepository.findPet(filterMap)
                .doOnSubscribe(() -> view.showLoading(true))
                .doOnTerminate(() -> view.showLoading(false))
                .subscribe(pets -> {
                        },
                        throwable -> {
                        });
    }


}
