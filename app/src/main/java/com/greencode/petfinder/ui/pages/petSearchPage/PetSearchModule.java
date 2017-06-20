package com.greencode.petfinder.ui.pages.petSearchPage;

import android.support.annotation.NonNull;

import com.greencode.petfinder.data.repository.PetRepository;
import com.greencode.petfinder.domain.SearchPetsInteractor;
import com.greencode.petfinder.domain.injection.JobThread;
import com.greencode.petfinder.domain.injection.UIThread;
import com.greencode.petfinder.ui.injection.FragmentScope;

import dagger.Module;
import dagger.Provides;
import rx.Scheduler;

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
    public PetSearchPresenter providePetSearchPresenter(SearchPetsInteractor searchPetsInteractor) {
        return new PetSearchPresenter(searchPetsInteractor, view);
    }

    @FragmentScope
    @Provides
    public SearchPetsInteractor provideSearchPetsInteractor(@UIThread Scheduler uiScheduler,
                                                            @JobThread Scheduler jobScheduler,
                                                            @NonNull PetRepository petRepository) {
        return new SearchPetsInteractor(uiScheduler, jobScheduler, petRepository);
    }

}
