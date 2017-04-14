package com.greencode.petfinder.ui.shelterList;

import android.support.annotation.NonNull;

import com.greencode.petfinder.data.source.location.LocationRepository;
import com.greencode.petfinder.data.source.shelters.SheltersRepository;
import com.greencode.petfinder.ui.FragmentScope;

import dagger.Module;
import dagger.Provides;

/**
 * @author Anton Kazakov
 * @date 14.04.17.
 */

@Module
public class ShelterListModule {

    private SheltersContract.View view;

    public ShelterListModule(SheltersContract.View view) {
        this.view = view;
    }

    @FragmentScope
    @Provides
    public SheltersListPresenter provideShelterListPresenter(@NonNull ShelterListMapper shelterListMapper,
                                                                    @NonNull LocationRepository locationRepository,
                                                                    @NonNull SheltersRepository sheltersRepository
                                                                    ){
        return new SheltersListPresenter(shelterListMapper, locationRepository, sheltersRepository, view);
    }

    @FragmentScope
    @Provides
    public static ShelterListMapper provideShelterListMapper(){
        return new ShelterListMapper();
    }

}
