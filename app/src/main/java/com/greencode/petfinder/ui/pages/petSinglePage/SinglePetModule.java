package com.greencode.petfinder.ui.pages.petSinglePage;

import android.support.annotation.NonNull;

import com.greencode.petfinder.domain.GetPetInteractor;
import com.greencode.petfinder.domain.GetPetsInShelterInteractor;
import com.greencode.petfinder.ui.injection.FragmentScope;

import dagger.Module;
import dagger.Provides;

/**
 * @author Anton Kazakov
 * @date 09.04.17.
 */
@Module
public class SinglePetModule {

    private SinglePetContract.View view;

    public SinglePetModule(SinglePetContract.View view) {
        this.view = view;
    }

    @Provides
    @FragmentScope
    public SinglePetPresenter provideSinglePetPresenter(@NonNull SinglePetMapper singlePetMapper,
                                                        @NonNull GetPetInteractor getPetInteractor,
                                                        @NonNull GetPetsInShelterInteractor getPetsInShelterInteractor) {

        return new SinglePetPresenter(view, singlePetMapper, getPetInteractor, getPetsInShelterInteractor);
    }

    @FragmentScope
    @Provides
    public static SinglePetMapper provideSinglePetMapper() {
        return new SinglePetMapper();
    }

}
