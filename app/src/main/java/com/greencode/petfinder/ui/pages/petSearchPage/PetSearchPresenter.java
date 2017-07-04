package com.greencode.petfinder.ui.pages.petSearchPage;

import android.support.annotation.NonNull;

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
    private PetSearchMapper petSearchMapper;

    public PetSearchPresenter(@NonNull SearchPetsInteractor searchPetsInteractor,
                              @NonNull PetSearchContract.View view,
                              @NonNull PetSearchMapper petSearchMapper) {
        this.searchPetsInteractor = searchPetsInteractor;
        this.view = view;
        this.petSearchMapper = petSearchMapper;
    }

    @Override
    public void destroy() {
        searchPetsInteractor.clear();
    }

    @Override
    public void searchPets(Map<String, String> filterMap) {
        searchPetsInteractor.buildAndExecute(filterMap,
                () -> view.showLoading(true),
                () -> view.showLoading(false),
                new PetSearchObserver());
    }

    private List<PetSearchResultsItemView> transformAndUpdateView(List<Pet> pets) {
        return petSearchMapper.transformSinglePet(pets);
    }

    private class PetSearchObserver implements Observer<List<Pet>> {

        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
            view.showError(e.getLocalizedMessage());
        }

        @Override
        public void onNext(List<Pet> pets) {
            List<PetSearchResultsItemView> petListItemViews =
                    PetSearchPresenter.this.transformAndUpdateView(pets);
            view.showPets(petListItemViews);
        }
    }

}
