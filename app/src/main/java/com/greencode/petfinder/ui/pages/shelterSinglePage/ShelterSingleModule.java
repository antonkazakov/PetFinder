package com.greencode.petfinder.ui.pages.shelterSinglePage;

import android.support.annotation.NonNull;

import com.greencode.petfinder.data.repository.SheltersRepository;
import com.greencode.petfinder.domain.GetPetsInShelterInteractor;
import com.greencode.petfinder.domain.GetShelterInteractor;
import com.greencode.petfinder.domain.injection.JobThread;
import com.greencode.petfinder.domain.injection.UIThread;
import com.greencode.petfinder.ui.injection.FragmentScope;
import com.greencode.petfinder.ui.pages.petListPage.PetListMapper;

import dagger.Module;
import dagger.Provides;
import rx.Scheduler;

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
    public ShelterPresenter provideShelterListPresenter(GetShelterInteractor getShelterInteractor,
                                                        GetPetsInShelterInteractor getPetsInShelterInteractor,
                                                        PetListMapper petListMapper) {
        return new ShelterPresenter(getShelterInteractor, getPetsInShelterInteractor, view, petListMapper);
    }

    @FragmentScope
    @Provides
    public GetShelterInteractor provideGetShelterInteractor(@UIThread Scheduler uiScheduler,
                                                            @JobThread Scheduler jobScheduler,
                                                            @NonNull SheltersRepository sheltersRepository) {
        return new GetShelterInteractor(uiScheduler, jobScheduler, sheltersRepository);
    }

    @FragmentScope
    @Provides
    public PetListMapper providePetListMapper() {
        return new PetListMapper();
    }

}
