package com.greencode.petfinder.ui.pages.petListPage;

import android.support.annotation.NonNull;

import com.greencode.petfinder.data.repository.LocationRepository;
import com.greencode.petfinder.data.repository.PetRepository;
import com.greencode.petfinder.ui.injection.FragmentScope;

import dagger.Module;
import dagger.Provides;

/**
 * @author Anton Kazakov
 * @date 25.04.17.
 */
@Module
public class PetListModule {

    private PetListContract.View view;

    public PetListModule(PetListContract.View view) {
        this.view = view;
    }

    @FragmentScope
    @Provides
    public PetListPresenter provideShelterListPresenter(@NonNull PetListMapper petListMapper,
                                                        @NonNull LocationRepository locationRepository,
                                                        @NonNull PetRepository petRepository) {
        return new PetListPresenter(petRepository, locationRepository, petListMapper, view);
    }

    @FragmentScope
    @Provides
    public static PetListMapper provideShelterListMapper() {
        return new PetListMapper();
    }


}
