package com.greencode.petfinder.data;

import android.content.Context;

import com.greencode.petfinder.data.api.ApiService;
import com.greencode.petfinder.data.mappers.PetMapper;
import com.greencode.petfinder.data.repository.LocationModule;
import com.greencode.petfinder.data.repository.LocationRepository;
import com.greencode.petfinder.data.repository.PetRepository;
import com.greencode.petfinder.data.repository.PetRepositoryModule;
import com.greencode.petfinder.data.repository.ShelterRepositoryModule;
import com.greencode.petfinder.data.repository.SheltersRepository;
import com.greencode.petfinder.domain.DomainModule;
import com.greencode.petfinder.domain.injection.JobThread;
import com.greencode.petfinder.domain.injection.UIThread;
import com.greencode.petfinder.ui.pages.shelterSinglePage.ShelterSingleModule;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;
import rx.Scheduler;

/**
 * @author Anton Kazakov
 * @date 30.03.17.
 */
@Singleton
@Component(modules = {NetworkModule.class,
        PetRepositoryModule.class,
        AppModule.class,
        LocationModule.class,
        ShelterRepositoryModule.class,
        ShelterSingleModule.class,
        DomainModule.class})
public interface AppComponent {

    ApiService apiService();

    PetMapper petMapper();

    PetRepository petRepository();

    SheltersRepository shelterRepository();

    LocationRepository locationRepository();

    Context context();

    Retrofit retrofit();

    @JobThread
    Scheduler jobScheduler();

    @UIThread
    Scheduler uiScheduler();

}
