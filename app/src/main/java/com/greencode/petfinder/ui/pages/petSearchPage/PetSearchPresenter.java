package com.greencode.petfinder.ui.pages.petSearchPage;

import com.greencode.petfinder.data.entity.locanbeans.pet.Pet;
import com.greencode.petfinder.domain.SearchPetsInteractor;

import java.util.List;
import java.util.Map;

import rx.Observer;

/**
 * @author Anton Kazakov
 * @date 13.06.17.
 */

public class PetSearchPresenter implements PetSearchContract.Presenter {

    private SearchPetsInteractor searchPetsInteractor;
    private PetSearchContract.View view;

    public PetSearchPresenter(SearchPetsInteractor searchPetsInteractor, PetSearchContract.View view) {
        this.searchPetsInteractor = searchPetsInteractor;
        this.view = view;
    }

    @Override
    public void start() {

    }

    @Override
    public void searchPets(Map<String, String> filterMap) {
        searchPetsInteractor.execute(filterMap, new Observer<List<Pet>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(List<Pet> pets) {
                view.showPets();
            }
        });
    }

}
