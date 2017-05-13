package com.greencode.petfinder.ui.screens.shelterSingle;

import android.support.annotation.NonNull;

import com.greencode.petfinder.data.repository.SheltersRepository;
import com.greencode.petfinder.ui.FragmentScope;
import com.greencode.petfinder.ui.screens.petList.PetListMapper;

import dagger.Module;
import dagger.Provides;

/**
 * @author Anton Kazakov
 * @date 14.04.17.
 */

@Module
public class ShelterSingleModule {

    private ShelterPageContract.View view;

    public ShelterSingleModule(ShelterPageContract.View view) {
        this.view = view;
    }

    @FragmentScope
    @Provides
    public ShelterPresenter provideShelterListPresenter(@NonNull SheltersRepository sheltersRepository,
                                                        PetListMapper petListMapper){
        return new ShelterPresenter(sheltersRepository, view, petListMapper);
    }

    @FragmentScope
    @Provides
    public static PetListMapper providePetListMapper(){
        return new PetListMapper();
    }

}
