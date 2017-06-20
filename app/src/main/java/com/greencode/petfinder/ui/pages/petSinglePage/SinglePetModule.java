package com.greencode.petfinder.ui.pages.petSinglePage;

import android.support.annotation.NonNull;

import com.greencode.petfinder.data.repository.PetRepository;
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
    public SinglePetPresenter provideSinglePetPresenter(@NonNull PetRepository petRepository,
                                                        SinglePetMapper singlePetMapper){
        return new SinglePetPresenter(petRepository, view, singlePetMapper);
    }

    @FragmentScope
    @Provides
    public static SinglePetMapper provideSinglePetMapper(){
        return new SinglePetMapper();
    }

}
