package com.greencode.petfinder.ui.singlePet;

import android.support.annotation.NonNull;

import com.greencode.petfinder.data.source.pet.PetRepository;
import com.greencode.petfinder.ui.FragmentScope;

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
    public SinglePetPresenter provideSinglePetPresenter(@NonNull PetRepository petRepository){
        return new SinglePetPresenter(petRepository, view);
    }

}
