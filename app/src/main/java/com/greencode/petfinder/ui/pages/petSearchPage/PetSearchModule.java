package com.greencode.petfinder.ui.pages.petSearchPage;

import com.greencode.petfinder.data.repository.PetRepository;
import com.greencode.petfinder.ui.injection.FragmentScope;

import dagger.Module;
import dagger.Provides;

/**
 * @author Anton Kazakov
 * @date 13.06.17.
 */
@Module
public class PetSearchModule {

    private PetSearchContract.View view;

    public PetSearchModule(PetSearchContract.View view) {
        this.view = view;
    }

    @FragmentScope
    @Provides
    public PetSearchPresenter providePetSearchPresenter(PetRepository petRepository) {
        return new PetSearchPresenter(petRepository, view);
    }

}
